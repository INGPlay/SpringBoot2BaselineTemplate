package baseline.version3.springboot.ipAccess.domain;

import baseline.version3.springboot.ipAccess.entity.IpAccess;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,    // "spring"
        injectionStrategy = InjectionStrategy.FIELD
)
public interface IpAccessMapper {

    IpAccess requestInsert(IpAccessRequest.Request request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void requestUpdate(IpAccessRequest.RequestUpdate request, @MappingTarget IpAccess ipAccess);

    IpAccessResponse.Response toResponse(IpAccess ipAccess);
}
