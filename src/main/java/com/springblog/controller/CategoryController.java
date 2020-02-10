package com.springblog.controller;

import com.springblog.dao.CategoryDao;
import com.springblog.pojo.Category;
import com.springblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public List<Category> list(){
        return categoryService.list();
    }


}
