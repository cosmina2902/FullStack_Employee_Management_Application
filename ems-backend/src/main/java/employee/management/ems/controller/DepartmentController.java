package employee.management.ems.controller;

import employee.management.ems.dto.DepartmentDto;
import employee.management.ems.dto.EmployeeDto;
import employee.management.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.createDepartment(departmentDto);
        return  new ResponseEntity<>(savedDepartment, HttpStatus.OK);
    }

    @GetMapping("department/{id}")
    public ResponseEntity<DepartmentDto> getDepById(@PathVariable("id") Long depId){
        DepartmentDto departmentDto = departmentService.getDepById(depId);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>>getDeps(){
        List<DepartmentDto> departments = departmentService.getAllDeps();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
    @PutMapping("department/{id}/update")
    public ResponseEntity<DepartmentDto> updateDep(@PathVariable("id") Long depId,
                                                   @RequestBody DepartmentDto departmentDto){
        DepartmentDto updatedDep = departmentService.updateDep(depId, departmentDto);
        return new ResponseEntity<>(updatedDep, HttpStatus.OK);

    }

    @DeleteMapping("department/{id}/delete")
    public ResponseEntity<String> deleteDepbyId(@PathVariable("id") Long id){
        String message = departmentService.deleteDep(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
