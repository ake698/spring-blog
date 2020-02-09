package com.springblog.controller;

import com.springblog.pojo.Book;
import com.springblog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> list(){
        return bookService.list();
    }

    @PostMapping("/books")
    public Book addOrUpdate(@RequestBody Book book){
        bookService.addOrUpdate(book);
        return book;
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Book book){
        bookService.deleteById(book.getId());
    }

    @GetMapping("/category/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid){
        if(0!=cid)return bookService.listByCategory(cid);
        else return list();
    }

    @GetMapping("/booksbyauthor")
    public List<Book> listByAuthor(@RequestParam("author")String author){
        return bookService.listByAuthor(author);
    }
}
