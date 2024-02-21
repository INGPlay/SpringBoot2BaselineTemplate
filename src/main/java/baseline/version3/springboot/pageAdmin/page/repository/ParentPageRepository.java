package baseline.version3.springboot.pageAdmin.page.repository;

import baseline.version3.springboot.pageAdmin.page.entity.ParentPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentPageRepository extends JpaRepository<ParentPage, Long> {
}