package net.javaguides.employee.service;

import java.util.List;
import net.javaguides.employee.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(Long departmentId, EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long departmentId, Long employeeId);

    List<EmployeeDto> getEmployeeByDepartmentId(Long departmentId);

    EmployeeDto updateEmployee(Long departmentId, Long employeeId, EmployeeDto employeeDto);
}
