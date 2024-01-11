package baseline.version3.springboot.pageAdmin.repository.querydsl;

import baseline.version3.springboot.entity.pageAdmin.QParentPage;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QParentPageRepository {
    private final JPAQueryFactory jpaQueryFactory;

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
                                eqParentPageRootPath(requestDynamicQuery.getParentPageRootPath()),
                                eqParentPageId(requestDynamicQuery.getParentPageId()),
                                neParentPageId(requestDynamicQuery.getNotParentPageId())
                        )
                        .fetchOne()
        );
    }

    private BooleanExpression eqParentPageRootPath(String rootPath){
        if (rootPath == null || rootPath.isEmpty()){
            return null;
        }

        return parentPage.parentPageRootPath.eq(rootPath);
    }

    private BooleanExpression eqParentPageId(Long id){
        if (id == null){
            return null;
        }

        return parentPage.parentPageId.eq(id);
    }

    private BooleanExpression neParentPageId(Long id){
        if (id == null){
            return null;
        }

        return parentPage.parentPageId.ne(id);
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
