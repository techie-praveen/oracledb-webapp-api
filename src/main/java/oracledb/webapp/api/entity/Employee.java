package oracledb.webapp.api.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq", sequenceName = "EMP_SEQ", allocationSize = 1)
    @Column(name = "emp_id")
    private Long empId;

    @Column(name = "emp_name", nullable = false, length = 100)
    private String empName;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "hire_date")
    private String hireDate;

    @Column(name = "job_title", length = 100)
    private String jobTitle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id", nullable = false)
    @JsonBackReference
    private Department department;
}

