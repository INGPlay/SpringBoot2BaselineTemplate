package baseline.version3.springboot.pageAdmin.domain.subPage;

import baseline.version3.springboot.entity.pageAdmin.PageAuthorityCondition;
import baseline.version3.springboot.entity.pageAdmin.ParentPage;
import baseline.version3.springboot.entity.pageAdmin.SubPage;
import baseline.version3.springboot.exceptionHandler.exception.ServiceLayerException;
import baseline.version3.springboot.exceptionHandler.subType.ServiceException;
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

    @Mappings({
            @Mapping(expression = "java( getParentPage(requestInsert) )", target = "parentPage"),
            @Mapping(expression = "java( getPageAuthorityCondition(requestInsert) )", target = "pageAuthorityCondition")
    })
    public abstract SubPage toInsertEntity(SubPageRequest.RequestInsert requestInsert);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdateEntity(SubPageRequest.RequestUpdate requestUpdate, @MappingTarget ParentPage parentPage);

    public abstract SubPageResponse.Response toResponse(ParentPage parentPage);

    protected ParentPage getParentPage(SubPageRequest.RequestInsert requestInsert){
        return parentPageRepository.findById(requestInsert.getParentPageId()).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );
    }

    protected abstract PageAuthorityCondition getPageAuthorityCondition(SubPageRequest.RequestInsert requestInsert);
}
