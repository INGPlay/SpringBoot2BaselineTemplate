package baseline.version3.springboot.entity.pageAdmin;

import baseline.version3.springboot.entity.base.DateTimeBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/** 권한 | page_authority_condition */
@Entity
@Table(name = "page_admin_page_authority_condition")
@Getter @Setter
public class PageAuthorityCondition extends DateTimeBase {

    // 권한 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_authority_condition_id" ,nullable = false)
    private Long pageAuthorityConditionId;

    // 권한 코드
    @Column(name = "page_authority_condition_code" ,nullable = false ,length = 64)
    private String pageAuthorityConditionCode;

    // 권한 이름
    @Column(name = "page_authority_condition_name" ,nullable = false ,length = 128)
    private String pageAuthorityConditionName;

    // 권한 묘사
    @Column(name = "page_authority_condition_description" ,length = 256)
    private String pageAuthorityConditionDescription;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "sub_page_sub_page_id")
    private SubPage subPage;

}