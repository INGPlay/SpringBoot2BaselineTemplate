package baseline.version3.springboot.pageAdmin.domain.pageAuthority;

import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.repository.entity.PageAuthority;
import baseline.version3.springboot.pageAdmin.repository.entity.ParentPage;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,    // "spring"
        injectionStrategy = InjectionStrategy.FIELD
)
public abstract class PageAuthorityMapper {
    public abstract PageAuthority toInsertEntity(PageAuthorityRequest.RequestInsert requestInsert);
}
