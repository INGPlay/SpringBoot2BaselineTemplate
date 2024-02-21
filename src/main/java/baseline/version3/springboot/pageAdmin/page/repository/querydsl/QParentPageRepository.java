package baseline.version3.springboot.pageAdmin.page.repository.querydsl;

import baseline.version3.springboot.common.util.QueryDslNullableUtil;
import baseline.version3.springboot.pageAdmin.page.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.page.domain.parentPage.ParentPageResponse;
import baseline.version3.springboot.pageAdmin.page.entity.QPageAuthority;
import baseline.version3.springboot.pageAdmin.page.entity.QParentPage;
import baseline.version3.springboot.pageAdmin.page.entity.QSubPage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QParentPageRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QueryDslNullableUtil queryDslNullableUtil;

    private QParentPage parentPage = QParentPage.parentPage;
    private QSubPage subPage = QSubPage.subPage;

    private QPageAuthority pageAuthority = QPageAuthority.pageAuthority;

    public List<ParentPageResponse.Response> selectList(){

        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                ParentPageResponse.Response.class,
                                parentPage.parentPageId,
                                parentPage.parentPageTitle,
                                parentPage.parentPageDescription,
                                parentPage.parentPageRootPath,
                                subPage.count().coalesce(0L).as("countSubPageList"),
                                pageAuthority.pageAuthorityCode,
                                pageAuthority.pageAuthorityName,
                                parentPage.registerDate,
                                parentPage.lastModifyDate
                        )
                )
                .from(parentPage)
                .leftJoin(parentPage.pageAuthority, pageAuthority)
                .leftJoin(parentPage.subPageList, subPage)
                .groupBy(parentPage.parentPageId)
                .fetch();
    }

    public Optional<ParentPageResponse.Response> selectOne(ParentPageRequest.RequestDynamicQueryOne requestDynamicQueryOne){
        return Optional.ofNullable(
                jpaQueryFactory
                        .select(
                                Projections.constructor(
                                        ParentPageResponse.Response.class,
                                        parentPage.parentPageId,
                                        parentPage.parentPageTitle,
                                        parentPage.parentPageDescription,
                                        parentPage.parentPageRootPath,
                                        subPage.count().coalesce(0L).as("countSubPageList"),
                                        pageAuthority.pageAuthorityCode,
                                        pageAuthority.pageAuthorityName,
                                        parentPage.registerDate,
                                        parentPage.lastModifyDate
                                )
                        )
                        .from(parentPage)
                        .leftJoin(parentPage.pageAuthority, pageAuthority)
                        .leftJoin(parentPage.subPageList, subPage)
                        .where(
                                queryDslNullableUtil.eq(parentPage.parentPageRootPath, requestDynamicQueryOne.getParentPageRootPath()),
                                queryDslNullableUtil.eq(parentPage.parentPageId, requestDynamicQueryOne.getParentPageId())
                        )
                        .groupBy(parentPage.parentPageId)
                        .fetchOne()
        );
    }
}
