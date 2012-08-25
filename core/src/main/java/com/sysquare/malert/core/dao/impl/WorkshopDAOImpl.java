package com.sysquare.malert.core.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sysquare.malert.core.dao.WorkshopDAO;
import com.sysquare.malert.model.domain.Workshop;

@Repository
public class WorkshopDAOImpl implements WorkshopDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
    public Workshop findById(Integer id) {
        return (Workshop)sessionFactory.openSession().get(Workshop.class, id);
    }
	
	@Override
	public void addWorkshop(Workshop workshop) {
		sessionFactory.getCurrentSession().save(workshop);
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<Workshop> listWorkshop(boolean showInactive) {
	    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Workshop.class);
	    if(!showInactive) {
	        criteria.add(Restrictions.eq("active", true));
	    }
	    criteria.addOrder(Order.asc("active"));
	    criteria.addOrder(Order.asc("name"));
        List<Workshop> workshops = criteria.list();
        return workshops;
	}

	@Override
	public void removeWorkshop(Integer id) {
		Workshop workshop = (Workshop) sessionFactory.getCurrentSession().load(
				Workshop.class, id);
		if (null != workshop) {
			sessionFactory.getCurrentSession().delete(workshop);
		}
	}

    @Override
    public void updateWorkshop(Workshop workshop) {
        sessionFactory.getCurrentSession().update(workshop);       
    }

    @Override
    public Workshop findByCode(String code, Integer excludeId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Workshop.class);
        criteria.add(Restrictions.eq("code", code));
        if(excludeId != null) {
            criteria.add(Restrictions.ne("id", excludeId));
        }
        Workshop workshop = (Workshop)criteria.uniqueResult();
        return workshop;
    }
	
	
}
