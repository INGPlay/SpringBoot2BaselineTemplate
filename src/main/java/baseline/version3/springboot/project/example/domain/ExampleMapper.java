package baseline.version3.springboot.project.example.domain;

import baseline.version3.springboot.entity.Example;
import baseline.version3.springboot.project.example.domain.dto.ExampleDomain;
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
