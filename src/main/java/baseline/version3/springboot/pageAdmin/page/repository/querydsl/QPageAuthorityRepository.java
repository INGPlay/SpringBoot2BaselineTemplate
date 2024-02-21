package baseline.version3.springboot.pageAdmin.page.repository.querydsl;

import baseline.version3.springboot.common.util.QueryDslNullableUtil;
import baseline.version3.springboot.pageAdmin.page.domain.pageAuthority.PageAuthorityRequest;
import baseline.version3.springboot.pageAdmin.page.domain.pageAuthority.PageAuthorityResponse;
import baseline.version3.springboot.pageAdmin.page.entity.QPageAuthority;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QPageAuthorityRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QueryDslNullableUtil queryDslNullableUtil;

    private QPageAuthority pageAuthority = QPageAuthority.pageAuthority;

    public List<PageAuthorityResponse.Response> selectList(){
        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                PageAuthorityResponse.Response.class,
                                pageAuthority.pageAuthorityId,
                                pageAuthority.pageAuthorityCode,
                                pageAuthority.pageAuthorityName,
                                pageAuthority.pageAuthorityDescription,
                                pageAuthority.registerDate,
                                pageAuthority.lastModifyDate
                        )
                )
                .from(pageAuthority)
                .fetch();
    }

    public Optional<PageAuthorityResponse.Response> selectOne(PageAuthorityRequest.RequestDynamicQueryOne requestDynamicQueryOne){
        return Optional.ofNullable(
                jpaQueryFactory
                        .select(
                                Projections.constructor(
                                        PageAuthorityResponse.Response.class,
                                        pageAuthority.pageAuthorityId,
                                        pageAuthority.pageAuthorityCode,
                                        pageAuthority.pageAuthorityName,
                                        pageAuthority.pageAuthorityDescription,
                                        pageAuthority.registerDate,
                                        pageAuthority.lastModifyDate
                                )
                        )
                        .from(pageAuthority)
                        .where(
                                queryDslNullableUtil.eq(pageAuthority.pageAuthorityCode, requestDynamicQueryOne.getPageAuthorityCode())
                        )
                        .fetchOne()
        );
    }
}
