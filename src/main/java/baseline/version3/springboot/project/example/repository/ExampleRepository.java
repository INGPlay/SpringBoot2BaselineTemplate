package baseline.version3.springboot.project.example.repository;

import baseline.version3.springboot.project.example.repository.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
}