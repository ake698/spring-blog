package com.springblog.dao;

import com.springblog.pojo.Book;
import com.springblog.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDao extends JpaRepository<Book,Integer> {
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);
    List<Book> findAllByAuthorLike(String author);
}
