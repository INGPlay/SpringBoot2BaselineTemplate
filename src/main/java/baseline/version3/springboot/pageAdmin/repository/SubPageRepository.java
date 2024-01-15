package baseline.version3.springboot.pageAdmin.repository;

import baseline.version3.springboot.entity.pageAdmin.SubPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubPageRepository extends JpaRepository<SubPage, Long> {
}