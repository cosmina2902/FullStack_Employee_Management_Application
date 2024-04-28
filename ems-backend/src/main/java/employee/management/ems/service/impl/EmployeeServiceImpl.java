package employee.management.ems.service.impl;

import employee.management.ems.dto.EmployeeDto;
import employee.management.ems.entity.Department;
import employee.management.ems.entity.Employee;
import employee.management.ems.exception.ResourceNotFoundException;
import employee.management.ems.mapper.EmployeeMapper;
import employee.management.ems.repository.DepartmentRepository;
import employee.management.ems.repository.EmployeeRepository;
import employee.management.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmpoyee(employeeDto);

        Department department = departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow(()->
                new ResourceNotFoundException("Department is not exist withn id"+ employeeDto.getDepartmentId()));
        employee.setDepartment(department);

        Employee savedEmployee= employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow( () -> new ResourceNotFoundException("Employee is not exist with given id: "+ employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employees = employeeRepository.findAll();
       return employees.stream().map((employee)->
               EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id: "+ employeeId));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Department department = departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow(()->
                new ResourceNotFoundException("Department is not exist within id"+ employeeDto.getDepartmentId()));
        employee.setDepartment(department);

        Employee updateEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updateEmployee);
    }

    @Override
    public String deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return "Employee deleted successfully";
    }
}
