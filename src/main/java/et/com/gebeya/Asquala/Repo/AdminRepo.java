package et.com.gebeya.Asquala.Repo;

import et.com.gebeya.Asquala.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long > {
}
