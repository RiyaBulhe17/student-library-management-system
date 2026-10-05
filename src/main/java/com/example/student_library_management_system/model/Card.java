package com.example.student_library_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
@Data
public class Card {
    //id,cardstatus,expirydate,createddate,updateddate
    @Id
    @Column(name = "card")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_status",nullable = false)
    private String cardStatus;

    @Column(name = "expiry_date",nullable = false)
    private String expirydate;

    @Column(name = "createdDate",nullable = false)
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "updatedDate",nullable = false)
    @UpdateTimestamp
    private Date updatedDate;


    @JsonBackReference//it is printing the data already in the previous class, we do not need to print it again
    @JoinColumn//it joins the primary key student id from student table as a foreign key to card
    @OneToOne//one card will be assigned to one student
    private Student student;// uses primary key of the student table to join with the card table

    @OneToMany(mappedBy = "card")
    private List<Transaction> transactionList;
}
