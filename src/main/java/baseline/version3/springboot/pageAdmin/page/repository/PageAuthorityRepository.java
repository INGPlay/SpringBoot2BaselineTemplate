package baseline.version3.springboot.pageAdmin.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import baseline.version3.springboot.pageAdmin.page.entity.PageAuthority;

import java.util.Optional;

public interface PageAuthorityRepository extends JpaRepository<PageAuthority, Long> {
    public Optional<PageAuthority> findByPageAuthorityCode(String pageAuthorityCode);
}