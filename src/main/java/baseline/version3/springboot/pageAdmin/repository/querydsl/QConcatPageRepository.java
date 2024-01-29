package baseline.version3.springboot.pageAdmin.repository.querydsl;

import baseline.version3.springboot.common.util.QueryDslNullableUtil;
import baseline.version3.springboot.pageAdmin.domain.concatPage.ConcatPageRequest;
import baseline.version3.springboot.pageAdmin.domain.concatPage.ConcatPageResponse;
import baseline.version3.springboot.pageAdmin.repository.entity.QPageAuthority;
import baseline.version3.springboot.pageAdmin.repository.entity.QParentPage;
import baseline.version3.springboot.pageAdmin.repository.entity.QSubPage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QConcatPageRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QueryDslNullableUtil queryDslNullableUtil;

    private QParentPage parentPage = QParentPage.parentPage;
    private QSubPage subPage = QSubPage.subPage;
    private QPageAuthority pageAuthority = QPageAuthority.pageAuthority;

    public List<ConcatPageResponse.Response> selectList(){
        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                ConcatPageResponse.Response.class,
                                parentPage.parentPageId,
                                parentPage.parentPageTitle,
                                parentPage.parentPageDescription,
                                parentPage.parentPageRootPath,

                                pageAuthority.pageAuthorityCode,
                                pageAuthority.pageAuthorityName,
                                pageAuthority.pageAuthorityDescription,

                                subPage.subPageId,
                                subPage.subPageTitle,
                                subPage.subPageDescription,
                                subPage.subPagePath,
                                subPage.registerDate,
                                subPage.lastModifyDate
                        )
                )
                .from(parentPage)
                .join(parentPage.pageAuthority, pageAuthority)
                .rightJoin(parentPage.subPageList, subPage)
                .fetch();
    }
}
