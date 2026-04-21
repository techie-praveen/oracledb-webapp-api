package oracledb.webapp.api.service;

import oracledb.webapp.api.entity.Employee;
import oracledb.webapp.api.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public Page<Employee> getEmployees(String search, int page, int size) {

        if (search == null || search.isEmpty()) {
            return repository.findAll(PageRequest.of(page, size));
        }

        return repository.findByEmployeeNameContainingIgnoreCase(
                search,
                PageRequest.of(page, size)
        );


    }

}
