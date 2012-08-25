package com.sysquare.malert.core.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sysquare.malert.core.dao.VisitObservationDAO;
import com.sysquare.malert.core.util.OrderBy;
import com.sysquare.malert.model.domain.User;
import com.sysquare.malert.model.domain.VisitObservation;

@Repository
public class VisitObservationDAOImpl implements VisitObservationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addVisitObservation(VisitObservation visitObservation) {
		sessionFactory.getCurrentSession().save(visitObservation);
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<VisitObservation> listVisitObservation() {
		return sessionFactory.getCurrentSession().createQuery("from VisitObservation").list();
	}

	@Override
	public void removeVisitObservation(Integer id) {
		VisitObservation visitObservation = (VisitObservation) sessionFactory.getCurrentSession().load(
				VisitObservation.class, id);
		if (null != visitObservation) {
			sessionFactory.getCurrentSession().delete(visitObservation);
		}
	}

    @Override
    public List<VisitObservation> findByWorkshop(Integer workshopId, List<String> groupBy, List<OrderBy> orderBy) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM VisitObservation vo WHERE vo.workshop.id=" + workshopId;
        if(groupBy != null && !groupBy.isEmpty()) {
            hql += " GROUP BY ";
            for (String property : groupBy) {
                hql += " " + property + ",";
            }
            hql = hql.substring(0, hql.length()-1);
        }
        if(orderBy != null && !orderBy.isEmpty()) {
            hql += " ORDER BY ";
            for (OrderBy prop : orderBy) {
                if(prop.isAsc()) {
                    hql += " " + prop.getProperty() + " ASC,";
                } else {
                    hql += " " + prop.getProperty() + " DESC,";
                }
            }
            hql = hql.substring(0, hql.length()-1);
        }
        Query query = session.createQuery(hql);

        @SuppressWarnings("unchecked")
        List<VisitObservation> visitObservations = query.list();
        return visitObservations;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VisitObservation> findByWorkshopVisitDateAndStatus(Integer workshopId, Date visitDate, String status) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(VisitObservation.class).createAlias("workshop", "workshop").
        add(Restrictions.eq("workshop.id", workshopId));
        if(visitDate != null) {
            criteria.add(Restrictions.eq("visitDate", visitDate));
        }
        if(status != null && !status.trim().isEmpty()) {
            criteria.add(Restrictions.eq("status", status));
        }
        criteria.addOrder(Order.desc("visitDate"));
        criteria.addOrder(Order.desc("status"));
        return criteria.list();
    }

    @Override
    public void saveVisitObservations(List<VisitObservation> visitObservations) {
        Session session = sessionFactory.getCurrentSession();
        for (VisitObservation visitObservation : visitObservations) {
            session.save(visitObservation);
        }
    }

    @Override
    public VisitObservation findById(Integer visitObservationId) {
        return (VisitObservation)sessionFactory.getCurrentSession().get(VisitObservation.class, visitObservationId);
    }

    @Override
    public void update(VisitObservation visitObservation) {
        sessionFactory.getCurrentSession().update(visitObservation);
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public List<VisitObservation> findByStatus(String status) {
    	Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(VisitObservation.class).add(Restrictions.eq("status", status));
        criteria.addOrder(Order.desc("visitDate"));
        return criteria.list();
	}
	
    @SuppressWarnings("unchecked")
	@Override
	public List<VisitObservation> findByWorkshopAndStatus(Integer workshopId, String status, boolean isSent) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(VisitObservation.class).createAlias("workshop", "workshop").
                add(Restrictions.eq("workshop.id", workshopId));
        if(status != null && !status.trim().isEmpty()) {
            criteria.add(Restrictions.eq("status", status));
        }
        criteria.add(Restrictions.lt("targetDate", new Date()));
        criteria.add(Restrictions.eq("isSent", Boolean.valueOf(isSent)));
        criteria.addOrder(Order.desc("visitDate"));
        return criteria.list();
    }

    @Override
    public void updateVisitObservation(List<VisitObservation> visitObservations) {
        Session session = sessionFactory.getCurrentSession();
        for (VisitObservation visitObservation : visitObservations) {
            session.saveOrUpdate(visitObservation);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VisitObservation> findAllPendingVOAfterTarget(User user) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(VisitObservation.class).createAlias("workshop", "workshop").
                add(Restrictions.eq("workshop.active", Boolean.TRUE));
        criteria.add(Restrictions.lt("targetDate", new Date()));
        criteria.add(Restrictions.eq("status", "pending"));
        if(!user.isAdminUser()) {
            criteria.add(Restrictions.eq("workshop.tsmEmail", user.getEmail()).ignoreCase());
        }
        criteria.addOrder(Order.asc("workshop.name"));
        criteria.addOrder(Order.desc("visitDate"));
        return criteria.list();
    }
}
