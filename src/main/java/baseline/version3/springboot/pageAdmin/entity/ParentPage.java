package baseline.version3.springboot.pageAdmin.entity;

import baseline.version3.springboot.common.entity.base.DateTimeBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/** 상위 페이지 | parent_page */
@Entity
@Table(name = "page_admin_parent_page")
@Getter @Setter
public class ParentPage extends DateTimeBase {

    // 상위 사이트 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_page_id" ,nullable = false)
    private Long parentPageId;

    // 제목
    @Column(name = "parent_page_title" ,nullable = false ,length = 128)
    private String parentPageTitle;

    // 설명
    @Column(name = "parent_page_description" ,length = 1024)
    private String parentPageDescription;

    // 루트 경로
    @Column(name = "parent_page_root_path", nullable = false, length = 128, unique = true)
    private String parentPageRootPath;

    @OneToMany(mappedBy = "parentPage", orphanRemoval = true)
    private List<SubPage> subPageList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_authority_page_authority_id")
    private PageAuthority pageAuthority;
}
