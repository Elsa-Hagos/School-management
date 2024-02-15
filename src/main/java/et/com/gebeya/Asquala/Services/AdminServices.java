package et.com.gebeya.Asquala.Services;
import et.com.gebeya.Asquala.Model.*;
import et.com.gebeya.Asquala.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Service
public class AdminServices {
        @Autowired
        private AddressRepo addressRepo;
        @Autowired
        private PhoneNumberRepo phoneNumberRepo;
        @Autowired
        private TeacherRepo teacherRepo;
        @Autowired
        private  SubjectRepo subjectRepo;
        @Autowired
        private GradeSectionRepo gradeSectionRepo;
        @Autowired
        private StudentRepo studentRepo;
        @Autowired
        private GuardianRepo guardianRepo;

    //SUBJECT AND GRADESECTION SERVISES
    public Subject createSubject(Subject subject){
        return subjectRepo.save(subject);}
    public void deleteSubject(String name){
        subjectRepo.deleteByName(name);}
    public GradeSection createGradeSection(GradeSection gradeSection ){
      return gradeSectionRepo.save(gradeSection);
    }
    public void deleteGradeSection(String grade, String section){
        gradeSectionRepo.deleteByGradeAndSection( grade, section);}
    public String assignSubject(String subjectName, String grade, String section) {
        Subject existingSubject = subjectRepo.findByName(subjectName);
        if (existingSubject == null) {
            throw new RuntimeException("Subject not found with name: " + subjectName);
        }
        GradeSection existingGradeSection = gradeSectionRepo.findByGradeAndSection(grade, section);
        if (existingGradeSection == null) {
            throw new RuntimeException("GradeSection not found with grade: " + grade + " and section: " + section);
        }
        // Assign the subject to the grade section
        existingGradeSection.getSubjects().add(existingSubject);
        gradeSectionRepo.save(existingGradeSection);
        return "Subject assigned to GradeSection...";
    }




    //TEACHER SERVISES
        public Teacher createTeacher(Teacher teacher, Address address, PhoneNumber phoneNumber) {
            Address savedAddress = addressRepo.save(address);
            PhoneNumber savedPhoneNumber = phoneNumberRepo.save(phoneNumber);
            teacher.setAddress(savedAddress);
           teacher.setPhoneNumber(savedPhoneNumber);
            teacher.setActive(true);
            return teacherRepo.save(teacher);}
        public String updateTeacher(String name, String middleName, Teacher teacher, Address address, PhoneNumber phoneNumber) {
            Teacher existingTeacher = teacherRepo.findByNameAndMiddleName(name, middleName);
            if (existingTeacher == null) {throw new RuntimeException("Teacher not found with name: " + name + " and middle name: " + middleName);}
            if (!existingTeacher.isActive()) {throw new RuntimeException("Teacher with name: " + name + " and middle name: " + middleName + " is not active");}
            Address existingAddress = addressRepo.findById(existingTeacher.getAddress().getId()).orElse(null);
            if (existingAddress == null) {throw new RuntimeException("Address not found");}
            PhoneNumber existingPhoneNumber = phoneNumberRepo.findById(existingTeacher.getPhoneNumber().getId()).orElse(null);
            if (existingPhoneNumber == null) {throw new RuntimeException("PhoneNumber not found");}
        if (teacher.getName() != null) existingTeacher.setName(teacher.getName());
        if (teacher.getMiddleName() != null) existingTeacher.setMiddleName(teacher.getMiddleName());
        if (teacher.getLastName() != null) existingTeacher.setLastName(teacher.getLastName());
        if (teacher.getGender() != null) existingTeacher.setGender(teacher.getGender());
        if (teacher.getQualification() != null) existingTeacher.setQualification(teacher.getQualification());
        if (address.getCity() != null) existingAddress.setCity(address.getCity());
        if (phoneNumber.getPhoneNumber() != null) existingPhoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber());
        Instant now = Instant.now();
        existingTeacher.setUpdatedOn(now);
        existingAddress.setUpdatedOn(now);
        existingPhoneNumber.setUpdatedOn(now);
        teacherRepo.save(existingTeacher);
        addressRepo.save(existingAddress);
        phoneNumberRepo.save(existingPhoneNumber);
        return "Teacher, Address, and PhoneNumber updated...";}
    public String deleteTeacher(String name, String middleName) {
        Teacher existingTeacher = teacherRepo.findByNameAndMiddleName(name, middleName);
        if (existingTeacher == null) {
            throw new RuntimeException("Teacher not found with name: " + name + " and middle name: " + middleName);}
        existingTeacher.setActive(false);
        teacherRepo.save(existingTeacher);
        return "Teacher deleted...";}
    public String restoreTeacher(String name, String middleName) {
        Teacher existingTeacher = teacherRepo.findByNameAndMiddleName(name, middleName);
        if (existingTeacher == null) {
            throw new RuntimeException("Teacher not found with name: " + name + " and middle name: " + middleName);}
        existingTeacher.setActive(true);
        teacherRepo.save(existingTeacher);
        return "Teacher restored...";}
    public List<Teacher> viewTeacher() {
        return teacherRepo.findAll();
    }

