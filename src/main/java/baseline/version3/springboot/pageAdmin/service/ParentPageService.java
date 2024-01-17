package baseline.version3.springboot.pageAdmin.service;

import baseline.version3.springboot.pageAdmin.repository.entity.ParentPage;
import baseline.version3.springboot.exceptionHandler.exception.ServiceLayerException;
import baseline.version3.springboot.exceptionHandler.subType.ServiceException;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageMapper;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.domain.parentPage.ParentPageResponse;
import baseline.version3.springboot.pageAdmin.repository.ParentPageRepository;
import baseline.version3.springboot.pageAdmin.repository.querydsl.QParentPageRepository;
import lombok.extern.slf4j.Slf4j;
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

    public List<ParentPageResponse.Response> findList(){
        return qParentPageRepository.selectList();
    }


    public Optional<ParentPageResponse.Response> findOne(ParentPageRequest.RequestDynamicQuery requestDynamicQuery){
        return qParentPageRepository.selectOne(requestDynamicQuery);
    }

    public Optional<ParentPageResponse.Response> findOneById(Long id){
        return qParentPageRepository.selectOneById(id);
    }

    public void registerParentPage(ParentPageRequest.RequestInsert requestInsert){
        if (requestInsert.getParentPageIndexPath() == null || requestInsert.getParentPageIndexPath().isEmpty()){
            requestInsert.setParentPageIndexPath(requestInsert.getParentPageIndexPath());
        }
        ParentPage entity = parentPageMapper.toInsertEntity(requestInsert);
        parentPageRepository.save(entity);
    }

    public void updateParentPage(ParentPageRequest.RequestUpdate requestUpdate){
        ParentPage parentPage = parentPageRepository.findById(requestUpdate.getParentPageId()).orElseThrow(() -> {
            throw new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY);
        });

        parentPageMapper.toUpdateEntity(requestUpdate, parentPage);
        parentPageRepository.save(parentPage);
    }

    public void deleteParentPageById(Long id){
        parentPageRepository.deleteById(id);
    }
}
