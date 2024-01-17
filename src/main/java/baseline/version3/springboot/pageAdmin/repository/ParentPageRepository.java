package baseline.version3.springboot.pageAdmin.repository;

import baseline.version3.springboot.pageAdmin.repository.entity.ParentPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentPageRepository extends JpaRepository<ParentPage, Long> {
}