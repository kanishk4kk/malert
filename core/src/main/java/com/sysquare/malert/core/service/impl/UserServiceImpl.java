package com.sysquare.malert.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sysquare.malert.core.dao.UserDAO;
import com.sysquare.malert.core.service.UserService;
import com.sysquare.malert.model.domain.User;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	public List<User> listUser(User adminUser) {
		return userDAO.listUser(adminUser);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void removeUser(Integer id) {
		userDAO.removeUser(id);
	}
	
	@Override
	public User findByEmail(String email, Integer excludeId) {
	    return userDAO.findByEmail(email, excludeId);
	}
	
	@Override
	public boolean isUserExistWithEmail(String email, Integer excludeId) {
	    User user = findByEmail(email, excludeId);
	    if(user != null) {
	        return true;
	    }
	    return false;
	}
	
	@Override
	public User findByEmailAndPassword(String email, String password) {
	    return userDAO.findByEmailAndPassword(email, password);
	}
	
	@Override
    public boolean isUserExists(String email, String password) {
        User user = findByEmailAndPassword(email, password);
        if(user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User findById(Integer id) {
        return userDAO.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
    
}
