package com.bankptrk.springboot.demosecurity.service;

import com.bankptrk.springboot.demosecurity.entity.User;
import com.bankptrk.springboot.demosecurity.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	void save(WebUser webUser);

}
