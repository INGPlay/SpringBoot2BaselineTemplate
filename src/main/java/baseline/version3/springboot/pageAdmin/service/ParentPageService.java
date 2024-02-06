package baseline.version3.springboot.pageAdmin.service;

import baseline.version3.springboot.pageAdmin.repository.entity.ParentPage;
import baseline.version3.springboot.controllerAdvice.exception.ServiceLayerException;
import baseline.version3.springboot.controllerAdvice.subType.ServiceException;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageMapper;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageResponse;
import baseline.version3.springboot.pageAdmin.repository.ParentPageRepository;
import baseline.version3.springboot.pageAdmin.repository.querydsl.QParentPageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ParentPageService {

    private final ParentPageRepository parentPageRepository;
    private final QParentPageRepository qParentPageRepository;
    private final ParentPageMapper parentPageMapper;

    @Cacheable(cacheNames = "ParentPageService.findList")
    public List<ParentPageResponse.Response> findList(){
        return qParentPageRepository.selectList();
    }


    public Optional<ParentPageResponse.Response> findOne(ParentPageRequest.RequestDynamicQueryOne requestDynamicQueryOne){
        return qParentPageRepository.selectOne(requestDynamicQueryOne);
    }

    public Optional<ParentPageResponse.Response> findOneById(Long id){

        ParentPageRequest.RequestDynamicQueryOne requestDynamicQueryOne = new ParentPageRequest.RequestDynamicQueryOne(id);
        return qParentPageRepository.selectOne(requestDynamicQueryOne);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "ParentPageService.findList", allEntries = true),
                    @CacheEvict(cacheNames = "SubPageService.findList", allEntries = true),
                    @CacheEvict(cacheNames = "SubPageService.findOne", allEntries = true)
            }
    )
    public void registerParentPage(ParentPageRequest.RequestInsert requestInsert){
        ParentPage entity = parentPageMapper.toInsertEntity(requestInsert);
        parentPageRepository.save(entity);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "ParentPageService.findList", allEntries = true),
                    @CacheEvict(cacheNames = "SubPageService.findList", allEntries = true),
                    @CacheEvict(cacheNames = "SubPageService.findOne", allEntries = true)
            }
    )
    public void updateParentPage(ParentPageRequest.RequestUpdate requestUpdate){
        ParentPage parentPage = parentPageRepository.findById(requestUpdate.getParentPageId()).orElseThrow(() -> {
            throw new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY);
        });

        parentPageMapper.toUpdateEntity(requestUpdate, parentPage);
        parentPageRepository.save(parentPage);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "ParentPageService.findList", allEntries = true),
                    @CacheEvict(cacheNames = "SubPageService.findList", allEntries = true),
                    @CacheEvict(cacheNames = "SubPageService.findOne", allEntries = true)
            }
    )
    public void deleteParentPageById(Long id){
        parentPageRepository.deleteById(id);
    }
}
