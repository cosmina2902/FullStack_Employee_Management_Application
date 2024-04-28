package employee.management.ems.service.impl;

import employee.management.ems.dto.DepartmentDto;
import employee.management.ems.entity.Department;
import employee.management.ems.entity.Employee;
import employee.management.ems.exception.ResourceNotFoundException;
import employee.management.ems.mapper.DepartmentMapper;
import employee.management.ems.mapper.EmployeeMapper;
import employee.management.ems.repository.DepartmentRepository;
import employee.management.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
     private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment= departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepById(Long depId) {
        Department department = departmentRepository.findById(depId)
                .orElseThrow( () -> new ResourceNotFoundException("Department is not exist whith given id: "+ depId));
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDeps() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map( (dep) ->
                DepartmentMapper.mapToDepartmentDto(dep)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDep(Long depId, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(depId)
                .orElseThrow(() -> new ResourceNotFoundException("Department is not exist whith given id: "+ depId));
        department.setDepartmentName((departmentDto.getDepartmentName()));
        department.setDepartmentDescription((departmentDto.getDepartmentDescription()));
        Department updateDep = departmentRepository.save(department);
        return  DepartmentMapper.mapToDepartmentDto(updateDep);
    }

    @Override
    public String deleteDep(Long depId) {
        departmentRepository.deleteById(depId);
        return "Department deleted Succesfully";
    }
}
