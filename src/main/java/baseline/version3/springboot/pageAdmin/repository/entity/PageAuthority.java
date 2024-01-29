package baseline.version3.springboot.pageAdmin.repository.entity;

import baseline.version3.springboot.entity.base.DateTimeBase;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/** 권한 | page_authority_condition */
@Entity
@Table(name = "page_admin_page_authority")
@Getter @Setter
public class PageAuthority extends DateTimeBase {

    // 권한 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_authority_id",nullable = false)
    @Setter(AccessLevel.NONE)
    private Long pageAuthorityId;

    // 권한 코드
    @Column(name = "page_authority_code", length = 64, unique = true)
    private String pageAuthorityCode;

    // 권한 이름
    @Column(name = "page_authority_name", length = 128)
    private String pageAuthorityName;

    // 권한 묘사
    @Column(name = "page_authority_description", length = 256)
    private String pageAuthorityDescription;

    @OneToMany(mappedBy = "pageAuthority", orphanRemoval = true)
    private List<ParentPage> parentPageList = new ArrayList<>();
}