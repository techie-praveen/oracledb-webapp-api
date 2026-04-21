package oracledb.webapp.api.dto;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class EmployeeDTO {

    private Long employeeId;
    private String employeeName;
    private String designation;
    private String status;
    private String departmentName;


    }
