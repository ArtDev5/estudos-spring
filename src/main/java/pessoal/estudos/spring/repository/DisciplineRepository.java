package pessoal.estudos.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pessoal.estudos.spring.entity.DisciplineEntity;

public interface DisciplineRepository extends JpaRepository<DisciplineEntity, String> {
}
