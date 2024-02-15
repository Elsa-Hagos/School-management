package et.com.gebeya.Asquala.Repo;

import et.com.gebeya.Asquala.Model.Subject;
import et.com.gebeya.Asquala.Model.Teacher;
import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepo extends JpaRepository<Subject , Long> , JpaSpecificationExecutor<Subject> {
   Subject  findByName(String name);


    void deleteByName(String name);
}
