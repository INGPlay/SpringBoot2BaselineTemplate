package baseline.version3.springboot.pageAdmin.service;

import baseline.version3.springboot.pageAdmin.repository.entity.SubPage;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageMapper;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageResponse;
import baseline.version3.springboot.pageAdmin.repository.SubPageRepository;
import baseline.version3.springboot.pageAdmin.repository.querydsl.QSubPageRepository;
import faraway.util.restformat.domain.exception.ServiceException;
import faraway.util.restformat.domain.exception.ServiceLayerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class SubPageService {

    private final SubPageRepository subPageRepository;
    private final QSubPageRepository qSubPageRepository;
    private final SubPageMapper subPageMapper;

    @Cacheable(cacheNames = "SubPageService.findList", key = "'_' + #requestDynamicQuery.parentPageId + '_'")
    public List<SubPageResponse.Response> findList(SubPageRequest.RequestDynamicQuery requestDynamicQuery){
        return qSubPageRepository.selectList(requestDynamicQuery);
    }

    @Cacheable(
            cacheNames = "SubPageService.findOne",
            key = "'_' + #requestDynamicQuery.concatPagePath + '_'",
            condition = "#requestDynamicQuery.concatPagePath != null && #requestDynamicQuery.parentPageId != '' "
    )
    public Optional<SubPageResponse.Response> findOne(SubPageRequest.RequestDynamicQueryOne requestDynamicQuery){
        return qSubPageRepository.selectOne(requestDynamicQuery);
    }



    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "ParentPageService.findList", allEntries = true),
                    @CacheEvict(cacheNames = "SubPageService.findList", allEntries = true),
                    @CacheEvict(cacheNames = "SubPageService.findOne", allEntries = true)
            }
    )
    public void registerSubPage(SubPageRequest.RequestInsert requestInsert){
        SubPage entity = subPageMapper.toInsertEntity(requestInsert);
        subPageRepository.save(entity);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "SubPageService.findList", allEntries = true),
                    @CacheEvict(cacheNames = "SubPageService.findOne", allEntries = true)
            }
    )
    public void updateSubPage(SubPageRequest.RequestUpdate requestUpdate){
        SubPage subPage = subPageRepository.findById(requestUpdate.getSubPageId()).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );
        subPageMapper.toUpdateEntity(requestUpdate, subPage);
        subPageRepository.save(subPage);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "ParentPageService.findList", allEntries = true),
                    @CacheEvict(cacheNames = "SubPageService.findList", allEntries = true),
                    @CacheEvict(cacheNames = "SubPageService.findOne", allEntries = true)
            }
    )
    public void deleteParentPageById(Long id){
        subPageRepository.deleteById(id);
    }
}
