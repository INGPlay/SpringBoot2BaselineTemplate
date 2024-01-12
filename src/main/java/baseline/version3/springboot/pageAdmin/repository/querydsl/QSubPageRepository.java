package baseline.version3.springboot.pageAdmin.repository.querydsl;

import baseline.version3.springboot.entity.pageAdmin.QPageAuthorityCondition;
import baseline.version3.springboot.entity.pageAdmin.QParentPage;
import baseline.version3.springboot.entity.pageAdmin.QSubPage;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QSubPageRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private QParentPage parentPage = QParentPage.parentPage;
    private QSubPage subPage = QSubPage.subPage;
    private QPageAuthorityCondition pageAuthorityCondition = QPageAuthorityCondition.pageAuthorityCondition;

    public List<SubPageResponse.Response> selectList(SubPageRequest.RequestDynamicQuery requestDynamicQuery){
        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                SubPageResponse.Response.class,
                                subPage.subPageId,
                                subPage.subPageTitle,
                                subPage.subPageDescription,
                                subPage.subPagePath,
                                pageAuthorityCondition.pageAuthorityConditionCode,
                                subPage.registerDate,
                                subPage.lastModifyDate
                        )
                )
                .from(pageAuthorityCondition)
                .rightJoin(pageAuthorityCondition.subPage, subPage)
                .join(subPage.parentPage, parentPage)
                .where(
                        parentPage.parentPageId.eq(requestDynamicQuery.getParentPageId())
                )
                .fetch();

    }
}
