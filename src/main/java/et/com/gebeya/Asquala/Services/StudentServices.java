package et.com.gebeya.Asquala.Services;

import et.com.gebeya.Asquala.Dto.StudentSubjectResponse;
import et.com.gebeya.Asquala.Model.*;
import et.com.gebeya.Asquala.Repo.GradeSectionRepo;
import et.com.gebeya.Asquala.Repo.Specifications.StudentSpecification;
import et.com.gebeya.Asquala.Repo.StudentRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServices {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private static GradeSectionRepo gradeSectionRepo;

    public static Set<Subject> getSubjectsByGradeAndSection(String grade, String section) {
        List<GradeSection> gradeSections = gradeSectionRepo.findAll(StudentSpecification.hasGradeAndSection(grade, section));
        Set<Subject> subjects = new HashSet<>();
        for (GradeSection gradeSection : gradeSections) {
            subjects.addAll(gradeSection.getSubjects());
        }
        return subjects;
    }

    public String getStudentsByGradeAndSection(String grade, String section) {
        GradeSection existingGradeSection = gradeSectionRepo.findByGradeAndSection(grade, section);
        if (existingGradeSection == null) {
            throw new RuntimeException("GradeSection not found with :" + grade + " and section " + section);
        }

        Set<Student> students = existingGradeSection.getStudents();
        if (students == null) {
            throw new RuntimeException("No students found for grade: " + grade + " and section " + section);
        }

        List<String> studentNames = new ArrayList<>();
        for (Student student : students) {
            studentNames.add(student.getName() + " " + student.getMiddleName());
        }

        return studentNames.toString();
    }




}


 