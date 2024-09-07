package com.bankptrk.springboot.demosecurity.dao;

import com.bankptrk.springboot.demosecurity.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
