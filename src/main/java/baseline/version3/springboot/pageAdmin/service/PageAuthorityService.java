package baseline.version3.springboot.pageAdmin.service;

import baseline.version3.springboot.exceptionHandler.exception.ServiceLayerException;
import baseline.version3.springboot.exceptionHandler.subType.ServiceException;
import baseline.version3.springboot.pageAdmin.domain.pageAuthority.PageAuthorityMapper;
import baseline.version3.springboot.pageAdmin.domain.pageAuthority.PageAuthorityRequest;
import baseline.version3.springboot.pageAdmin.domain.pageAuthority.PageAuthorityResponse;
import baseline.version3.springboot.pageAdmin.repository.PageAuthorityRepository;
import baseline.version3.springboot.pageAdmin.repository.entity.PageAuthority;
import baseline.version3.springboot.pageAdmin.repository.querydsl.QPageAuthorityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public List<PageAuthorityResponse.Response> findList(){
        return qPageAuthorityRepository.selectList();
    }

    public Optional<PageAuthorityResponse.Response> findOne(PageAuthorityRequest.RequestDynamicQueryOne requestDynamicQueryOne){
        return qPageAuthorityRepository.selectOne(requestDynamicQueryOne);
    }

    public void createAuth(PageAuthorityRequest.RequestInsert requestInsert){
        PageAuthority entity = pageAuthorityMapper.toInsertEntity(requestInsert);
        pageAuthorityRepository.save(entity);
    }

    public void updateAuth(PageAuthorityRequest.RequestUpdate requestUpdate){
        PageAuthority pageAuthority = pageAuthorityRepository.findById(requestUpdate.getPageAuthorityId()).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );
        pageAuthorityMapper.toUpdateEntity(requestUpdate, pageAuthority);
        pageAuthorityRepository.save(pageAuthority);
    }

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
