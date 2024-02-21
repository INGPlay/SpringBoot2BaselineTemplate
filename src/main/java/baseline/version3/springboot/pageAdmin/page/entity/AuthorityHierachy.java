package baseline.version3.springboot.pageAdmin.page.entity;

import baseline.version3.springboot.common.entity.base.DateTimeBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/** 권한 계층 | authority_hierachy */
@Entity
@Table(name = "page_admin_authority_hierachy")
@Getter @Setter
public class AuthorityHierachy extends DateTimeBase {

    // 권한 계층 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_hierachy_id" ,nullable = false)
    private Long authorityHierachyId;

    // 권한 계층 상위 code
    @Column(name = "authority_hierachy_parent_code" ,nullable = false ,length = 64)
    private String authorityHierachyParentCode;

    // 권한 계층 하위 code
    @Column(name = "authority_hierachy_child_code" ,nullable = false ,length = 64)
    private String authorityHierachyChildCode;
}
