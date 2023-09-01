package pessoal.estudos.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pessoal.estudos.spring.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
