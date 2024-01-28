package baseline.version3.springboot.pageAdmin.repository.querydsl;

import baseline.version3.springboot.common.util.QueryDslNullableUtil;
import baseline.version3.springboot.pageAdmin.domain.concatPage.ConcatPageResponse;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageResponse;
import baseline.version3.springboot.pageAdmin.repository.entity.QParentPage;
import baseline.version3.springboot.pageAdmin.repository.entity.QSubPage;
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

    public List<ParentPageResponse.Response> selectList(){

        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                ParentPageResponse.Response.class,
                                parentPage.parentPageId,
                                parentPage.parentPageTitle,
                                parentPage.parentPageDescription,
                                parentPage.parentPageRootPath,
                                parentPage.parentPageIndexPath,
                                subPage.count().coalesce(0L).as("countSubPageList"),
                                parentPage.registerDate,
                                parentPage.lastModifyDate
                        )
                )
                .from(parentPage)
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
                                        parentPage.parentPageIndexPath,
                                        subPage.count().coalesce(0L).as("countSubPageList"),
                                        parentPage.registerDate,
                                        parentPage.lastModifyDate
                                )
                        )
                        .from(parentPage)
                        .where(
                                queryDslNullableUtil.eq(parentPage.parentPageRootPath, requestDynamicQueryOne.getParentPageRootPath()),
                                queryDslNullableUtil.eq(parentPage.parentPageId, requestDynamicQueryOne.getParentPageId())
                        )
                        .leftJoin(parentPage.subPageList, subPage)
                        .groupBy(parentPage.parentPageId)
                        .fetchOne()
        );
    }
}
