package com.bankptrk.springboot.demosecurity.dao;

import com.bankptrk.springboot.demosecurity.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    void save(User theUser);
}
