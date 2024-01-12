package baseline.version3.springboot.pageAdmin.domain.subPage;

import baseline.version3.springboot.entity.pageAdmin.ParentPage;
import baseline.version3.springboot.entity.pageAdmin.SubPage;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,    // "spring"
        injectionStrategy = InjectionStrategy.FIELD
)
public abstract class SubPageMapper {

    public abstract SubPage toInsertEntity(SubPageRequest.RequestInsert requestInsert);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdateEntity(SubPageRequest.RequestUpdate requestUpdate, @MappingTarget ParentPage parentPage);

    public abstract SubPageResponse.Response toResponse(ParentPage parentPage);
}
