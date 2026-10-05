package com.example.student_library_management_system.requestdto;

import com.example.student_library_management_system.model.Student;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Data
public class Cardrequestdto {

    private String cardStatus;
    private String expirydate;
    private Date createdDate;
    private Date updatedDate;
    private Student student;
}
