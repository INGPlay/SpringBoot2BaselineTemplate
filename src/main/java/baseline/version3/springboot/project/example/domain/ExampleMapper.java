package baseline.version3.springboot.project.example.domain;

import baseline.version3.springboot.project.example.entity.Example;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,    // "spring"
        injectionStrategy = InjectionStrategy.FIELD
)
public interface ExampleMapper {

    Example requestInsert(ExampleRequest.RequestInsert requestInsert);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void requestUpdate(ExampleRequest.RequestUpdate requestUpdate, @MappingTarget Example example);

    ExampleResponse.Response toResponse(Example example);

    List<ExampleResponse.Response> toResponse(List<Example> exampleList);
}
