package com.example.student_library_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "publisher_name")
    private String publishername;

    @Column(name = "publishedDate",nullable = false)
    private String publishedDate;

    @Column(name = "pages",nullable = false)
    private int pages;

    @Column(name = "availibily",nullable = false)
    private boolean availability;

    @Column(name = "category",nullable = false)
    private String category;

    @Column(name = "rackno",nullable = false)
    private String rackNo;

    @OneToMany(mappedBy = "book" )
    private List<Transaction> transactionList;

}
