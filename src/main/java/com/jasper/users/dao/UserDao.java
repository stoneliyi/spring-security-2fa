package com.jasper.users.dao;

import com.jasper.users.model.User;

public interface UserDao {

	User findByUserName(String username);

}