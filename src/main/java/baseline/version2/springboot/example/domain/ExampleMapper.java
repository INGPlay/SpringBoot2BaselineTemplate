package baseline.version2.springboot.example.domain;

import baseline.version2.springboot.common.entity.Example;
import baseline.version2.springboot.example.domain.dto.ExampleDomain;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,    // "spring"
        injectionStrategy = InjectionStrategy.FIELD
)
public interface ExampleMapper {

    @Mapping(source = "editExampleDTO.id", target="id", ignore=true)
    Example requestInsert(ExampleDomain.EditDTO editExampleDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void requestUpdate(ExampleDomain.EditDTO editExampleDTO, @MappingTarget Example example);

    ExampleDomain.ResponseVO entityToResponse(Example example);
}
