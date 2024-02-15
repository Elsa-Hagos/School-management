package et.com.gebeya.Asquala.Controller;


import et.com.gebeya.Asquala.Dto.AuthenticationRequest;
import et.com.gebeya.Asquala.Dto.AuthenticationResponse;
import et.com.gebeya.Asquala.Dto.RegisterRequest;
import et.com.gebeya.Asquala.Dto.StudentCreationDTO;
import et.com.gebeya.Asquala.Model.*;
import et.com.gebeya.Asquala.Repo.*;
import et.com.gebeya.Asquala.Services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

        @Autowired
        private AdminServices adminServices;
        @Autowired
        private TeacherRepo teacherRepo;
        @Autowired
        private SubjectRepo subjectRepo;
        @Autowired
        private GradeSectionRepo gradeSectionRepo;
        @Autowired
        private AuthenticationServices authenticationServices;


        @PostMapping("create-acct")
        public ResponseEntity<AuthenticationResponse> register(
                @RequestBody RegisterRequest request) {
                return ResponseEntity.ok(authenticationServices.register(request));
        }

        // SUBJECT AND GRADESECTION  CONTROLLERS

        @PostMapping( "/createSubject")
        public ResponseEntity<Subject>createSubject(@RequestBody Subject subject){
                Subject createdSubject = adminServices.createSubject(subject);
                return new ResponseEntity<>(createdSubject, HttpStatus.CREATED);        }
        @DeleteMapping("/deleteSubject/{id}")
        public void deleteSubject(@PathVariable String name){
                subjectRepo.deleteByName(name);
        }
        @PostMapping(value = "/createGradeSection")
        public ResponseEntity<GradeSection> createGradeSection(@RequestBody GradeSection gradeSection) {
                GradeSection createdGradeSection = adminServices.createGradeSection(gradeSection);
                return new ResponseEntity<>(createdGradeSection, HttpStatus.CREATED);
        }
        @DeleteMapping("/deleteGradeSection/{grade}{section}")
        public void deleteGradeSection(@PathVariable String grade ,@PathVariable String section){
                gradeSectionRepo.deleteByGradeAndSection(grade , section);
        }

        @PostMapping("/assignSubject")
        public String assignSubject(@RequestParam String subjectName, @RequestParam String grade, @RequestParam String section) {
                return adminServices.assignSubject(subjectName, grade, section);
        }




//TEACHER CONTROLLERS


        @PostMapping(value = "/createTeacher")
        public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher, Address address, PhoneNumber phoneNumber) {
                Teacher createdTeacher = adminServices.createTeacher(teacher, address, phoneNumber);
                return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);}
        @PutMapping("/updateTeacher/{name}/{middleName}")
        public ResponseEntity<String> updateTeacher(@PathVariable String name, @PathVariable String middleName, @RequestBody Teacher teacher, Address address, PhoneNumber phoneNumber) {
                try {
                        String response = adminServices.updateTeacher(name, middleName, teacher, address, phoneNumber);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                } catch (RuntimeException e) {
                        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}}
        @PutMapping("/DeleteTeacher/{name}/{middleName}")
        public ResponseEntity<String> DeleteTeacher(@PathVariable String name, @PathVariable String middleName) {
                try {
                        String response = adminServices.deleteTeacher(name, middleName);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                } catch (RuntimeException e) {
                        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}}
        @PutMapping("/RestoreTeacher/{name}/{middleName}")
        public ResponseEntity<String> restoreTeacher(@PathVariable String name, @PathVariable String middleName) {
                try {
                        String response = adminServices.restoreTeacher(name, middleName);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                } catch (RuntimeException e) {
                        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}}
        @GetMapping("/viewTeacher")
        public List<Teacher> viewTeacher() {
                return adminServices.viewTeacher();
        }
        @PutMapping("/AssignTeacher/{name}/{middleName}/{subjectName}/{grade}/{section}")
        public ResponseEntity<String> assignTeacher(@PathVariable String name, @PathVariable String middleName, @PathVariable String subjectName, @PathVariable String grade, @PathVariable String section) {
                try {
                        String response = adminServices.assignTeacher(name, middleName, subjectName, grade, section);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                } catch (RuntimeException e) {
                        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}}



        //STUDENT CONTROLLER




        @PostMapping(value = "/createStudent")
        public ResponseEntity<Student> createStudent(@RequestBody StudentCreationDTO studentCreationDTO) {
                Student createdStudent = adminServices.createStudent(studentCreationDTO.getStudent(), studentCreationDTO.getGuardian(), studentCreationDTO.getAddress(), studentCreationDTO.getPhoneNumber());
                return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        }
        @PutMapping("/updateStudent/{name}/{middleName}")
        public String updateStudent(@RequestParam String name, @RequestParam String middleName, @RequestBody Student student, @RequestBody Address address, @RequestBody PhoneNumber phoneNumber) {
                return adminServices.updateStudent(name, middleName, student);
        }
        @PutMapping("/DeleteStudent/{name}/{middleName}")
        public ResponseEntity<String> DeleteStudent(@PathVariable String name, @PathVariable String middleName) {
                try {
                        String response = adminServices.deleteStudent(name, middleName);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                } catch (RuntimeException e) {
                        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}}
        @PutMapping("/RestoreStudent/{name}/{middleName}")
        public ResponseEntity<String> restoreStudent(@PathVariable String name, @PathVariable String middleName) {
                try {
                        String response = adminServices.restoreStudent(name, middleName);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                } catch (RuntimeException e) {
                        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);}}
        @GetMapping("/viewStudents")
        public List<Student> viewStudents() {
                return adminServices.viewStudents();
        }

        @GetMapping("/viewStudent/{name}/{middleName}")
        public ResponseEntity<Boolean> getStudentByNameAndMiddleName(@PathVariable String name, @PathVariable String middleName) {
                Student student = adminServices.getStudentByNameAndMiddleName(name, middleName);
                return ResponseEntity.ok(student != null);
        }

        @PostMapping("/assignStudent")
        public String assignStudent(@RequestParam String name, @RequestParam String middleName, @RequestParam String grade, @RequestParam String section) {
                return adminServices.assignStudent(name, middleName, grade, section);
        }


}














