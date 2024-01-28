package baseline.version3.springboot.pageAdmin.domain.subPage;

import baseline.version3.springboot.pageAdmin.repository.PageAuthorityRepository;
import baseline.version3.springboot.pageAdmin.repository.entity.PageAuthority;
import baseline.version3.springboot.pageAdmin.repository.entity.ParentPage;
import baseline.version3.springboot.pageAdmin.repository.entity.SubPage;
import baseline.version3.springboot.controllerAdvice.exception.ServiceLayerException;
import baseline.version3.springboot.controllerAdvice.subType.ServiceException;
import baseline.version3.springboot.pageAdmin.repository.ParentPageRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,    // "spring"
        injectionStrategy = InjectionStrategy.FIELD
)
public abstract class SubPageMapper {

    @Autowired
    protected ParentPageRepository parentPageRepository;
    @Autowired
    protected PageAuthorityRepository pageAuthorityRepository;

    @Mappings({
            @Mapping(expression = "java( getParentPage(requestInsert) )", target = "parentPage"),
            @Mapping(expression = "java( getPageAuthority(requestInsert.getPageAuthorityCode()) )", target = "pageAuthority")
//            @Mapping(expression = "java( getPageAuthority(requestInsert) )", target = "pageAuthorityCondition")
    })
    public abstract SubPage toInsertEntity(SubPageRequest.RequestInsert requestInsert);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(expression = "java( getPageAuthority(requestUpdate.getPageAuthorityCode()) )", target = "pageAuthority")
    })
    public abstract void toUpdateEntity(SubPageRequest.RequestUpdate requestUpdate, @MappingTarget SubPage subPage);

    public abstract SubPageResponse.Response toResponse(SubPage subPage);

    protected ParentPage getParentPage(SubPageRequest.RequestInsert requestInsert){
        return parentPageRepository.findById(requestInsert.getParentPageId()).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );
    }

    // 없으면 없는대로 감
    protected PageAuthority getPageAuthority(String pageAuthorityCode){
        return pageAuthorityRepository.findByPageAuthorityCode(pageAuthorityCode).orElse(null);
    }
}
