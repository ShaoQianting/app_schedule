package com.example.schedule.dao;


import com.example.schedule.po.User;

import java.util.List;

public interface UserDao {
    public void add(String user_name,String password2,String email, String phone_num);
    public void remove(int id);
    public void update(User user);
    public User findById(int id);
    public List<User> findByName(User user_name);
    public List<User> findAll();

}
