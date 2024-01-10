package baseline.version3.springboot.project.example.domain;

import baseline.version3.springboot.entity.Example;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,    // "spring"
        injectionStrategy = InjectionStrategy.FIELD
)
public interface ExampleMapper {

    @Mapping(source = "editExampleDTO.id", target="id", ignore=true)
    Example requestInsert(ExampleRequest.Request editExampleDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void requestUpdate(ExampleRequest.Request editExampleDTO, @MappingTarget Example example);

    ExampleResponse.Response toResponse(Example example);

    ExampleRequest.Event toEvent(ExampleRequest.Request request);
}
