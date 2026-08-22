package net.javaguides.employee.controller;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.javaguides.employee.dto.EmployeeDto;
import net.javaguides.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<EmployeeDto> createEmployee(@PathVariable final Long departmentId,
                                               @RequestBody final EmployeeDto employeeDto) {
        // Call the service method to create an employee
        final EmployeeDto savedEmployee = employeeService.createEmployee(departmentId, employeeDto);

        return ResponseEntity
                .created(URI.create("/api/v1/departments/" + departmentId + "/employees/" + savedEmployee.getId()))
                .body(savedEmployee);
    }

    @GetMapping("/{departmentId}/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable final Long departmentId,
                                                       @PathVariable final Long employeeId) {
        // Call the service method to get an employee by ID
        final EmployeeDto employeeDto = employeeService.getEmployeeById(departmentId, employeeId);

        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/{departmentId}/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartmentId(@PathVariable final Long departmentId) {
        // Call the service method to get employees by department ID
        final List<EmployeeDto> employeeDtos = employeeService.getEmployeeByDepartmentId(departmentId);

        return ResponseEntity.ok(employeeDtos);
    }

    @PutMapping("/{departmentId}/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable final Long departmentId,
                                                      @PathVariable final Long employeeId,
                                                      @RequestBody final EmployeeDto employeeDto) {
        // Call the service method to update an employee
        final EmployeeDto updatedEmployee = employeeService.updateEmployee(departmentId, employeeId, employeeDto);

        return ResponseEntity.ok(updatedEmployee);
    }
}