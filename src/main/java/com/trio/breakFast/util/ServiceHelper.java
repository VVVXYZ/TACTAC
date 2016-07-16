package com.trio.breakFast.util;


import com.trio.breakFast.dao.BaseDao;
import com.trio.breakFast.pageModel.FilterGroup;
import com.trio.breakFast.pageModel.FilterRule;
import com.trio.breakFast.pageModel.PageHelper;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceHelper {

    /**
     * @param filterGroup
     * @param dao        基础dao接口
     * @param modelClass 实体类的类型
     * @author loser
     * @date 2016/5/12
     * @des 是否存在
     */
    static public <T> Boolean isExist(BaseDao<T> dao, Class<T> modelClass, FilterGroup filterGroup) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = HqlUtil.getCountHql(modelClass, filterGroup, params);
        return dao.count(hql, params) > 0;
    }

    /**
     * @param property  字段
     * @param value  值
     * @param dao        基础dao接口
     * @param modelClass 实体类的类型
     * @author loser
     * @date 2016/5/12
     * @des 是否存在
     */
    static public <T> Boolean isExist(BaseDao<T> dao, Class<T> modelClass, String property, Object value) {
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.addRules(new FilterRule(property, HqlUtil.EQUAL, value));
        return isExist(dao,modelClass,filterGroup);
    }

    /**
     * @param id  id
     * @param property  字段
     * @param value  值
     * @param dao        基础dao接口
     * @param modelClass 实体类的类型
     * @author loser
     * @date 2016/5/12
     * @des 是否存在
     */
    static public <T> Boolean isExist(BaseDao<T> dao, Class<T> modelClass, Integer id,String property, Object value) {
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.addRules(new FilterRule("id", HqlUtil.NO_EQUAL, id));
        filterGroup.addRules(new FilterRule(property, HqlUtil.EQUAL, value));
        return isExist(dao,modelClass,filterGroup);
    }

    /**
     * @param object
     * @param dao        基础dao接口
     * @param modelClass 实体类的类型
     * @author loser
     * @date 2016/5/12
     * @des 新增
     */
    static public <T> Integer create(BaseDao<T> dao, Class<T> modelClass, T object) {

        Serializable id = dao.save(object);
        if (id != null) {
            return Integer.parseInt(id.toString());
        }
        return -1;
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 删除
     * 
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> void delete(BaseDao<T> dao, Class<T> modelClass, T object) {

        dao.delete(object);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 删除
     *
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> void delete(BaseDao<T> dao, Class<T> modelClass, Integer id) {

        Map<String, Object> params = new HashMap<String, Object>();

        FilterGroup filterGroup = new FilterGroup();
        filterGroup.addRules(new FilterRule("id", HqlUtil.EQUAL, id.toString()));

        String hql = HqlUtil.getDeleteHql(modelClass, filterGroup, params);

        dao.executeHql(hql, params);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 批量删除
     *
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> void deleteBatch(BaseDao<T> dao, Class<T> modelClass, String ids) {

        Map<String, Object> params = new HashMap<String, Object>();

        FilterGroup filterGroup = new FilterGroup();
        filterGroup.addRules(new FilterRule("id", HqlUtil.EQUAL, ids));

        String hql = HqlUtil.getDeleteHql(modelClass, filterGroup, params);

        dao.executeHql(hql, params);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 条件删除
     *
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     * @param filterGroup 过滤条件
     */
    static public <T> void delete(BaseDao<T> dao, Class<T> modelClass, FilterGroup filterGroup) {

        Map<String, Object> params = new HashMap<String, Object>();

        String hql = HqlUtil.getDeleteHql(modelClass, filterGroup, params);

        dao.executeHql(hql, params);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 合并
     *
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> T merge(BaseDao<T> dao, Class<T> modelClass, T object) {
        return (T) dao.merge(object);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 更新
     *
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> void update(BaseDao<T> dao, Class<T> modelClass, T object) {

        dao.update(object);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取单个对象
     *
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> T get(BaseDao<T> dao, Class<T> modelClass, Integer id) {

        return (T) dao.get(modelClass, id);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 过滤获取单个对象
     *
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> T get(BaseDao<T> dao, Class<T> modelClass, FilterGroup filterGroup) {

        Map<String, Object> params = new HashMap<String, Object>();
        String hql = HqlUtil.getQueryHql(modelClass, filterGroup, params);
        return (T) dao.get(hql, params);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取列表
     *
     * @param filterGroup 过滤条件
     * @param page 当前页码
     * @param rows 每页条数
     * @param sorters 排序属性
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> List<T> find(BaseDao<T> dao, Class<T> modelClass, FilterGroup filterGroup, Integer page, Integer rows, HqlUtil.Sorter... sorters) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = HqlUtil.getQueryHql(modelClass, filterGroup, params, sorters);
        return dao.find(hql, params, page, rows);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取列表
     *
     * @param page 当前页码
     * @param rows 每页条数
     * @param sorters 排序属性
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> List<T> find(BaseDao<T> dao, Class<T> modelClass, Integer page, Integer rows, HqlUtil.Sorter... sorters) {
        return find(dao, modelClass, null, page, rows, sorters);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取列表
     *
     * @param filterGroup 过滤条件
     * @param sorters 排序属性
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> List<T> find(BaseDao<T> dao, Class<T> modelClass, FilterGroup filterGroup, HqlUtil.Sorter... sorters) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = HqlUtil.getQueryHql(modelClass, filterGroup, params, sorters);

        return dao.find(hql, params);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取列表
     *
     * @param sorters 排序属性
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> List<T> find(BaseDao<T> dao, Class<T> modelClass, HqlUtil.Sorter... sorters) {
        return find(dao, modelClass, null, sorters);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取列表
     *
     * @param filterGroup 过滤条件
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> List<T> find(BaseDao<T> dao, Class<T> modelClass, FilterGroup filterGroup) {

        Map<String, Object> params = new HashMap<String, Object>();
        String hql = HqlUtil.getQueryHql(modelClass, filterGroup, params);
        return dao.find(hql, params);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取列表
     *
     * @param filterGroup 过滤条件
     * @param page 当前页码
     * @param rows 每页条数
     * @param sorters 排序属性
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> List<T> findVO(BaseDao dao, Class modelClass, Class<T> targetClass,Collection<String> propertys, FilterGroup filterGroup, Integer page, Integer rows, HqlUtil.Sorter... sorters) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = HqlUtil.getSelectHql(modelClass, targetClass, propertys, filterGroup, params, sorters);
        return dao.findVO(hql, params, page, rows);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取列表
     *
     * @param page 当前页码
     * @param rows 每页条数
     * @param sorters 排序属性
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> List<T> findVO(BaseDao dao, Class modelClass, Class<T> targetClass,Collection<String> propertys, Integer page, Integer rows, HqlUtil.Sorter... sorters) {
        return findVO(dao, modelClass,targetClass, propertys, null, page, rows, sorters);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取列表
     *
     * @param filterGroup 过滤条件
     * @param sorters 排序属性
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> List<T> findVO(BaseDao dao, Class modelClass, Class<T> targetClass,Collection<String> propertys, FilterGroup filterGroup, HqlUtil.Sorter... sorters) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = HqlUtil.getSelectHql(modelClass, targetClass, propertys, filterGroup, params, sorters);

        return dao.findVO(hql, params);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取列表
     *
     * @param sorters 排序属性
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> List<T> findVO(BaseDao dao, Class modelClass, Class<T> targetClass,Collection<String> propertys, HqlUtil.Sorter... sorters) {
        return findVO(dao, modelClass,targetClass, propertys, null, sorters);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取列表
     *
     * @param filterGroup 过滤条件
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> List<T> findVO(BaseDao dao, Class modelClass, Class<T> targetClass,Collection<String> propertys, FilterGroup filterGroup) {

        Map<String, Object> params = new HashMap<String, Object>();
        String hql = HqlUtil.getSelectHql(modelClass, targetClass, propertys, filterGroup, params);
        return dao.findVO(hql, params);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取数量
     *
     * @param filterGroup 过滤条件对象
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> Long count(BaseDao<T> dao, Class<T> modelClass, FilterGroup filterGroup) {


        Map<String, Object> params = new HashMap<String, Object>();
        String hql = HqlUtil.getCountHql(modelClass, filterGroup, params);

        return dao.count(hql, params);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 获取数量
     *
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> Long count(BaseDao<T> dao, Class<T> modelClass) {
        return count(dao, modelClass, null);
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 分页查询
     *
     * @param filterGroup 过滤条件
     * @param page 当前页码
     * @param rows 每页条数
     * @param sorters 排序属性
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> PageHelper<T> findByPage(BaseDao<T> dao, Class<T> modelClass, FilterGroup filterGroup, Integer page, Integer rows, HqlUtil.Sorter... sorters) {
        PageHelper<T> pageHelper = new PageHelper<T>();

        page = page != null && page > 0 ? page : 1;
        pageHelper.setPage(page);
        rows = rows != null && rows > 0 ? rows : 10;
        pageHelper.setRows(rows);
        if(sorters.length > 0) {
            pageHelper.setOrder(sorters[0].getOrder());
            pageHelper.setSort(sorters[0].getSort());
        }

        pageHelper.setDataList(find(dao, modelClass, filterGroup, page, rows, sorters));
        pageHelper.setTotalRows(count(dao, modelClass, filterGroup).intValue());
        pageHelper.countTotalPages();
        pageHelper.setMessage("查询成功");

        return pageHelper;
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 分页查询
     *
     * @param filterGroup 过滤条件
     * @param page 当前页码
     * @param rows 每页条数
     * @param sort 排序属性
     * @param order 排序方式
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> PageHelper<T> findByPage(BaseDao<T> dao, Class<T> modelClass, FilterGroup filterGroup, Integer page, Integer rows, String sort, String order) {
        return findByPage(dao,modelClass,filterGroup,page,rows,new HqlUtil.Sorter(sort, order));
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 分页查询
     *
     * @param filterGroup 过滤条件
     * @param page 当前页码
     * @param rows 每页条数
     * @param sorters 排序属性
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> PageHelper<T> findVOByPage(BaseDao dao, Class modelClass, Class<T> targetClass,Collection<String> propertys, FilterGroup filterGroup, Integer page, Integer rows, HqlUtil.Sorter... sorters) {
        PageHelper<T> pageHelper = new PageHelper<T>();

        page = page != null && page > 0 ? page : 1;
        pageHelper.setPage(page);
        rows = rows != null && rows > 0 ? rows : 10;
        pageHelper.setRows(rows);
        if(sorters.length > 0) {
            pageHelper.setOrder(sorters[0].getOrder());
            pageHelper.setSort(sorters[0].getSort());
        }

        pageHelper.setDataList(findVO(dao, modelClass,targetClass, propertys, filterGroup, page, rows, sorters));
        pageHelper.setTotalRows(count(dao, modelClass, filterGroup).intValue());
        pageHelper.countTotalPages();
        pageHelper.setMessage("查询成功");

        return pageHelper;
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 分页查询
     *
     * @param filterGroup 过滤条件
     * @param page 当前页码
     * @param rows 每页条数
     * @param sort 排序属性
     * @param order 排序方式
     * @param  dao 基础dao接口
     * @param modelClass 实体类的类型
     */
    static public <T> PageHelper<T> findVOByPage(BaseDao dao, Class modelClass, Class<T> targetClass,Collection<String> propertys, FilterGroup filterGroup, Integer page, Integer rows, String sort, String order) {
        return findVOByPage(dao, modelClass,targetClass, propertys, filterGroup, page, rows, new HqlUtil.Sorter(sort, order));
    }
}
