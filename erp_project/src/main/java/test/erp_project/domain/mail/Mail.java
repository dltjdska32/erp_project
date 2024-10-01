package test.erp_project.domain.mail;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Mail {

    @Column(name = "mai_num")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailNum;

    @Column(nullable = false)
    private String title;

    private String contents;

    @Column(name = "created_date")
    private LocalDate createdDate;

    private Long mailStoreNum;

    @Column(name = "is_deleted")
    private Boolean isDeleted;


    @ManyToOne
    @JoinColumn(name = "mail_store_num")
    private MailStore mailStore;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mail")
    private List<ReceivedMail> receivedMailList = new ArrayList<>();

}
