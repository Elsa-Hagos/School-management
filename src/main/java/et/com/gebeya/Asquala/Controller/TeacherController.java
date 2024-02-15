package et.com.gebeya.Asquala.Controller;

import et.com.gebeya.Asquala.Model.Student;
import et.com.gebeya.Asquala.Model.Teacher;
import et.com.gebeya.Asquala.Repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/api/v1/Teacher")

public class TeacherController {

        @Autowired
        private TeacherRepo teacherRepo;



}
