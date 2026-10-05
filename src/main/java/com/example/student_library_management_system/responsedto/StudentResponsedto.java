package com.example.student_library_management_system.responsedto;

import com.example.student_library_management_system.model.Student;
import lombok.Data;

@Data
public class StudentResponsedto {

    // responsedto - used to send response back to the user in customized format
    private String message;
    private Student saveStudent;
}