package com.example.student_library_management_system.requestdto;

import com.example.student_library_management_system.model.Card;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class Studentrequestdto {

//we can use model class itself to take input even that will work as we have seen in hospital management system
    // but we should follow the standard practice to maintain different layers in spring boot project
    // this helps us to clearly make a difference between model class and requestdto class
    private String name;
    private String email;
    private String dept;
    private String mobile;
    private String sem;
    private String gender;
    private String address;
    private String dob;
    private Card card;

}
