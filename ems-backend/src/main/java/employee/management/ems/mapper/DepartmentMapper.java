package employee.management.ems.mapper;

import employee.management.ems.dto.DepartmentDto;
import employee.management.ems.dto.EmployeeDto;
import employee.management.ems.entity.Department;
import employee.management.ems.entity.Employee;

public class DepartmentMapper {
    public static DepartmentDto mapToDepartmentDto(Department department){
        return  new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription()
        );
    }

    public static Department mapToDepartment(DepartmentDto departmentDto){
        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription()
        );
    }
}
