package et.com.gebeya.Asquala.Repo;

import et.com.gebeya.Asquala.Model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GuardianRepo extends JpaRepository <Guardian , Long> {

}
