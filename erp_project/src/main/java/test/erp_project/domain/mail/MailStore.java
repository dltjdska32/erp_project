package test.erp_project.domain.mail;

import jakarta.persistence.*;
import lombok.*;
import test.erp_project.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mail_store")
public class MailStore {
    @Id
    @Column(name = "mail_stoer_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailStoreNum;

    @Enumerated(EnumType.STRING)
    private MailType mailType;


    @ManyToOne
    @JoinColumn(name = "user_num")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mail_store")
    private List<Mail> mails = new ArrayList<>();

}
