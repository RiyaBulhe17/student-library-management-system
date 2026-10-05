package com.example.student_library_management_system.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.example.student_library_management_system.Repository.StudentRepository;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.Studentrequestdto;
import com.example.student_library_management_system.responsedto.StudentResponsedto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentResponsedto saveStudent(Studentrequestdto studentrequestdto) {
        //convert the request dto into model class

        Student student = new Student();
        student.setName(studentrequestdto.getName());
        student.setEmail(studentrequestdto.getEmail());
        student.setSem(studentrequestdto.getSem());
        student.setDept(studentrequestdto.getDept());
        student.setMobile(studentrequestdto.getMobile());
        student.setAddress(studentrequestdto.getAddress());
        student.setDob(studentrequestdto.getDob());
        student.setGender(studentrequestdto.getGender());

        //whenever a student is added at that time card also gets added as a part of cascading
        Card card = new Card();
        card.setCardStatus("ACTIVE");
        card.setExpirydate(LocalDate.now().plusYears(3).toString());
        student.setCard(card);


        student = studentRepository.save(student);
        StudentResponsedto studentResponcedto = new StudentResponsedto();
        studentResponcedto.setMessage("Student saved Successfully!");
        studentResponcedto.setSaveStudent(student);

        return studentResponcedto;
    }

    public Student findStudentById(int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()){
            return studentOptional.get();
    }else {
            throw new RuntimeException("Student with id :"+id+"not found");
        }
  }
    public List<Student> findAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }


    public Student findStudentByEmail(String email){
        Student student = studentRepository.getStudentByEmail(email);
        return student;
    }

    public List<Student> findStudentByDept(String dept){
        List<Student> studentList = studentRepository.getStudentByDept(dept);
        return studentList;
    }

    public Page<Student> findStudentByPage(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        return studentRepository.findAll(pageRequest);
    }
    //sorting and pagination
    public Page<Student> findStudentByPageAndSort(int pageNo,int pageSize, String sortBy ,String orderBy){
        Page<Student> studentPage = null;
        if (orderBy.equalsIgnoreCase("ascending")){
            studentPage = studentRepository.findAll(PageRequest.of(pageNo,pageSize ,Sort.by(sortBy).ascending()));
        }else if (orderBy.equalsIgnoreCase("descending")){
            studentPage = studentRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending()));
        }
        return studentPage;
    }

    public List<Student> sortStudentbyfield(String sortBy, String orderBy){
        List<Student> studentList = null;
        if (orderBy.equalsIgnoreCase("ascending")){
            studentList = studentRepository.findAll(Sort.by(sortBy).ascending());
        }else if (orderBy.equalsIgnoreCase("descending")){
            studentList = studentRepository.findAll(Sort.by(sortBy).descending());
        }
        return studentList;
    }
    public String deleteStudentById(int id){
        Student student = findStudentById(id);
        if (student!=null){
            studentRepository.deleteById(id);
            return "Student with id:"+id+"deleted successfully!";
        }else {
            throw new RuntimeException("Student with id:"+id+"not found");
        }

    }
    public String updateStudent(int id,Studentrequestdto studentrequestdto) {
        Student existingStudent = findStudentById(id);
        if (existingStudent != null) {
            existingStudent.setName(studentrequestdto.getName());
            existingStudent.setDept(studentrequestdto.getDept());
            existingStudent.setSem(studentrequestdto.getSem());
            existingStudent.setMobile(studentrequestdto.getMobile());
            existingStudent.setAddress(studentrequestdto.getAddress());
            existingStudent.setGender(studentrequestdto.getGender());
            existingStudent.setEmail(studentrequestdto.getEmail());
            existingStudent.setDob(studentrequestdto.getDob());

            studentRepository.save(existingStudent);
            return "Student with id:" + id + "updated successfully!";
        } else {
            throw new RuntimeException("Student with id:" + id + "not found");
        }

    }

        /*
        pagination - fetching or getting the records of data int the form of pages
        pagenumber - the number of pages we want to see (0,1,2,3,4,5,6,7,8,9,....)
        pagesize - tatal number of records in each page(fixed for each page)

        total number of record - 28, pages size - 5
        0th page - 1-5
        1st page - 6-10
        2nd page - 11-15
        3rd - 16-20
        4th - 21-25
        5th - 26-28

        tatal number of records -11 , page size-3
        0th page -1-3
        1st page- 4-6
        2nd page - 7-9
        3rd page -10-11
         */

}