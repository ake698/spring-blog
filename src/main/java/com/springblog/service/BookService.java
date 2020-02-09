package com.springblog.service;

import com.springblog.dao.BookDao;
import com.springblog.pojo.Book;
import com.springblog.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CategoryService categoryService;

    public List<Book> list(){
        Sort sort  = Sort.by(Sort.Direction.DESC,"id");
        return bookDao.findAll(sort);
    }

    public void addOrUpdate(Book book){
        bookDao.save(book);
    }

    public void deleteById(int id){
        bookDao.deleteById(id);
    }

    public List<Book> listByCategory(int cid){
        Category category = categoryService.get(cid);
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return bookDao.findAllByCategory(category);
    }

    public List<Book> listByAuthor(String author){
        return bookDao.findAllByAuthor(author);
    }

}
