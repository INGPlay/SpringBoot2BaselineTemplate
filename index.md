# SpringBoot3Baseline

## 계층
- Controller : _Controller, _ApiController
- Service : _Sevice
- Domain (MapStruct) : _Mapper
- Repository (Spring Data JPA, QueryDsl) : _Repository _QRepository
- Model : _Request.__DTO, _Response.__VO, _Sub.__Enum
- Entity : _

## Entity 연관관계
- 최대한 N:1(@ManyToOne)만 사용, 필요 시 편의메소드와 함께 양방향 관계 사용, 1:N(@OneToMany)만 사용하지 말 것


## 배포 시 DB 시간 맞추기

## Date vs LocalDateTime
- LocalDateTime 가 다 좋은데 온갖 호환 문제가 일어남.(Redis 변환, Controller로 받는 경우 등)
- 모두 대처는 가능하나, 번거로움.

## select 로 하나의 값만 가져오는 함수의 VO의 경우 생성자로 값을 제한하는 것이 좋은 것 같다.
- 다음과 같이 유니크한 값을 가져올 수 있는 파라미터로 묶을 수 있다.
```java

    @Getter
    public static class RequestDynamicQueryOne {
        private Long parentPageId;
        private Long subPageId;
        private String subPagePath;

        // PK의 경우
        public RequestDynamicQueryOne(Long subPageId) {
            this.subPageId = subPageId;
        }

        // parentPageId와 subPagePath 로 조회하면 유니크가 될 때
        public RequestDynamicQueryOne(Long parentPageId, String subPagePath) {
            this.parentPageId = parentPageId;
            this.subPagePath = subPagePath;
        }
    }

```
```java
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
                                        pageAuthority.pageAuthorityCode.coalesce(""),
                                        pageAuthority.pageAuthorityName.coalesce(""),
                                        subPage.registerDate,
                                        subPage.lastModifyDate
                                )
                        )
                        .from(subPage)
                        .join(subPage.parentPage, parentPage)
                        .leftJoin(subPage.pageAuthority, pageAuthority)
                        .where(
                                // Custom Nullable Comparison Util
                                queryDslNullableUtil.eq(parentPage.parentPageId, requestDynamicQueryOne.getParentPageId()),
                                queryDslNullableUtil.eq(subPage.subPageId, requestDynamicQueryOne.getSubPageId()),
                                queryDslNullableUtil.eq(subPage.subPagePath, requestDynamicQueryOne.getSubPagePath())
                        )
                        .fetchOne()
        );
    }
```
