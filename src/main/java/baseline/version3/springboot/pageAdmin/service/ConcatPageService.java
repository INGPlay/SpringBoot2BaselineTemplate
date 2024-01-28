package baseline.version3.springboot.pageAdmin.service;

import baseline.version3.springboot.pageAdmin.domain.concatPage.ConcatPageRequest;
import baseline.version3.springboot.pageAdmin.domain.concatPage.ConcatPageResponse;
import baseline.version3.springboot.pageAdmin.repository.querydsl.QConcatPageRepository;
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
public class ConcatPageService {

    private final QConcatPageRepository qConcatPageRepository;

    public List<ConcatPageResponse.Response> findList(){
        return qConcatPageRepository.selectList();
    }

    public Optional<ConcatPageResponse.Response> findOne(ConcatPageRequest.RequestDynamicQueryOne requestDynamicQueryOne){
        return qConcatPageRepository.selectList().stream().filter(
                page -> page.concatPagePath().equals(requestDynamicQueryOne.getConcatPagePath())
        ).findAny();
    }
}
