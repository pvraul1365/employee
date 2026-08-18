package net.javaguides.employee.service;

import net.javaguides.employee.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(Long id);

}
