package net.javaguides.employee.mapper;

import net.javaguides.employee.dto.DepartmentDto;
import net.javaguides.employee.entity.Department;

/**
 * DepartmentMapper
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 14/08/2026 - 17:37
 * @since 1.17
 */
public class DepartmentMapper {

    // Convert Department JPA Entity into DepartmentDto
    public static DepartmentDto toDepartmentDto(Department department) {
        if (department == null) {
            return null;
        }
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription());
    }

    // Convert DepartmentDto into Department JPA Entity
    public static Department toDepartment(DepartmentDto departmentDto) {
        if (departmentDto == null) {
            return null;
        }
        return new Department(
                departmentDto.id(),
                departmentDto.departmentName(),
                departmentDto.departmentDescription(),
                null);
    }

}
