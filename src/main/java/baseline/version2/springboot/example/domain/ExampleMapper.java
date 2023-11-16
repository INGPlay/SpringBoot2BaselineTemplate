package baseline.version2.springboot.example.domain;

import baseline.version2.springboot.common.entity.Example;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExampleMapper {
    ExampleMapper INSTANCE = Mappers.getMapper( ExampleMapper.class );

    @Mapping(source = "editExampleDTO.id", target="id", ignore=true)
    Example requestInsert(EditExampleDTO editExampleDTO);

    Example requestUpdate(EditExampleDTO editExampleDTO);

    ResponseVO entityToResponse(Example example);
}
