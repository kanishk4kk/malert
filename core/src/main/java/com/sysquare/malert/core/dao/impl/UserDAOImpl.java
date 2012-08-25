package com.sysquare.malert.core.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sysquare.malert.core.dao.UserDAO;
import com.sysquare.malert.model.domain.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<User> listUser(User adminUser) {
	    if(adminUser.isAdminUser()) {
	        return sessionFactory.getCurrentSession().createQuery("from User").list();            
        }
        return null;
	}

	@Override
	public void removeUser(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().load(
				User.class, id);
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);
		}
	}
	
    @Override
	public User findByEmail(String email, Integer excludeId) {
	    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
	    criteria.add(Restrictions.eq("email", email));
	    if(excludeId != null) {
	        criteria.add(Restrictions.ne("id", excludeId));
	    }
	    User user = (User)criteria.uniqueResult();
	    return user;
	}
	
    @Override
    public User findByEmailAndPassword(String email, String password) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));
        criteria.add(Restrictions.eq("password", password));
        User user = (User)criteria.uniqueResult();
        return user;
    }

    @Override
    public User findById(Integer id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        User user = (User)criteria.uniqueResult();
        return user;
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

}
