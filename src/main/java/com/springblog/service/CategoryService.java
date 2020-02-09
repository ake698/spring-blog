package com.springblog.service;

import com.springblog.dao.CategoryDao;
import com.springblog.pojo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public List<Category>list(){
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
        //springboot2.2.1（含）以上的版本Sort已经不能再实例化了，构造方法已经是私有的了！
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return categoryDao.findAll(sort);
    }
    public Category get(int id){
        Category category = categoryDao.findById(id).orElse(null);
        return category;
    }
}
