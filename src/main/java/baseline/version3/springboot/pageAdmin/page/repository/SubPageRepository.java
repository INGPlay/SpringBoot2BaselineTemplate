package baseline.version3.springboot.pageAdmin.page.repository;

import baseline.version3.springboot.pageAdmin.page.entity.SubPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubPageRepository extends JpaRepository<SubPage, Long> {
}