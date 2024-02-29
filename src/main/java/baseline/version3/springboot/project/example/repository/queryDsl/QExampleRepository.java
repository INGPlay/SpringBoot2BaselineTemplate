package baseline.version3.springboot.project.example.repository.queryDsl;

import baseline.version3.springboot.common.repository.base.QRepositoryBase;
import baseline.version3.springboot.common.util.QueryDslNullableUtil;
import baseline.version3.springboot.project.example.domain.ExampleMapper;
import baseline.version3.springboot.project.example.domain.ExampleRequest;
import baseline.version3.springboot.project.example.domain.ExampleResponse;
import baseline.version3.springboot.project.example.entity.QExample;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class QExampleRepository extends QRepositoryBase {

    private final QExample example = QExample.example;
    private final ExampleMapper exampleMapper;
    protected QExampleRepository(JPAQueryFactory jpaQueryFactory, QueryDslNullableUtil queryDslNullableUtil, ExampleMapper exampleMapper) {
        super(jpaQueryFactory, queryDslNullableUtil);
        this.exampleMapper = exampleMapper;
    }

    public List<ExampleResponse.Response> selectList(ExampleRequest.RequestDynamicQuery requestDynamicQuery){
        return exampleMapper.toResponse(
                jpaQueryFactory
                .selectFrom(example)
                .fetch()
        );
    }

    public Optional<ExampleResponse.Response> selectOne(ExampleRequest.RequestDynamicQueryOne request){
        return Optional.of(
                exampleMapper.toResponse(
                        jpaQueryFactory
                                .selectFrom(example)
                                .where(
                                        queryDslNullableUtil.eq(example.id, request.getId())
                                )
                                .fetchOne()
                )
        );
    }


}
