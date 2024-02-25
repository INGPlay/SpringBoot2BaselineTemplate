package baseline.version3.springboot.pageAdmin.page.service;

import baseline.version3.springboot.controllerAdvice.exception.ServiceLayerException;
import baseline.version3.springboot.controllerAdvice.subType.ServiceException;
import baseline.version3.springboot.pageAdmin.page.domain.pageAuthority.PageAuthorityMapper;
import baseline.version3.springboot.pageAdmin.page.domain.pageAuthority.PageAuthorityRequest;
import baseline.version3.springboot.pageAdmin.page.domain.pageAuthority.PageAuthorityResponse;
import baseline.version3.springboot.pageAdmin.page.entity.PageAuthority;
import baseline.version3.springboot.pageAdmin.page.repository.PageAuthorityRepository;
import baseline.version3.springboot.pageAdmin.page.repository.querydsl.QPageAuthorityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class PageAuthorityService {
    private final PageAuthorityRepository pageAuthorityRepository;
    private final QPageAuthorityRepository qPageAuthorityRepository;
    private final PageAuthorityMapper pageAuthorityMapper;

    @Transactional(readOnly = true)
    public List<PageAuthorityResponse.Response> findList(){
        return qPageAuthorityRepository.selectList();
    }

    @Transactional(readOnly = true)
    public Optional<PageAuthorityResponse.Response> findOne(PageAuthorityRequest.RequestDynamicQueryOne requestDynamicQueryOne){
        return qPageAuthorityRepository.selectOne(requestDynamicQueryOne);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void createAuth(PageAuthorityRequest.RequestInsert requestInsert){
        PageAuthority entity = pageAuthorityMapper.toInsertEntity(requestInsert);
        pageAuthorityRepository.save(entity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void updateAuth(PageAuthorityRequest.RequestUpdate requestUpdate){
        PageAuthority pageAuthority = pageAuthorityRepository.findById(requestUpdate.getPageAuthorityId()).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );
        pageAuthorityMapper.toUpdateEntity(requestUpdate, pageAuthority);
        pageAuthorityRepository.save(pageAuthority);
    }

    @Transactional(readOnly = true)
    public PageAuthorityResponse.Response findById(Long id) {
        PageAuthority pageAuthority = pageAuthorityRepository.findById(id).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );
        return pageAuthorityMapper.toResponse(pageAuthority);
    }

    public void delete(PageAuthorityRequest.RequestDelete requestDelete){
        pageAuthorityRepository.deleteById(requestDelete.getPageAuthorityId());
    }
}
