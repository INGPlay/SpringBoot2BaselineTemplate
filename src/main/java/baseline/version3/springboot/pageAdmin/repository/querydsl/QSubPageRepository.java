package baseline.version3.springboot.pageAdmin.repository.querydsl;

import baseline.version3.springboot.common.util.QueryDslNullableUtil;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageResponse;
import baseline.version3.springboot.pageAdmin.repository.PageAuthorityRepository;
import baseline.version3.springboot.pageAdmin.repository.entity.QPageAuthority;
import baseline.version3.springboot.pageAdmin.repository.entity.QParentPage;
import baseline.version3.springboot.pageAdmin.repository.entity.QSubPage;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QSubPageRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QueryDslNullableUtil queryDslNullableUtil;

    private QParentPage parentPage = QParentPage.parentPage;
    private QSubPage subPage = QSubPage.subPage;
    private QPageAuthority pageAuthority = QPageAuthority.pageAuthority;

    public List<SubPageResponse.Response> selectList(SubPageRequest.RequestDynamicQuery requestDynamicQuery){
        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                SubPageResponse.Response.class,
                                subPage.subPageId,
                                subPage.subPageTitle,
                                subPage.subPageDescription,
                                subPage.subPagePath,
                                subPage.registerDate,
                                subPage.lastModifyDate,
                                parentPage.parentPageId,
                                parentPage.parentPageTitle,
                                parentPage.parentPageRootPath,
                                parentPage.parentPageDescription,
                                pageAuthority.pageAuthorityCode,
                                pageAuthority.pageAuthorityName,
                                Expressions.stringTemplate(
                                        "function('regexp_replace',{0},{1},{2})",
                                        parentPage.parentPageRootPath.concat(subPage.subPagePath), "/+", "/"
                                ).as("concatPagePath")
                        )
                )
                .from(subPage)
                .join(subPage.parentPage, parentPage)
                .leftJoin(parentPage.pageAuthority, pageAuthority)
                .where(
                        queryDslNullableUtil.eq(parentPage.parentPageId, requestDynamicQuery.getParentPageId())
                )
                .fetch();

    }

    public Optional<SubPageResponse.Response> selectOne(SubPageRequest.RequestDynamicQueryOne requestDynamicQueryOne){
        return Optional.ofNullable(
                jpaQueryFactory
                        .select(
                                Projections.constructor(
                                        SubPageResponse.Response.class,
                                        subPage.subPageId,
                                        subPage.subPageTitle,
                                        subPage.subPageDescription,
                                        subPage.subPagePath,
                                        subPage.registerDate,
                                        subPage.lastModifyDate,
                                        parentPage.parentPageId,
                                        parentPage.parentPageTitle,
                                        parentPage.parentPageRootPath,
                                        parentPage.parentPageDescription,
                                        pageAuthority.pageAuthorityCode,
                                        pageAuthority.pageAuthorityName,
                                        Expressions.stringTemplate(
                                                "function('regexp_replace',{0},{1},{2})",
                                                parentPage.parentPageRootPath.concat(subPage.subPagePath), "/+", "/"
                                        ).as("concatPagePath")
                                )
                        )
                        .from(subPage)
                        .join(subPage.parentPage, parentPage)
                        .leftJoin(parentPage.pageAuthority, pageAuthority)
                        .where(
                                queryDslNullableUtil.eq(parentPage.parentPageId, requestDynamicQueryOne.getParentPageId()),
                                queryDslNullableUtil.eq(subPage.subPageId, requestDynamicQueryOne.getSubPageId()),
                                queryDslNullableUtil.eq(subPage.subPagePath, requestDynamicQueryOne.getSubPagePath()),
                                queryDslNullableUtil.eq(
                                        Expressions.stringTemplate(
                                                "function('regexp_replace',{0},{1},{2})",
                                                parentPage.parentPageRootPath.concat(subPage.subPagePath), "/+", "/"
                                        ),
                                        requestDynamicQueryOne.getConcatPagePath()
                                )
                        )
                        .fetchOne()
        );
    }
}
