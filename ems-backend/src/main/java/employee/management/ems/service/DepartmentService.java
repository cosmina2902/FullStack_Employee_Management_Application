package employee.management.ems.service;

import employee.management.ems.dto.DepartmentDto;
import employee.management.ems.dto.EmployeeDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepById(Long depId);

    List<DepartmentDto> getAllDeps();

    DepartmentDto updateDep(Long depId, DepartmentDto departmentDto);

    String deleteDep (Long depId);
}
