package baseline.version3.springboot.pageAdmin.domain.subPage;

import baseline.version3.springboot.pageAdmin.entity.ParentPage;
import baseline.version3.springboot.pageAdmin.entity.SubPage;
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

    @Mappings({
            @Mapping(expression = "java( getParentPage(requestInsert) )", target = "parentPage")
    })
    public abstract SubPage toInsertEntity(SubPageRequest.RequestInsert requestInsert);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdateEntity(SubPageRequest.RequestUpdate requestUpdate, @MappingTarget SubPage subPage);

    public abstract SubPageResponse.Response toResponse(SubPage subPage);

    protected ParentPage getParentPage(SubPageRequest.RequestInsert requestInsert){
        return parentPageRepository.findById(requestInsert.getParentPageId()).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );
    }


}
