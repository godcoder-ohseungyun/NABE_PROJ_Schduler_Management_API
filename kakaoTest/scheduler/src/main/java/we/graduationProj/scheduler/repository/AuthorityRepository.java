package we.graduationProj.scheduler.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import we.graduationProj.scheduler.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
