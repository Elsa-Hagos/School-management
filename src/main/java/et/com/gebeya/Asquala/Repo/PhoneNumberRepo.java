package et.com.gebeya.Asquala.Repo;

import et.com.gebeya.Asquala.Model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepo extends JpaRepository<PhoneNumber , Long> {
}
