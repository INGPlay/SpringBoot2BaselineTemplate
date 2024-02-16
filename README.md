# SpringBoot3Baseline

# Tech Stack
## BackEnd
- JDK 17
- Spring Boot 3
- Spring MVC

### Persistence
- Spring Data JPA
- QueryDSL

### Authentication
- Keycloak
- Spring Security

### Cache
- Spring Data Redis
- Spring Cache

### Logging
- log4jdbc

### Object Mapping
- MapStruct
- ModelMapper

### Request API
- RestTemplate

### Etc
- Spring Validation
- Spring AOP


## FrontEnd

### Template Engine
- Thymeleaf

### JavaScript
- Vue.js
- AJAX

### CSS
- BootStrap 5
- UIKit


# 기록
## 계층
- Controller :
    - View : _Controller,
    - REST : _ApiController
- Service : _Sevice
- Domain (MapStruct) : _Mapper
- Repository (Spring Data JPA, QueryDsl) :
    - SpringData : _Repository
    - QueryDsl : _QRepository
- Model :
    - CUD : _Request.RequestInsert, _Request.RequestUpdate, _Request.RequestDelete,
    - 검색 : _Request.RequestDynamicQuery, _Request.RequestDynamicQueryOne
    - 반환 : _Response.Response
    - 타입 : _Sub.__Enum
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

## 동적 검색 모델 객체(ex - RequestDynamicQuery)는 생성자보다 빌더(Builder) 패턴을 쓰는 게 좋아보인다
- 검색 모델은 MapStruct를 통하지 않고, 파라미터를 유동적으로 제한할 필요성이 있기 때문... (+ 가독성)
- 근데 하나만 가져올 때 사용하는 검색 모델 객체(ex - RequestDynamicQueryOne)은 어떤 값이 들어갈지 생성자를 사용하거나 하는 것이 좋을 것 같다.

## 반환 객체(ex - Response)는 record 를 사용하는 것이 좋은 것 같다.
- VO를 쉽게 구성할 수 있다. 들어오는 값 검증도 쉽다.
- 단점은 모든 필드가 파라미터로 들어가는 생성자가 무조건 생겨서 필드를 숨길 수 없어서 커스텀이 불편한 감이 있음.

## 404나 500 같은 HttpStatus는 ErrorController로 잡는 게 좋은 것 같다
- HttpStatus는 기본적으로 Exception으로 잡히지 않고, 잡는다 하더라도 여러 설정이 필요함