/*
    public Teacher viewTeacher(String name, String middleName) {
        Teacher existingTeacher = teacherRepo.findByNameAndMiddleName(name, middleName);
        if (existingTeacher == null || !existingTeacher.isActive()) {
            throw new RuntimeException("Active Teacher not found with name: " + name + " and middle name: " + middleName);
        }
        Address existingAddress = addressRepo.findById(existingTeacher.getAddress().getId()).orElse(null);
        if (existingAddress == null || !existingAddress.getIsActive()) {
            throw new RuntimeException("Active Address not found");
        }
        PhoneNumber existingPhoneNumber = phoneNumberRepo.findById(existingTeacher.getPhoneNumber().getId()).orElse(null);
        if (existingPhoneNumber == null || !existingPhoneNumber.getIsActive()) {
            throw new RuntimeException("Active PhoneNumber not found");
        }

        // Fetch the teacher, address, and phone number
        existingTeacher.setAddress(existingAddress);
        existingTeacher.setPhoneNumber(existingPhoneNumber);

        return existingTeacher;}  */

    public String assignTeacher(String name, String middleName, String subjectName, String grade, String section) {
        Teacher existingTeacher = teacherRepo.findByNameAndMiddleName(name, middleName);
        if (existingTeacher == null || !existingTeacher.isActive()) {
            throw new RuntimeException("Active Teacher not found with name: " + name + " and middle name: " + middleName);}
        Subject existingSubject = subjectRepo.findByName(subjectName);
        if (existingSubject == null) {
            throw new RuntimeException("Subject not found with name: " + subjectName);}
        GradeSection existingGradeSection = gradeSectionRepo.findByGradeAndSection(grade, section);
        if (existingGradeSection == null) {
            throw new RuntimeException("GradeSection not found with grade: " + grade + " and section: " + section);}
        //Assign the teacher to the grade section
        existingGradeSection.setHomeRoomTeacher(existingTeacher);
        // Assign the subject to the teacher
       existingTeacher.getSubject().add(existingSubject);
        teacherRepo.save(existingTeacher);
        subjectRepo.save(existingSubject);
        gradeSectionRepo.save(existingGradeSection);
        return "Teacher assigned to GradeSection and Subject...";
    }


    //STUDENT SERVISES




    public Student createStudent(Student student, Guardian guardian, Address address, PhoneNumber phoneNumber) {
        Address savedAddress = addressRepo.save(address);
        PhoneNumber savedPhoneNumber = phoneNumberRepo.save(phoneNumber);
        guardian.setAddress(savedAddress);
        guardian.setPhoneNumber(savedPhoneNumber);
        Guardian savedGuardian = guardianRepo.save(guardian);
        guardian.setActive(true);
        if (student.getGuardians() == null) {
            student.setGuardians(new HashSet<>());
        }
       // student.getGuardians().add(savedGuardian);
        Student savedStudent = studentRepo.save(student);
        student.setIsActive(true);
        return savedStudent;}



    public String updateStudent(String name, String middleName, Student student) {
        Student existingStudent = studentRepo.findByNameAndMiddleName(name, middleName);
        if (existingStudent == null) {
            throw new RuntimeException("Student not found with name: " + name + " and middle name: " + middleName);}
        if (!existingStudent.getIsActive()) {
            throw new RuntimeException("Student with name: " + name + " and middle name: " + middleName + " is not active");}
        // Update the fields if they are not null
        if (student.getName() != null) existingStudent.setName(student.getName());
        if (student.getMiddleName() != null) existingStudent.setMiddleName(student.getMiddleName());
        if (student.getLastName() != null) existingStudent.setLastName(student.getLastName());
        if (student.getGender() != null) existingStudent.setGender(student.getGender());
        if (student.getDob() != null) existingStudent.setDob(student.getDob());
        if (student.getStudentId() != null) existingStudent.setStudentId(student.getStudentId());
        Instant now = Instant.now();
        existingStudent.setUpdatedOn(now);
        studentRepo.save(existingStudent);
        return "Student updated...";
    }

    public String deleteStudent(String name, String middleName) {
        Student existingStudent = studentRepo.findByNameAndMiddleName(name, middleName);
        if (existingStudent == null) {
            throw new RuntimeException("Student not found with name: " + name + " and middle name: " + middleName);}
        existingStudent.setIsActive(false);
        studentRepo.save(existingStudent);
        return "student deleted...";
    }


    public String restoreStudent(String name, String middleName) {
        Student existingStudent = studentRepo.findByNameAndMiddleName(name, middleName);
        if (existingStudent == null) {
            throw new RuntimeException("Student not found with name: " + name + " and middle name: " + middleName);}
        existingStudent.setIsActive(true);
       studentRepo.save(existingStudent);
        return "Student restored...";
    }
    public List<Student> viewStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentByNameAndMiddleName(String name , String middleName ) {
        Student existingStudent = studentRepo.findByNameAndMiddleName(name, middleName);
        if (existingStudent == null) {
            throw new RuntimeException("Student not found with name: " + name + " and middle name: " + middleName);
        }
        return studentRepo.findByNameAndMiddleName(name, middleName);
    }
    public String assignStudent(String name, String middleName, String grade, String section) {
        Student existingStudent = studentRepo.findByNameAndMiddleName(name, middleName);
        if (existingStudent == null) {
            throw new RuntimeException(" Student not found with name: " + name + " and middle name: " + middleName);
        }
        GradeSection existingGradeSection = gradeSectionRepo.findByGradeAndSection(grade, section);
        if (existingGradeSection == null) {
            throw new RuntimeException("GradeSection not found with grade: " + grade + " and section: " + section);
        }

        // Assign the student to the grade section
        existingGradeSection.getStudents().add(existingStudent);
        // Assign the grade section to the student
        //   existingStudent.getGradeSections().add(existingGradeSection);
        studentRepo.save(existingStudent);
        gradeSectionRepo.save(existingGradeSection);
        return "Student assigned to GradeSection...";

    }
}












