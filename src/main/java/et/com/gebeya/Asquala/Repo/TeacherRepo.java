package et.com.gebeya.Asquala.Repo;

import et.com.gebeya.Asquala.Model.Subject;
import et.com.gebeya.Asquala.Model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository <Teacher , Long> , JpaSpecificationExecutor<Teacher> {

  // List<Teacher> findByNameAndMiddleName(String name, String middleName);
    Teacher findByNameAndMiddleName(String name, String middleName);
  Teacher findByName(String name);


}
