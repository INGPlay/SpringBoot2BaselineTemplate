package baseline.version3.springboot.pageAdmin.ipAccess.service;

import baseline.version3.springboot.common.domain.subType.ApplyStatus;
import baseline.version3.springboot.controllerAdvice.exception.ServiceLayerException;
import baseline.version3.springboot.controllerAdvice.subType.ServiceException;
import baseline.version3.springboot.pageAdmin.ipAccess.domain.IpAccessMapper;
import baseline.version3.springboot.pageAdmin.ipAccess.domain.IpAccessRequest;
import baseline.version3.springboot.pageAdmin.ipAccess.domain.IpAccessResponse;
import baseline.version3.springboot.pageAdmin.ipAccess.entity.IpAccess;
import baseline.version3.springboot.pageAdmin.ipAccess.repository.IpAccessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class IpAccessService {

    private final IpAccessMapper ipAccessMapper;

    private final IpAccessRepository ipAccessRepository;

    @Transactional(readOnly = true)
    public IpAccessResponse.Response findOne(Long id){
        IpAccess ipAccess = ipAccessRepository.findById(id).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );

        return ipAccessMapper.toResponse(ipAccess);
    }

    @Transactional(readOnly = true)
    public List<IpAccessResponse.Response> findList(){
        List<IpAccess> all = ipAccessRepository.findAll();
        return all.stream().map(ipAccessMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
//    @Cacheable(cacheNames = "IpAccessService.findList", key = "'_' + #requestDynamicQuery.applyStatus + '_'")
    public List<IpAccessResponse.Response> findList(IpAccessRequest.RequestDynamicQuery requestDynamicQuery){
        List<IpAccess> all = ipAccessRepository.findAllByApplyStatusEquals(requestDynamicQuery.getApplyStatus());
        return all.stream().map(ipAccessMapper::toResponse)
                .toList();
    }

    @Cacheable(cacheNames = "IpAccessService.isAppliedIp", key = "'_' + #clientIp + '_'")
    @Transactional(readOnly = true)
    public Boolean isAppliedIp(String clientIp) {
        IpAccessRequest.RequestDynamicQuery requestDynamicQuery = IpAccessRequest.RequestDynamicQuery
                .builder()
                .applyStatus(ApplyStatus.APPLY)
                .build();

        return findList(requestDynamicQuery).stream()
                .map(ipAccess -> new IpAddressMatcher(ipAccess.getIpAddress()))
                .anyMatch(ip -> ip.matches(clientIp));
    }

    public void insertOne(IpAccessRequest.Request request){
        IpAccess ipAccess = ipAccessMapper.requestInsert(request);

        ipAccessRepository.save(ipAccess);
    }

    @CacheEvict(cacheNames = "IpAccessService.isAppliedIp", allEntries = true)
    public void updateOne(IpAccessRequest.RequestUpdate request){

        IpAccess ipAccess = ipAccessRepository.findById(request.getIpAccessId()).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );

        ipAccessMapper.requestUpdate(request, ipAccess);

        ipAccessRepository.save(ipAccess);
    }

    @CacheEvict(cacheNames = "IpAccessService.isAppliedIp", allEntries = true)
    public void deleteOne(IpAccessRequest.RequestDelete request){
        IpAccess ipAccess = ipAccessRepository.findById(request.getIpAccessId()).orElseThrow(
                () -> new ServiceLayerException(ServiceException.NOT_FOUND_IN_REPOSITORY)
        );

        ipAccessRepository.delete(ipAccess);
    }
}
