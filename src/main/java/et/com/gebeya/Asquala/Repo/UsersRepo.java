package et.com.gebeya.Asquala.Repo;

import et.com.gebeya.Asquala.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users , Long> {

    Optional<Users> findByusername(String username);
}
