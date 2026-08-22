package net.javaguides.employee;

import net.javaguides.employee.dto.EmployeeDto;
import net.javaguides.employee.entity.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeManagementApplication {

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		// Mapeo Employee -> EmployeeDto: department.id -> departmentId
		modelMapper.typeMap(Employee.class, EmployeeDto.class)
				.addMapping(src -> src.getDepartment().getId(), EmployeeDto::setDepartmentId);
		
		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

}
