package baseline.version3.springboot.pageAdmin.page.repository;

import baseline.version3.springboot.pageAdmin.page.entity.AuthorityHierachy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityHierachyRepository extends JpaRepository<AuthorityHierachy, Integer> {
}