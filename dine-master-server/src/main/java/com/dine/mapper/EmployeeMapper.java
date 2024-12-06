package com.dine.mapper;

import com.dine.annotation.AutoFill;
import com.dine.dto.EmployeePageQueryDTO;
import com.dine.entity.Employee;
import com.dine.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * Search employee by name
     */
    @Select("SELECT * FROM employee WHERE username = #{username}")
    Employee getByUsername(String username);

    /**
     * insert employee
     * @param employee
     */
    @Insert("INSERT INTO employee (name, username, password, phone, gender, id_number, create_time, update_time, create_user, update_user, status)"
            + "VALUES " +
            "(#{name}, #{username}, #{password}, #{phone}, #{gender}, #{idNumber}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser}, #{status})")
    @AutoFill(value = OperationType.INSERT)
    void insertEmployee(Employee employee);

    /**
     * employee pagination
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * update property by using key
     * @param employee
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Employee employee);

    /**
     * get employee by id
     * @param id
     * @return
     */
    @Select("SELECT * FROM employee WHERE id = #{id}")
    Employee getEmployeeById(Long id);
}
