package et.com.gebeya.Asquala.Controller;
import et.com.gebeya.Asquala.Model.Student;
import et.com.gebeya.Asquala.Model.Subject;
import et.com.gebeya.Asquala.Repo.StudentRepo;
import et.com.gebeya.Asquala.Services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/Student")
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private  StudentServices studentServices;




    @GetMapping("/subjects")
    public Set<Subject> getSubjects(@RequestParam String grade, @RequestParam String section) {
        return StudentServices.getSubjectsByGradeAndSection(grade, section);
    }
    @GetMapping("/students/{grade}/{section}")
    public String getStudentsByGradeAndSection(@PathVariable String grade, @PathVariable String section) {
        return studentServices.getStudentsByGradeAndSection(grade, section);
    }
}


