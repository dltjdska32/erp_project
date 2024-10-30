package test.erp_project.dto.salary_dto;

import lombok.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminSalaryData {
    private String receivedDate;
    private Long salaryNum; //월급번호 PK
    private String name; //월급을 지급받은 직원의이름
    private Long userNum;
    private String deptName;
    private String positionName;
    private int basicSalary; // 기본급








}