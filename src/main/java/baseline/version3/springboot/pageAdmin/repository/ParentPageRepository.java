package baseline.version3.springboot.pageAdmin.repository;

import baseline.version3.springboot.pageAdmin.entity.ParentPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentPageRepository extends JpaRepository<ParentPage, Long> {
}