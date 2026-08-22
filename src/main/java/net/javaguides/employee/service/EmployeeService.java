package net.javaguides.employee.service;

import net.javaguides.employee.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(Long departmentId, EmployeeDto employeeDto);

}
