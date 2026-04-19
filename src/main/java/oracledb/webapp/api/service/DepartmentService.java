package oracledb.webapp.api.service;

import oracledb.webapp.api.entity.Department;
import oracledb.webapp.api.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long deptId) {
        return departmentRepository.findById(deptId);
    }

    public Department updateDepartment(Long deptId, Department departmentDetails) {
        Optional<Department> department = departmentRepository.findById(deptId);
        if (department.isPresent()) {
            Department dept = department.get();
            if (departmentDetails.getDeptName() != null) {
                dept.setDeptName(departmentDetails.getDeptName());
            }
            if (departmentDetails.getLocation() != null) {
                dept.setLocation(departmentDetails.getLocation());
            }
            if (departmentDetails.getBudget() != null) {
                dept.setBudget(departmentDetails.getBudget());
            }
            return departmentRepository.save(dept);
        }
        return null;
    }

    public boolean deleteDepartment(Long deptId) {
        Optional<Department> department = departmentRepository.findById(deptId);
        if (department.isPresent()) {
            departmentRepository.deleteById(deptId);
            return true;
        }
        return false;
    }
}

