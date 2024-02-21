package baseline.version3.springboot.pageAdmin.page.domain.pageAuthority;

import baseline.version3.springboot.pageAdmin.page.entity.PageAuthority;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,    // "spring"
        injectionStrategy = InjectionStrategy.FIELD
)
public abstract class PageAuthorityMapper {
    public abstract PageAuthority toInsertEntity(PageAuthorityRequest.RequestInsert requestInsert);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdateEntity(PageAuthorityRequest.RequestUpdate requestUpdate, @MappingTarget PageAuthority pageAuthority);

    public abstract PageAuthorityResponse.Response toResponse(PageAuthority pageAuthority);
}
