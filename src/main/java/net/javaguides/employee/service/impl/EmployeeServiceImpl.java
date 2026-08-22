package net.javaguides.employee.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.javaguides.employee.dto.EmployeeDto;
import net.javaguides.employee.entity.Department;
import net.javaguides.employee.entity.Employee;
import net.javaguides.employee.exception.BadRequestException;
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

    @Override
    public EmployeeDto getEmployeeById(final Long departmentId, final Long employeeId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        if (!employee.getDepartment().getId().equals(department.getId())) {
            throw new BadRequestException("Employee does not belong to department with id: " + departmentId);
        }

        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getEmployeeByDepartmentId(final Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);

        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .toList();
    }

}
