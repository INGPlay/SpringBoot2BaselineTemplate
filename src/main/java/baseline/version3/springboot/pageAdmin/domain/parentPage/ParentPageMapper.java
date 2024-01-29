package baseline.version3.springboot.pageAdmin.domain.parentPage;

import baseline.version3.springboot.pageAdmin.repository.PageAuthorityRepository;
import baseline.version3.springboot.pageAdmin.repository.entity.PageAuthority;
import baseline.version3.springboot.pageAdmin.repository.entity.ParentPage;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,    // "spring"
        injectionStrategy = InjectionStrategy.FIELD
)
public abstract class ParentPageMapper {

    @Autowired
    protected PageAuthorityRepository pageAuthorityRepository;

    @Mappings({
            @Mapping(expression = "java( getPageAuthority(requestInsert.getPageAuthorityCode()) )", target = "pageAuthority")
    })
    public abstract ParentPage toInsertEntity(ParentPageRequest.RequestInsert requestInsert);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(expression = "java( getPageAuthority(requestUpdate.getPageAuthorityCode()) )", target = "pageAuthority")
    })
    public abstract void toUpdateEntity(ParentPageRequest.RequestUpdate requestUpdate, @MappingTarget ParentPage parentPage);

    public abstract ParentPageResponse.Response toResponse(ParentPage parentPage);

    // 없으면 없는대로 감
    protected PageAuthority getPageAuthority(String pageAuthorityCode){
        return pageAuthorityRepository.findByPageAuthorityCode(pageAuthorityCode).orElse(null);
    }
}
