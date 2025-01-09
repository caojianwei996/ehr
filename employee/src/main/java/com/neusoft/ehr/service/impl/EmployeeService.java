package com.neusoft.ehr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neusoft.ehr.entity.ServiceCode;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.dto.AddEmployeeInfoDto;
import com.neusoft.ehr.entity.dto.LoginDto;
import com.neusoft.ehr.entity.dto.UpdatePasswordDto;
import com.neusoft.ehr.entity.po.EmployeesPo;
import com.neusoft.ehr.entity.vo.EmployeeInfoVo;
import com.neusoft.ehr.entity.vo.LoginVo;
import com.neusoft.ehr.service.IEmployeeService;
import com.neusoft.ehr.mapper.EmployeesMapper;
import com.neusoft.ehr.util.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.neusoft.ehr.entity.ServiceCode.EMAIL_CONFLICT;
import static com.neusoft.ehr.entity.ServiceCode.OLD_PASSWORD_ERROR;

@RequiredArgsConstructor
@Service
public class EmployeeService implements IEmployeeService {
    private final TokenUtil tokenUtil;
    private final EmployeesMapper employeesMapper;

    @Override
    public LoginVo login(LoginDto data) {
        //1.先判断用户是否存在
        EmployeesPo employees = employeesMapper.selectOne(
                Wrappers.<EmployeesPo>lambdaQuery()
                        .eq(EmployeesPo::getEmail, data.getUsername())
        );
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
                String token = "Bearer " + tokenUtil.encode(result);

                result.setToken(token);
                //返回结果
                return result;
            } else {
                throw new ServiceException(ServiceCode.PASSWORD_ERROR);
            }
        } else {
            throw new ServiceException(ServiceCode.USER_NOT_EXIST);
        }
    }

    @Override
    public void reset(String email) {
        //先查该用户是否存在
        EmployeesPo employees = employeesMapper.selectOne(
                Wrappers.<EmployeesPo>lambdaQuery()
                        .eq(EmployeesPo::getEmail, email)
        );
        if (employees != null) {
            String newPassword = "123456";
            employees.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
            employeesMapper.updateById(employees);
        } else {
            throw new ServiceException(ServiceCode.USER_NOT_EXIST);
        }
    }

    @Override
    public void updatePassword(UpdatePasswordDto data, LoginVo loginVo) {
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
        }
        throw new ServiceException(OLD_PASSWORD_ERROR);
    }

    @Override
    public void addEmployee(AddEmployeeInfoDto data) {

        //先判断是否邮箱冲突
        EmployeesPo employeesPo = employeesMapper.selectOne(new QueryWrapper<EmployeesPo>().eq("email", data.getEmail()));
        if(employeesPo ==null){
            EmployeesPo employee = new EmployeesPo();
            System.out.println(data.getName());
            employee.setName(data.getName());
            employee.setEmail(data.getEmail());
            employee.setGender(data.getGender());
            employee.setBirthday(LocalDate.now());
            employee.setSalary(data.getSalary());
            employee.setAuthority(data.getAuthority());
            employee.setDepartment(data.getDepartment());
            employee.setPosition(data.getPosition());
            employee.setWorkType(data.getAttendance());
            employee.setInduction(data.getInduction());
            employee.setTransferVocations((byte) 0);

            //密码
            employee.setPassword(BCrypt.hashpw("123ABCabc",BCrypt.gensalt()));
            employeesMapper.insert(employee);
            return;
        }
        throw new ServiceException(EMAIL_CONFLICT);
    }
}
