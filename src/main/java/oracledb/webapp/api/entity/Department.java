package oracledb.webapp.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_seq")
    @SequenceGenerator(name = "dept_seq", sequenceName = "DEPT_SEQ", allocationSize = 1)
    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "dept_name", nullable = false, length = 100)
    private String deptName;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "budget")
    private Double budget;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees;
}

