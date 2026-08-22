package net.javaguides.employee.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import net.javaguides.employee.dto.EmployeeDto;
import net.javaguides.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmployeeController
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 22/08/2026 - 09:56
 * @since 1.17
 */
@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/{departmentId}/employees")
    public ResponseEntity<EmployeeDto> createEmployee(@PathVariable Long departmentId,
                                               @RequestBody EmployeeDto employeeDto) {
        // Call the service method to create an employee
        EmployeeDto savedEmployee = employeeService.createEmployee(departmentId, employeeDto);

        return ResponseEntity
                .created(URI.create("/api/v1/departments/" + departmentId + "/employees/" + savedEmployee.getId()))
                .body(savedEmployee);
    }

}