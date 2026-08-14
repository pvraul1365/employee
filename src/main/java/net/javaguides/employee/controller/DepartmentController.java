package net.javaguides.employee.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import net.javaguides.employee.dto.DepartmentDto;
import net.javaguides.employee.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DepartmentController
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 14/08/2026 - 14:40
 * @since 1.17
 */
@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody final DepartmentDto departmentDto) {
        final DepartmentDto createdDepartment = departmentService.createDepartment(departmentDto);

        return ResponseEntity.created(URI.create("/api/v1/departments/" + createdDepartment.id()))
                .body(createdDepartment);
    }

}
