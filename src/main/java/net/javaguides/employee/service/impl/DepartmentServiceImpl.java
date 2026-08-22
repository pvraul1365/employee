package net.javaguides.employee.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.javaguides.employee.dto.DepartmentDto;
import net.javaguides.employee.entity.Department;
import net.javaguides.employee.exception.ResourceNotFoundException;
import net.javaguides.employee.repository.DepartmentRepository;
import net.javaguides.employee.service.DepartmentService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @Override
    public DepartmentDto createDepartment(final DepartmentDto departmentDto) {

        final Department department = modelMapper.map(departmentDto, Department.class);
        final Department savedDepartment = departmentRepository.save(department);

        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentById(final Long id) {

        final Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {

        return departmentRepository.findAll().stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .toList();
    }

    @Override
    public DepartmentDto updateDepartment(final Long id, final DepartmentDto departmentDto) {

        final Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());

        final Department updatedDepartment = departmentRepository.save(department);

        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

    @Override
    public void deleteDepartment(final Long id) {
        final Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        departmentRepository.delete(department);
    }

}
