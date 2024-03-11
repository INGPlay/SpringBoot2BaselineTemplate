package baseline.version3.springboot.pageAdmin.page.service;

import baseline.version3.springboot.controllerAdvice.exception.ServiceLayerException;
import baseline.version3.springboot.controllerAdvice.subType.ServiceException;
import baseline.version3.springboot.pageAdmin.page.entity.SubPage;
import baseline.version3.springboot.pageAdmin.page.domain.subPage.SubPageMapper;
import baseline.version3.springboot.pageAdmin.page.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.page.domain.subPage.SubPageResponse;
import baseline.version3.springboot.pageAdmin.page.repository.SubPageRepository;
import baseline.version3.springboot.pageAdmin.page.repository.querydsl.QSubPageRepository;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
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
    private final AntPathMatcher antPathMatcher;

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "SubPageService.findList", key = "'_' + #requestDynamicQuery.parentPageId + '_'")
    public List<SubPageResponse.Response> findList(SubPageRequest.RequestDynamicQuery requestDynamicQuery){
        return qSubPageRepository.selectList(requestDynamicQuery);
    }

    @Transactional(readOnly = true)
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


    @Nullable
    public SubPageResponse.Response findMatchedPage(HttpServletRequest request) throws IOException {
        // 해당하는 URI 찾기 (완전히 같은 경우)
        SubPageRequest.RequestDynamicQueryOne requestDynamicQueryOne = new SubPageRequest.RequestDynamicQueryOne(request.getRequestURI());
        Optional<SubPageResponse.Response> responseOne = findOne(requestDynamicQueryOne);

        SubPageResponse.Response subPage = null;
        // 등록해놓은 경로가 아닌 경우
        if (responseOne.isPresent()){
            subPage = responseOne.get();
        } else {

            // antPathMatcher를 사용하여 해당하는 URI 찾기 (특수기호를 사용한 경우 ex:'*')
            SubPageRequest.RequestDynamicQuery requestDynamicQuery = SubPageRequest.RequestDynamicQuery.builder().build();
            List<SubPageResponse.Response> responseList = findList(requestDynamicQuery).stream()
                    .filter(p -> antPathMatcher.match(p.concatPagePath(), request.getRequestURI()))
                    .toList();

            if (responseList.isEmpty()){
                return null;
            } else if (responseList.size() > 1) {
                log.warn("[PageAdmin] 중복된 패스가 존재합니다.");
                responseList.forEach(r -> {
                    log.warn(" - {}", r.concatPagePath());
                });

                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpServletResponse response = attr.getResponse();
                assert response != null;
                response.sendError(500, "[PageAdmin] 중복된 경로가 존재합니다.");

            } else {
                subPage = responseList.get(0);
            }
        }
        return subPage;
    }
}
