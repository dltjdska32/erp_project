package test.erp_project.domain.user;

import jakarta.persistence.*;
import lombok.*;

import test.erp_project.domain.board.Board;
import test.erp_project.domain.dept.Dept;
import test.erp_project.domain.leave_log.LeaveLog;
import test.erp_project.domain.mail.MailStore;
import test.erp_project.domain.mail.ReceivedMail;
import test.erp_project.domain.position.Position;
import test.erp_project.domain.salary_log.SalaryLog;
import test.erp_project.domain.work_log.WorkLog;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNum;

    @Column(unique = true, nullable = false, name="user_id")
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "remained_leave")
    private int remainedLeave;

    @ManyToOne
    @JoinColumn(name = "position_num")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "dept_num")
    private Dept dept;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<LeaveLog> leaveLogs = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<MailStore> mailStores = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ReceivedMail> receivedMails = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List <SalaryLog> salaryLogs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List <WorkLog> workLogs = new ArrayList<>();

}
