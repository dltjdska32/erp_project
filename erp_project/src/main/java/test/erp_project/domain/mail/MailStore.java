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
    @Column(name = "mail_store_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailStoreNum;

    @Enumerated(EnumType.STRING)
    private MailType mailType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private User user;


}
