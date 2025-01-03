package com.dine.controller.admin;

import com.dine.constant.JwtClaimsConstant;
import com.dine.dto.EmployeeDTO;
import com.dine.dto.EmployeeLoginDTO;
import com.dine.dto.EmployeePageQueryDTO;
import com.dine.entity.Employee;
import com.dine.properties.JwtProperties;
import com.dine.result.PageResult;
import com.dine.result.Result;
import com.dine.service.EmployeeService;
import com.dine.utils.JwtUtil;
import com.dine.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * employee management
  */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "Employee-related API")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * log in
     */
    @PostMapping("/login")
    @ApiOperation(value = "Employee login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("Employee login：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //After login successfully, generate a JWT(JSON Web Token)
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * log out
     */
    @PostMapping("/logout")
    @ApiOperation(value = "Employee logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * add new employee
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation("add new employee")
    public Result addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("add new employee: {}", employeeDTO);
        employeeService.addEmployee(employeeDTO);
        return Result.success();
    }

    /**
     * employee pagination search query
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Employee pagination query")
    public Result<PageResult> pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("Employee pagination, parameter: {}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * enable or disable employee account
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Enable or disable employee account")
    public Result enableOrDisable(@PathVariable Integer status, Long id) {
        log.info("Enable or disable employee account: {},{}", status, id);
        employeeService.enaleOrDisable(status, id);
        return Result.success();
    }

    /**
     * get employee by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Get employee by id")
    public Result<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return Result.success(employee);
    }

    /**
     * update employee information
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("update employee information")
    public Result updateEmployeeInfo(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Update employee information: {}", employeeDTO);
        employeeService.updateEmployeeInfo(employeeDTO);
        return Result.success();
    }
}

