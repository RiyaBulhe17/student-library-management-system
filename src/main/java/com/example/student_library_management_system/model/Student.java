package com.example.student_library_management_system.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="student")
@Data
public class Student {
    //id,name,email,dept,sem,gender,address,dob
    @Id
    @Column(name = "student")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="Student_name",nullable = false)
    private String name;

    @Column(name = "email_id",nullable = false,unique = true)
    private String email;

    @Column(name = "department",nullable = false)
    private String dept;

    @Column(name = "mobileno",nullable = false,unique = true)
    private String mobile;

    @Column(name = "semester",nullable = false)
    private String sem;

    @Column(name = "gender",nullable = false)
    private String gender;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "dob",nullable = false)
    private String dob;

    @JsonManagedReference//here we are manageing the printing of data
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private Card card;


}
