package com.sysquare.malert.core.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sysquare.malert.core.dao.OptionDAO;
import com.sysquare.malert.model.domain.AppOption;

@Repository
public class OptionDAOImpl implements OptionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addOption(AppOption option) {
        sessionFactory.getCurrentSession().save(option);
    }

    @Override
    public AppOption findByName(String name) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AppOption.class);
        criteria.add(Restrictions.eq("name", getMD5(name)));
        AppOption option = (AppOption)criteria.uniqueResult();
        return option;
    }

    @Override
    public void updateOption(AppOption option) {
        sessionFactory.getCurrentSession().update(option);
    }

    public String getMD5(String val) {
        String hql = "select md5(:val)";
        Query q = sessionFactory.getCurrentSession().createSQLQuery(hql);
        q.setParameter("val", val);
        String md5 = (String) q.uniqueResult();
        return md5;
    }
}
