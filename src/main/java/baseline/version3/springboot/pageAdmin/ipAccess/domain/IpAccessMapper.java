package baseline.version3.springboot.pageAdmin.ipAccess.domain;

import baseline.version3.springboot.pageAdmin.ipAccess.entity.IpAccess;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,    // "spring"
        injectionStrategy = InjectionStrategy.FIELD
)
public abstract class IpAccessMapper {

    public abstract IpAccess requestInsert(IpAccessRequest.Request request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void requestUpdate(IpAccessRequest.RequestUpdate request, @MappingTarget IpAccess ipAccess);

    public abstract IpAccessResponse.Response toResponse(IpAccess ipAccess);
}
