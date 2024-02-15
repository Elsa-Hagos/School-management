package et.com.gebeya.Asquala.Repo;

import et.com.gebeya.Asquala.Model.GradeSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeSectionRepo extends JpaRepository<GradeSection , Long> , JpaSpecificationExecutor<GradeSection> {
  GradeSection findByGradeAndSection(String grade, String section);
  GradeSection deleteByGradeAndSection(String grade, String section);
}
