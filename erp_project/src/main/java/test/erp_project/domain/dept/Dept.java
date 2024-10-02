package test.erp_project.domain.dept;

import jakarta.persistence.*;
import lombok.*;
import test.erp_project.domain.user.User;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_num")
    private Long deptNum;

    @Column(nullable = false, name = "dept_name")
    private String deptName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dept")
    private List<User> user = new ArrayList<>();


    protected Dept() {}

    public Dept(Long deptNum, String deptName) {
        this.deptNum = deptNum;
        this.deptName = deptName;
    }



}
