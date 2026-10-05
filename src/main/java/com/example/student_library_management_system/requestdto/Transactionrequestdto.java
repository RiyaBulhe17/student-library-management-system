package com.example.student_library_management_system.requestdto;

import com.example.student_library_management_system.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class Transactionrequestdto {
    //dto -take request from the api and send response
    //request dto - take request from the apis

    private String dueDate;
    private TransactionType transactionType;

    private int cardId;
    private int bookId;
}

