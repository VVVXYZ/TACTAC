package com.trio.breakFast.dao.impl;

import com.trio.breakFast.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/*
*
*   @author   loser
*   @data  2016/2/24
* */

@Repository
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获得当前事物的session
     *
     * @return org.hibernate.Session
     */
    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(T o) {
        if (o != null) {
            return this.getCurrentSession().save(o);
        }
        return null;
    }

    @Override
    public T get(Class<T> c, Serializable id) {
        return (T) this.getCurrentSession().get(c, id);
    }

    @Override
    public T get(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @Override
    public T get(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @Override
    public void delete(T o) {
        if (o != null) {
            this.getCurrentSession().delete(o);
        }
    }

    @Override
    public void update(T o) {
        if (o != null) {
            this.getCurrentSession().update(o);
        }
    }

    @Override
    public void saveOrUpdate(T o) {
        if (o != null) {
            this.getCurrentSession().saveOrUpdate(o);
        }
    }

    @Override
    public List<T> find(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.list();
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params, int page, int rows) {

        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public List<T> find(String hql, int page, int rows) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public <K> List<K> findVO(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    public <K> List<K> findVO(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.list();
    }

    @Override
    public <K> List<K> findVO(String hql, Map<String, Object> params, int page, int rows) {

        Query q = this.getCurrentSession().createQuery(hql);
       if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public <K> List<K> findVO(String hql, int page, int rows) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
     public Long count(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return (Long) q.uniqueResult();
    }

    @Override
    public Long count(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return (Long) q.uniqueResult();
    }

    @Override
    public Object executeStatisticsHql(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return  q.uniqueResult();
    }

    @Override
    public Object executeStatisticsHql(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.uniqueResult();
    }

    @Override
    public int executeHql(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.executeUpdate();
    }

    @Override
    public int executeHql(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.executeUpdate();
    }

    @Override
    public List<Object[]> findBySql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.list();
    }

    @Override
    public List<Object[]> findBySql(String sql, int page, int rows) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public List<Object[]> findBySql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.list();
    }

    @Override
    public List<Object[]> findBySql(String sql, Map<String, Object> params, int page, int rows) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }



    @Override
    public int executeSql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.executeUpdate();
    }

    @Override
    public int executeSql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.executeUpdate();
    }

    @Override
    public Object executeStatisticsSql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return  q.uniqueResult();
    }

    @Override
    public Object executeStatisticsSql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return q.uniqueResult();
    }

    @Override
    public Long countBySql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return ((BigInteger) q.uniqueResult() ).longValue();
    }

    @Override
    public Long countBySql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                Object value =  entry.getValue();
                if(value.getClass().isArray() ){
                    //是数组
                    q.setParameterList(entry.getKey().toString(), (Object[])value);
                }else if( Collection.class.isAssignableFrom(value.getClass()) ){
                    //是集合
                    q.setParameterList(entry.getKey().toString(), (Collection)value);
                }else{
                    q.setParameter(entry.getKey().toString(), value);
                }
            }
        }
        return ((BigInteger) q.uniqueResult() ).longValue();
    }

    @Override
    public T merge(T o) {
        return (T)this.getCurrentSession().merge(o);
    }

}
