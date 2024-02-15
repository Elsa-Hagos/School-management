package et.com.gebeya.Asquala.Repo;

import et.com.gebeya.Asquala.Model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> , JpaSpecificationExecutor<Student> {

    Student findByNameAndMiddleName(String name, String middleName);

}
