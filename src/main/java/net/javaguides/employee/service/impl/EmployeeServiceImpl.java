package net.javaguides.employee.service.impl;

import lombok.RequiredArgsConstructor;
import net.javaguides.employee.dto.EmployeeDto;
import net.javaguides.employee.entity.Department;
import net.javaguides.employee.entity.Employee;
import net.javaguides.employee.exception.ResourceNotFoundException;
import net.javaguides.employee.repository.DepartmentRepository;
import net.javaguides.employee.repository.EmployeeRepository;
import net.javaguides.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(final Long departmentId, final EmployeeDto employeeDto) {

        final Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new
                        ResourceNotFoundException("Department not found with id: " + departmentId));


        final Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setDepartment(department);

        final Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);

    }

}
