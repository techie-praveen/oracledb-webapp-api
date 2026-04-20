package oracledb.webapp.api.service;

import oracledb.webapp.api.entity.Department;
import oracledb.webapp.api.exception.ResourceNotFoundException;
import oracledb.webapp.api.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Create a new department
     */
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    /**
     * Fetch all departments
     */
    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    /**
     * Fetch department by ID
     */
    @Transactional(readOnly = true)
    public Department getDepartmentById(Long deptId) {
        return departmentRepository.findById(deptId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: " + deptId
                        ));
    }

    /**
     * Update department details
     */
    public Department updateDepartment(Long deptId, Department departmentDetails) {
        Department existingDepartment = getDepartmentById(deptId);

        existingDepartment.setDepartmentName(
                departmentDetails.getDepartmentName() != null
                        ? departmentDetails.getDepartmentName()
                        : existingDepartment.getDepartmentName()
        );

        existingDepartment.setLocation(
                departmentDetails.getLocation() != null
                        ? departmentDetails.getLocation()
                        : existingDepartment.getLocation()
        );

        existingDepartment.setBudget(
                departmentDetails.getBudget() != null
                        ? departmentDetails.getBudget()
                        : existingDepartment.getBudget()
        );

        return departmentRepository.save(existingDepartment);
    }

    /**
     * Delete department by ID
     */
    public void deleteDepartment(Long deptId) {
        Department department = getDepartmentById(deptId);
        departmentRepository.delete(department);
    }
}