package baseline.version3.springboot.ipAccess.repository;

import baseline.version3.springboot.ipAccess.entity.IpAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpAccessRepository extends JpaRepository<IpAccess, Long> {
}