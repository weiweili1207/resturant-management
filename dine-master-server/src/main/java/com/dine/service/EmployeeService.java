package com.dine.service;

import com.dine.dto.EmployeeDTO;
import com.dine.dto.EmployeeLoginDTO;
import com.dine.dto.EmployeePageQueryDTO;
import com.dine.entity.Employee;
import com.dine.result.PageResult;

public interface EmployeeService {

    /**
     * employee login
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * add new employee
     * @param employeeDTO
     */
    void addEmployee(EmployeeDTO employeeDTO);

    /**
     * employee page query
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void enaleOrDisable(Integer status, Long id);
}
