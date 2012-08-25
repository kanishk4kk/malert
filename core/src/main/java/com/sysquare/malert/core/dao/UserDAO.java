package com.sysquare.malert.core.dao;

import java.util.List;

import com.sysquare.malert.model.domain.User;

public interface UserDAO {
	
	void addUser(User user);
	List<User> listUser(User adminUser);
	void removeUser(Integer id);
	User findByEmail(String email, Integer excludeId);
    User findByEmailAndPassword(String email, String password);
    User findById(Integer id);
    void updateUser(User user);
}
