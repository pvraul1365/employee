package net.javaguides.employee.dto;

public record EmployeeDto(
    Long id,
    String firstName,
    String lastName,
    String email,
    Long departmentId
) {
}
