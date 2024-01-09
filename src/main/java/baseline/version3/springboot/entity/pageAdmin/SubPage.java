package baseline.version3.springboot.entity.pageAdmin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/** 하위 페이지 | sub_page */
@Entity
@Table(name = "page_admin_sub_page")
@Getter @Setter
public class SubPage {

    // 하위 페이지 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_page_id" ,nullable = false)
    private Integer subPageId;

    // 하위 페이지 제목
    @Column(name = "sub_page_title" ,nullable = false ,length = 128)
    private String subPageTitle;

    // 하위 페이지 묘사
    @Column(name = "sub_page_description" ,length = 1024)
    private String subPageDescription;

    // 페이지 경로
    @Column(name = "sub_page_path" ,nullable = false ,length = 512)
    private String subPagePath;

    @ManyToOne
    @JoinColumn(name = "parent_page_id")
    private ParentPage parentPage;
}
