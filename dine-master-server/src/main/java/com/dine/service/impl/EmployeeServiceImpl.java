package com.dine.service.impl;

import com.dine.constant.MessageConstant;
import com.dine.constant.PasswordConstant;
import com.dine.constant.StatusConstant;
import com.dine.context.BaseContext;
import com.dine.dto.EmployeeDTO;
import com.dine.dto.EmployeeLoginDTO;
import com.dine.dto.EmployeePageQueryDTO;
import com.dine.entity.Employee;
import com.dine.exception.AccountLockedException;
import com.dine.exception.AccountNotFoundException;
import com.dine.exception.PasswordErrorException;
import com.dine.mapper.EmployeeMapper;
import com.dine.result.PageResult;
import com.dine.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * employee login
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、select employee by name
        Employee employee = employeeMapper.getByUsername(username);

        //2、Exception handling
        if (employee == null) {
            //account not exist
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //compare password
        //The MD5 hash of the response from front end as a hexadecimal string
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //password not match
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (Objects.equals(employee.getStatus(), StatusConstant.DISABLE)) {
            //account locked
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、return object
        return employee;
    }
    /**
     * add new employee
     * @param employeeDTO
     */
    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        //object property copy: employee and employeeDTO have same property
        BeanUtils.copyProperties(employeeDTO, employee);

        //set employee account status default 1: enable 0 : disable
        employee.setStatus(StatusConstant.ENABLE);

        //set employee password, default 123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //set create time and update time
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //set create user and update user
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.insertEmployee(employee);
    }

    /**
     * employee page query
     * @param employeePageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        //select * from employee limit(0, 10)
        //start pagination
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);

        long totalPage = page.getTotal();
        List<Employee> records = page.getResult();
        return new PageResult(totalPage, records);
    }

    /**
     * enable or disable employee account
     * @param status
     * @param id
     */
    @Override
    public void enaleOrDisable(Integer status, Long id) {
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();
        employeeMapper.update(employee);
    }

    /**
     * get employee by id
     * @param id
     * @return
     */
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeMapper.getEmployeeById(id);
    }

    /**
     * update employee information
     * @param employeeDTO
     */
    @Override
    public void updateEmployeeInfo(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());
        employeeMapper.update(employee);
    }

}
