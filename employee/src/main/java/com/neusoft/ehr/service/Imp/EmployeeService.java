package com.neusoft.ehr.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.dto.LoginDto;
import com.neusoft.ehr.entity.dto.UpdatePasswordDto;
import com.neusoft.ehr.entity.po.EmployeesPo;
import com.neusoft.ehr.entity.vo.LoginVo;
import com.neusoft.ehr.service.IEmployeeService;
import com.neusoft.ehr.mapper.EmployeesMapper;
import com.neusoft.ehr.util.token.TokenUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.neusoft.ehr.entity.ServiceCode.*;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    private EmployeesMapper employeesMapper;

    @Override
    public LoginVo login(LoginDto data) {
        //1.先判断用户是否存在
        QueryWrapper<EmployeesPo> queryWrapper = new QueryWrapper<EmployeesPo>().eq("name", data.getUsername());
        EmployeesPo employees = employeesMapper.selectOne(queryWrapper);
        //2.用户存在
        if (employees != null) {
            //3.再判断密码是否正确
            String inputPassword = data.getPassword();
            //解密成功
            if (BCrypt.checkpw(inputPassword, employees.getPassword())) {

                LoginVo result = new LoginVo();
                result.setId(employees.getId());
                result.setName(employees.getName());
                result.setEmail(employees.getEmail());
                result.setAuthority(employees.getAuthority());

                //传入载荷数据，生成token
                String token = "Bearer "+tokenUtil.encode(result);

                result.setToken(token);
                //返回结果
                return result;
            } else {
                throw new ServiceException(PASSWORD_ERROR);
            }
        } else {
            throw new ServiceException(USER_NOT_EXIST);
        }
    }

    @Override
    public Void reset(String email) {
        //先查该用户是否存在
        QueryWrapper<EmployeesPo> queryWrapper = new QueryWrapper<EmployeesPo>().eq("email", email);
        EmployeesPo employees = employeesMapper.selectOne(queryWrapper);
        if(employees!=null){
            String newPassword = "123456";
            String hashpw = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            employees.setPassword(hashpw);
            employeesMapper.update(employees,queryWrapper);
        }else {
            throw new ServiceException(USER_NOT_EXIST);
        }
        return null;
    }

    @Override
    public LoginVo updatePassword(UpdatePasswordDto data,LoginVo loginVo) {
        QueryWrapper<EmployeesPo> queryWrapper = new QueryWrapper<EmployeesPo>();
        //先查该用户的原密码
        EmployeesPo employee = employeesMapper.selectOne(queryWrapper.eq("name", loginVo.getName()));
        String oldHashPw = employee.getPassword();

        //判断输入的原密码是否正确
        String oldPw = data.getOldPassword();

        if(BCrypt.checkpw(oldPw,oldHashPw)) {
            //密码匹配成功,允许修改密码
            //加密新密码
            String newHashPw = BCrypt.hashpw(data.getNewPassword(), BCrypt.gensalt());
            employee.setPassword(newHashPw);
            employeesMapper.update(new UpdateWrapper<EmployeesPo>().eq("name",loginVo.getName()));
            //该返回一个信息，
            return loginVo;
        }
        throw new ServiceException(OLD_PASSWORD_ERROR);
    }
}
