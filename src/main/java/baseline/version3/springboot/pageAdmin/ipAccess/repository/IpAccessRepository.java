package baseline.version3.springboot.pageAdmin.ipAccess.repository;

import baseline.version3.springboot.common.domain.subType.ApplyStatus;
import baseline.version3.springboot.pageAdmin.ipAccess.entity.IpAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpAccessRepository extends JpaRepository<IpAccess, Long> {
    public List<IpAccess> findAllByApplyStatusEquals(ApplyStatus applyStatus);
}