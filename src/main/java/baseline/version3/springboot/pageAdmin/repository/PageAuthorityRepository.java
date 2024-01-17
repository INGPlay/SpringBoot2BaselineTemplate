package baseline.version3.springboot.pageAdmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import baseline.version3.springboot.pageAdmin.repository.entity.PageAuthority;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageAuthorityRepository extends JpaRepository<PageAuthority, Long> {
}