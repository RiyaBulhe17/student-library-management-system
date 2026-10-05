package com.example.student_library_management_system.Service;

import com.example.student_library_management_system.Repository.BookRepository;
import com.example.student_library_management_system.Repository.CardRepository;
import com.example.student_library_management_system.Repository.TransactionRepository;
import com.example.student_library_management_system.enums.TransactionType;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.model.Transaction;
import com.example.student_library_management_system.requestdto.Transactionrequestdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    public String createTransaction(Transactionrequestdto transactionrequestdto) {
        Transaction transaction = new Transaction();
        if (transactionrequestdto.getTransactionType().equals(TransactionType.BORROW)) {
            transaction.setTransactionType(TransactionType.BORROW);
            transaction.setDueDate(LocalDate.now().plusDays(7).toString());
        } else if (transactionrequestdto.getTransactionType().equals(TransactionType.RETURN)) {
            transaction.setTransactionType(TransactionType.RETURN);
            transaction.setDueDate(null);
        }


        Optional<Card> cardOptional = cardRepository.findById(transactionrequestdto.getCardId());
        if (cardOptional.isPresent()){
            transaction.setCard(cardOptional.get());
        }else{
            transaction.setCard(null);
        }
        //cardid n bookid - foreign keys
        //we have to go to the card repositary and get the card details, if present then set inside the transaction
        //we have to go to the book repositary and get the book details, if present then set inside the transaction
        Optional<Book> bookOptional = bookRepository.findById(transactionrequestdto.getBookId());
        if (bookOptional.isPresent()){
            transaction.setBook(bookOptional.get());
        }else{
            transaction.setBook(null);
        }
        transactionRepository.save(transaction);
        return "transaction created successfully!";
    }
}