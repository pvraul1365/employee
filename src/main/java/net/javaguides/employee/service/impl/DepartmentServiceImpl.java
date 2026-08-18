package net.javaguides.employee.service.impl;

import lombok.RequiredArgsConstructor;
import net.javaguides.employee.dto.DepartmentDto;
import net.javaguides.employee.entity.Department;
import net.javaguides.employee.exception.ResourceNotFoundException;
import net.javaguides.employee.mapper.DepartmentMapper;
import net.javaguides.employee.repository.DepartmentRepository;
import net.javaguides.employee.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * DepartmentServiceImpl
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 14/08/2026 - 14:32
 * @since 1.17
 */
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(final DepartmentDto departmentDto) {

        final Department department = DepartmentMapper.toDepartment(departmentDto);
        final Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.toDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(final Long id) {

        final Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        return DepartmentMapper.toDepartmentDto(department);
    }

}
