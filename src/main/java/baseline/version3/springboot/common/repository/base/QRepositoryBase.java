package baseline.version3.springboot.common.repository.base;

import baseline.version3.springboot.common.util.QueryDslNullableUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;


public abstract class QRepositoryBase {

    protected final JPAQueryFactory jpaQueryFactory;
    protected final QueryDslNullableUtil queryDslNullableUtil;

    protected QRepositoryBase(JPAQueryFactory jpaQueryFactory, QueryDslNullableUtil queryDslNullableUtil) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.queryDslNullableUtil = queryDslNullableUtil;
    }
}
