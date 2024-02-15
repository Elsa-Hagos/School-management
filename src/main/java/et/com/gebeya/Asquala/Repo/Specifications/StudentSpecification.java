package et.com.gebeya.Asquala.Repo.Specifications;



    import et.com.gebeya.Asquala.Model.GradeSection;
    import et.com.gebeya.Asquala.Model.Guardian;
    import et.com.gebeya.Asquala.Model.Student;
    import jakarta.persistence.criteria.*;

    import org.springframework.data.jpa.domain.Specification;

    import java.util.ArrayList;
    import java.util.List;


public class StudentSpecification  {



            public static Specification<Student> hasNameAndMiddleName(String name, String middleName) {
                return (root, query, cb) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(cb.equal(root.get("name"), name));
                    predicates.add(cb.equal(root.get("middleName"), middleName));
                    return cb.and(predicates.toArray(new Predicate[0]));
                };
            }
    public static Specification<GradeSection> hasGradeAndSection(String grade, String section) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("grade"), grade));
            predicates.add(cb.equal(root.get("section"), section));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

        private static Join<Student, Guardian> getStudentGuardianJoin(Root<Student> root) {
            return root.join("guardian");
        }
    }


