package com.neusoft.ehr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.dto.LoginDto;
import com.neusoft.ehr.entity.dto.UpdatePasswordDto;
import com.neusoft.ehr.entity.po.EmployeesPo;
import com.neusoft.ehr.entity.vo.LoginVo;
import com.neusoft.ehr.service.IEmployeeService;
import com.neusoft.ehr.mapper.EmployeesMapper;
import com.neusoft.ehr.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

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
        String hashPassword = employeesMapper.selectOne(queryWrapper.eq("name", loginVo.getName())).getPassword();

        //再判断输入的原密码是否正确
        String oldPassword = data.getOldPassword();

//        BCrypt.checkpw(oldPassword,hashPassword){
//
//        }

        EmployeesPo employeesPo = employeesMapper.selectOne(queryWrapper.eq("password", oldPassword));
    }
}
