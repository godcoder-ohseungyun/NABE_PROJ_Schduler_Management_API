package we.graduationProj.scheduler.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import we.graduationProj.scheduler.entity.User;

import java.util.Optional;

//JpaRepository를 상속하면 findall이나 save메서드를 사용할수있다.
public interface UserRepository extends JpaRepository<User, Long> {
   @EntityGraph(attributePaths = "authorities")
   Optional<User> findOneWithAuthoritiesByUsername(String username); //username을 기준으로 권한 정보도 함께 가져온다.
}
