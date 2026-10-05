package com.example.student_library_management_system.Service;

import com.example.student_library_management_system.Repository.BookRepository;
import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.requestdto.Bookrequestdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public String saveBook(Bookrequestdto bookrequestdto){
        Book book = new Book();
        book.setTitle(bookrequestdto.getTitle());
        book.setPages(bookrequestdto.getPages());
        book.setRackNo(bookrequestdto.getRackNo());
        book.setAvailability(bookrequestdto.isAvailability());
        book.setCategory(bookrequestdto.getCategory());
        book.setPublishedDate(bookrequestdto.getPublishedDate());
        book.setPublishername(bookrequestdto.getPublishername());

        bookRepository.save(book);
        return "Book saved successfully!";
    }
}
