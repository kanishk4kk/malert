package com.sysquare.malert.core.service;

import java.util.List;

import com.sysquare.malert.model.domain.User;

public interface UserService {
	
	public void addUser(User user);
	public List<User> listUser(User adminUser);
	public void removeUser(Integer id);
    User findByEmail(String email, Integer excludeId);
    boolean isUserExistWithEmail(String email, Integer excludeId);
    User findByEmailAndPassword(String email, String password);
    boolean isUserExists(String email, String password);
    public User findById(Integer id);
    public void updateUser(User user);
}
