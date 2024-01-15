package baseline.version3.springboot.pageAdmin.repository.querydsl;

import baseline.version3.springboot.common.util.QueryDslNullableUtil;
import baseline.version3.springboot.entity.pageAdmin.QParentPage;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageResponse;
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
                                parentPage.registerDate,
                                parentPage.lastModifyDate
                        )
                )
                .from(parentPage)
                .fetch();
    }

    public Optional<ParentPageResponse.Response> selectOne(ParentPageRequest.RequestDynamicQuery requestDynamicQuery){
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
                                        parentPage.registerDate,
                                        parentPage.lastModifyDate
                                )
                        )
                        .from(parentPage)
                        .where(
                                queryDslNullableUtil.eq(parentPage.parentPageRootPath, requestDynamicQuery.getParentPageRootPath()),
                                queryDslNullableUtil.eq(parentPage.parentPageId, requestDynamicQuery.getParentPageId()),
                                queryDslNullableUtil.ne(parentPage.parentPageId, requestDynamicQuery.getNotParentPageId())
                        )
                        .fetchOne()
        );
    }

    public Optional<ParentPageResponse.Response> selectOneById(Long id){
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
                                        parentPage.registerDate,
                                        parentPage.lastModifyDate
                                )
                        )
                        .from(parentPage)
                        .where(parentPage.parentPageId.eq(id))
                        .fetchOne()
        );
    }
}
