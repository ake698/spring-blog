package com.springblog.UserService;

import com.springblog.dao.UserDao;
import com.springblog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userDao.findByUsername(username);
    }

    public User get(String username, String password){
        return userDao.getByUsernameAndPassword(username, password);
    }

    public User get(User user){
        return userDao.getByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public void add(User user) {
        userDao.save(user);
    }

}
