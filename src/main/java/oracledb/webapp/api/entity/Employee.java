package oracledb.webapp.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq", sequenceName = "EMP_SEQ", allocationSize = 1)
    private Long id;

    private String name;
    private String email;
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}