package test.erp_project.domain.position;


import jakarta.persistence.*;
import lombok.*;
import test.erp_project.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_num")
    private Long positionNum;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "leave_day")
    private int leaveDay;

    @Column(name = "basic_salary")
    private int basicSalary;

    @Column(name = "leave_pay")
    private int leavePay;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "position")
    private List<User> users = new ArrayList<User>();


}
