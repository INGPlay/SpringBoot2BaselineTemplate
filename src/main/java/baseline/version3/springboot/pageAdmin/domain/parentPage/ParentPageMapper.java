package baseline.version3.springboot.pageAdmin.domain.parentPage;

import baseline.version3.springboot.pageAdmin.repository.entity.ParentPage;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,    // "spring"
        injectionStrategy = InjectionStrategy.FIELD
)
public abstract class ParentPageMapper {

    public abstract ParentPage toInsertEntity(ParentPageRequest.RequestInsert requestInsert);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdateEntity(ParentPageRequest.RequestUpdate requestUpdate, @MappingTarget ParentPage parentPage);

    public abstract ParentPageResponse.Response toResponse(ParentPage parentPage);
}
