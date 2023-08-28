package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工信息
     * @author zyb
     * @date 2023/8/23 19:09
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询用户信息
     * @author zyb
     * @date 2023/8/24 20:46
     * @param employeePageQueryDTO
     * @return PageResult
     */
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用员工账号
     * @author zyb
     * @date 2023/8/28 15:39
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     *
     * @author zyb
     * @date 2023/8/28 16:14
     * @param id
     * @return Employee
     */
    Employee getById(Long id);

    /**
     *
     * @author zyb
     * @date 2023/8/28 16:20
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
