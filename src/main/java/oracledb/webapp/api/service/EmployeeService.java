package oracledb.webapp.api.service;

import oracledb.webapp.api.entity.Employee;
import oracledb.webapp.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long empId) {
        return employeeRepository.findById(empId);
    }

    public List<Employee> getEmployeesByDepartment(Long deptId) {
        return employeeRepository.findByDepartmentDeptId(deptId);
    }

    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public Employee updateEmployee(Long empId, Employee employeeDetails) {
        Optional<Employee> employee = employeeRepository.findById(empId);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            if (employeeDetails.getEmpName() != null) {
                emp.setEmpName(employeeDetails.getEmpName());
            }
            if (employeeDetails.getEmail() != null) {
                emp.setEmail(employeeDetails.getEmail());
            }
            if (employeeDetails.getPhone() != null) {
                emp.setPhone(employeeDetails.getPhone());
            }
            if (employeeDetails.getSalary() != null) {
                emp.setSalary(employeeDetails.getSalary());
            }
            if (employeeDetails.getHireDate() != null) {
                emp.setHireDate(employeeDetails.getHireDate());
            }
            if (employeeDetails.getJobTitle() != null) {
                emp.setJobTitle(employeeDetails.getJobTitle());
            }
            if (employeeDetails.getDepartment() != null) {
                emp.setDepartment(employeeDetails.getDepartment());
            }
            return employeeRepository.save(emp);
        }
        return null;
    }

    public boolean deleteEmployee(Long empId) {
        Optional<Employee> employee = employeeRepository.findById(empId);
        if (employee.isPresent()) {
            employeeRepository.deleteById(empId);
            return true;
        }
        return false;
    }
}

