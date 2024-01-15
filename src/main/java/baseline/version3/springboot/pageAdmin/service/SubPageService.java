package baseline.version3.springboot.pageAdmin.service;

import baseline.version3.springboot.entity.pageAdmin.SubPage;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageMapper;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.domain.subPage.SubPageResponse;
import baseline.version3.springboot.pageAdmin.repository.SubPageRepository;
import baseline.version3.springboot.pageAdmin.repository.querydsl.QSubPageRepository;
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
public class SubPageService {

    private final SubPageRepository subPageRepository;
    private final QSubPageRepository qSubPageRepository;
    private final SubPageMapper subPageMapper;

    public List<SubPageResponse.Response> findList(SubPageRequest.RequestDynamicQuery requestDynamicQuery){
        return qSubPageRepository.selectList(requestDynamicQuery);
    }

    public Optional<SubPageResponse.Response> findOne(SubPageRequest.RequestDynamicQueryOne requestDynamicQuery){
        return qSubPageRepository.selectOne(requestDynamicQuery);
    }

    public void registerSubPage(SubPageRequest.RequestInsert requestInsert){
        SubPage entity = subPageMapper.toInsertEntity(requestInsert);
        subPageRepository.save(entity);
    }

    public void deleteParentPageById(Long id){
        subPageRepository.deleteById(id);
    }
}
