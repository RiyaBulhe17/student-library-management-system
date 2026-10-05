package com.example.student_library_management_system.Controller;

import com.example.student_library_management_system.Service.BookService;
import com.example.student_library_management_system.Service.StudentService;
import com.example.student_library_management_system.requestdto.Bookrequestdto;
import com.example.student_library_management_system.requestdto.Studentrequestdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book/apis")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@RequestBody Bookrequestdto bookrequestdto){
        try {
            String response = bookService.saveBook(bookrequestdto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occured :"+e);
        }
    }
}
