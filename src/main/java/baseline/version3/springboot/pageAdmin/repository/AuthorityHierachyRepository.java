package baseline.version3.springboot.pageAdmin.repository;

import baseline.version3.springboot.pageAdmin.repository.entity.AuthorityHierachy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityHierachyRepository extends JpaRepository<AuthorityHierachy, Integer> {
}