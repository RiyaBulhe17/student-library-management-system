package com.example.student_library_management_system.Controller;

import com.example.student_library_management_system.Service.StudentService;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.Studentrequestdto;
import com.example.student_library_management_system.responsedto.StudentResponsedto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/apis")
public class StudentController {
/*
Uer request (either postman or UI) -> dispatcher servlet ->
Corresponding API controller class based on endpoint or url) ->\
service -> (bussiness logic) -> repository to perform the operations )
 */
    @Autowired
    StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<?> saveStudent(
            @RequestBody Studentrequestdto studentrequestdto) {

        try {
            StudentResponsedto studentResponsedto =
                    studentService.saveStudent(studentrequestdto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(studentResponsedto);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception occurred: " + e);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findStudentById(
            @PathVariable int id) {

        try {
            Student student = studentService.findStudentById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(student);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception occurred: " + e);
        }
    }

    @GetMapping("/test")
    public String test() {
        return "Student Controller is working";
    }

    @GetMapping("/findByPage")
    public ResponseEntity<?> findAllStudentByPage(
            @RequestParam int pageNo,
            @RequestParam int pageSize) {

        try {
            Page<Student> studentPage =
                    studentService.findStudentByPage(pageNo, pageSize);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(studentPage);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception occurred: " + e);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllStudents() {

        try {
            List<Student> studentList =
                    studentService.findAllStudents();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(studentList);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("Exception occurred: " + e);
        }
    }
    @GetMapping("/sort")
    public ResponseEntity<?> sortStudentByField(@RequestParam String sortBy,@RequestParam String orderBy){
        try {
            List<Student> studentList = studentService.sortStudentbyfield(sortBy,orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occured "+ e.getMessage());
        }
    }
    @GetMapping("/findByEmail")
    public ResponseEntity<?> findStudentByEmail(@RequestParam String email){
        try {
            Student student = studentService.findStudentByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(student);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occured "+ e.getMessage());
        }
    }

    @GetMapping("/findByDept")
    public ResponseEntity<?> findStudentByDept(@RequestParam String dept){
        try {
            List<Student> studentList = studentService.findStudentByDept(dept);
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occured "+ e.getMessage());
        }
    }

    @GetMapping("/findByPageAndSort")
    public ResponseEntity<?> findAllStudentByPageAndSort(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam String sortBy,@RequestParam String orderBy){
        try {
            Page<Student> studentPage = studentService.findStudentByPageAndSort(pageNo, pageSize, sortBy,orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(studentPage);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occured "+ e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudentById(
            @PathVariable int id) {

        try {
            String response =
                    studentService.deleteStudentById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception occurred: " + e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable int id,
            @RequestBody Studentrequestdto studentrequestdto) {

        try {
            String response =
                    studentService.updateStudent(id, studentrequestdto);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception occurred: " + e);
        }
    }
}