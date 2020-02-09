package com.springblog.controller;

import com.springblog.pojo.Book;
import com.springblog.pojo.Search;
import com.springblog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
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

    @PostMapping("/search")
    public List<Book> searchResult(@RequestBody Search s){
        if("".equals(s.getKeywords()))return bookService.list();
//        System.out.println("controller 接收"+s.toString());
        return bookService.Search(s.getKeywords());
    }

    @GetMapping("/booksbyauthor")
    public List<Book> listByAuthor(@RequestParam("author")String author){
        return bookService.listByAuthor(author);
    }

    @Value("${server.port}")
    String port;
    @Value("${upload-url}")
    String host;

    /**
     * 获取文件 返回访问文件url
     * @param file
     * @return
     */
    @PostMapping("/covers")
    public String coversUpload(MultipartFile file) {
        String folder = "E:/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://"+host+":"+port+"/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    public String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
