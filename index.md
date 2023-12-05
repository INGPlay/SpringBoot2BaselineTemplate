# SpringBoot2Baseline

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