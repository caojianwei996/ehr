package com.neusoft.ehr.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.dto.LoginDto;
import com.neusoft.ehr.entity.dto.UpdatePasswordDto;
import com.neusoft.ehr.entity.po.EmployeesPo;
import com.neusoft.ehr.entity.vo.LoginVo;
import com.neusoft.ehr.service.IEmployeeService;
import com.neusoft.ehr.mapper.EmployeesMapper;
import com.neusoft.ehr.utils.TokenUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.neusoft.ehr.entity.ServiceCode.PASSWORD_ERROR;
import static com.neusoft.ehr.entity.ServiceCode.USER_NOT_EXIST;

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

                //生成载荷数据
                HashMap<String, Object> jwtMap = new HashMap<>();
                jwtMap.put("id", result.getId());
                jwtMap.put("name", result.getName());
                jwtMap.put("email", result.getEmail());
                jwtMap.put("authority", result.getAuthority());

                //传入载荷数据，生成token
                String token = "Bearer "+tokenUtil.toToken(jwtMap);

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
    public LoginVo updatePassword(UpdatePasswordDto data) {
        //先判断原密码是否正确
        String oldPassword = data.getOldPassword();
        QueryWrapper<EmployeesPo> queryWrapper = new QueryWrapper<EmployeesPo>();
        EmployeesPo employeesPo = employeesMapper.selectOne(queryWrapper.eq("password", oldPassword));



        return null;
    }
}
