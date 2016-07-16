package com.trio.vmalogo.util;


import com.google.common.base.Strings;
import com.trio.vmalogo.pageModel.FilterGroup;
import com.trio.vmalogo.pageModel.FilterRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.ConvertUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * changed by loser on 2016/5/12
 */
public class HqlUtil {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Sorter {
        private String sort = "id";
        private String order = HqlUtil.ASC;
    }

    public static final String EQUAL = "eq"; //等于
    public static final String NO_EQUAL = "ne";//不等于
    public static final String NO_BEGIN_WITH = "bn";//不开始于
    public static final String LESS_THAN = "lt";//小于
    public static final String END_WITH = "ew";//结束于
    public static final String LESS_EQUAL = "le";//小于等于
    public static final String NO_END_WITH = "wn"; //不结束于
    public static final String GREATER_THAN = "gt"; //大于
    public static final String BEGIN_WITH = "bw"; //开始于
    public static final String GREATER_EQUAL = "ge";//大于等于
    public static final String IN = "in";//属于
    public static final String NO_IN = "ni";//不属于
    public static final String CONTAIN = "cn";//包含
    public static final String NO_CONTAIN = "nc";//不包含
    public static final String NULL = "nu";//为空
    public static final String NO_NULL = "nn";//不为空

    public static final String ASC = "ASC"; //升序
    public static final String DESC = "DESC";//降序

    /**
     * @author loser
     * @date 2016/5/12
     * @des 清理filterGroup
     */
    public static FilterGroup cleanFilterGroup(FilterGroup filterGroup) {

        //本身为空返回
        if (filterGroup == null) {
            return null;
        }

        //清理groups数组中的filterGroup
        List<FilterGroup> groups = filterGroup.getGroups();
        if (groups != null) {
            for (int i = 0; i < groups.size(); i++) {
                FilterGroup innerFilterGroup = groups.get(i);
                innerFilterGroup = cleanFilterGroup(innerFilterGroup);
                if (innerFilterGroup != null) {
                    groups.set(i, innerFilterGroup);
                } else {
                    groups.remove(i);
                    i--;
                }
            }
        }

        //集合数组长度为空引用设为空
        if (filterGroup.getRules() != null && filterGroup.getRules().size() == 0) {
            filterGroup.setRules(null);
        }
        //集合数组长度为空引用设为空
        if (filterGroup.getGroups() != null && filterGroup.getGroups().size() == 0) {
            filterGroup.setGroups(null);
        }

        //无搜索条件  该过滤条件为空
        if (filterGroup.getRules() == null && filterGroup.getGroups() == null) {
            return null;
        }

        return filterGroup;
    }

    /**
     * @author loser
     * @date 2016/5/12
     * @des 将搜索条件中的变量名改成唯一变量名
     */
    private static String getUniqueVarName(String fullName) {
        String varName = null;
        if (Pattern.compile("[0-9a-zA-Z.]*").matcher(fullName).matches()) {
            //丢弃空元素
            String[] fullNameSpiltArray = fullName.split("\\.");
            //获取最后一个元素名
            varName = fullNameSpiltArray[fullNameSpiltArray.length - 1];
        } else {
            varName = "calculateResult";
        }
        //加上UUID成为唯一名称
        return varName + UUID.randomUUID().toString().replace("-", "");
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des  根据类型和字段全名改变值的类型
    * */
    public static Object typeCast(Class modelClass, String fullName, Object value) {

        //丢弃空元素
        String[] fullNameSpiltArray = fullName.split("\\.");
        if (fullName.equals("id")) {
            modelClass = Integer.class;
        } else {
            //通过反射找出属性类型
            for (String name : fullNameSpiltArray) {
                try {
                    modelClass = modelClass.getDeclaredField(name).getType();
                } catch (Exception e) {
                    modelClass = null;
                    break;
                }
            }
        }
        //转换类型
        if (modelClass == null || modelClass.equals(value.getClass())) {
            return value;
        } else if (modelClass.isEnum()) {
            for (Enum e : ((Class<Enum>) modelClass).getEnumConstants()) {
                if (e.name().equals(value)) {
                    return e;
                }
            }
            return value;
        } else if (Date.class.isAssignableFrom(modelClass)) {

            Date date = DateUtil.string2Date(value.toString());
            if (date == null) {
                return value;
            } else {
                return date;
            }
        } else if (BigDecimal.class.isAssignableFrom(modelClass)) {
            BigDecimal bigDecimal = new BigDecimal(value.toString());
            bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
            return bigDecimal;
        } else {
            return ConvertUtils.convert(value, modelClass);
        }
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des  将in和not in中的value改为list
    * */
    public static List paramsStringToList(Class modelClass, String fullName, String paramsString) {
        List valueList = new ArrayList<Object>();

        //丢弃空元素
        String[] valueArray = paramsString.split(",");

        //将元素加入list
        for (String value : valueArray) {
            //转换类型后加入
            valueList.add(typeCast(modelClass, fullName, value));
        }

        return valueList;
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 往hql语句中添加一组参数
    * */
    public static StringBuilder appendParameterGroup(Class modelClass, StringBuilder whereCondition, FilterGroup filterGroup, Map<String, Object> params, StringBuilder prefix) {

        if (filterGroup == null) {
            return whereCondition;
        }

        whereCondition.append(" ( ");

        //添加条件
        if (filterGroup.getRules() != null) {
            for (FilterRule filterRule : filterGroup.getRules()) {
                whereCondition = appendParameter(modelClass, whereCondition, filterRule, params, prefix).append(' ').append(filterGroup.getGroupOp()).append(" ");
            }
        }

        //添加条件组
        if (filterGroup.getGroups() != null) {
            for (FilterGroup innerFilterGroup : filterGroup.getGroups()) {
                whereCondition = appendParameterGroup(modelClass, whereCondition, innerFilterGroup, params, prefix).append(' ').append(filterGroup.getGroupOp()).append(" ");
            }
        }

        whereCondition.setLength(whereCondition.length() - filterGroup.getGroupOp().length() - 1);

        whereCondition.append(" ) ");

        return whereCondition;
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 往hql语句中添加一个参数
    * */
    public static StringBuilder appendParameter(Class modelClass, StringBuilder whereCondition, FilterRule filterRule, Map<String, Object> params, StringBuilder prefix) {

        String op = filterRule.getOp();
        String filed = filterRule.getField();
        String uniqueFiled = getUniqueVarName(filed);
        Object value = filterRule.getData();

        switch (op) {

            //等于
            case EQUAL:
                op = " = ";
                break;

            //不等于
            case NO_EQUAL:
                op = " != ";
                break;

            //不开始于
            case NO_BEGIN_WITH:
                //小于
            case LESS_THAN:
                op = " < ";
                break;

            //结束于
            case END_WITH:
                //小于等于
            case LESS_EQUAL:
                op = " <= ";
                break;

            //不结束于
            case NO_END_WITH:
                //大于
            case GREATER_THAN:
                op = " > ";
                break;

            //开始于
            case BEGIN_WITH:
                //大于等于
            case GREATER_EQUAL:
                op = " >= ";
                break;

            //属于
            case IN:
                op = " in ";
                break;

            //不属于
            case NO_IN:
                op = " not in ";
                break;

            //包含
            case CONTAIN:
                op = " like ";
                break;

            //不包含
            case NO_CONTAIN:
                op = " not like ";
                break;

            //判断是否非空作特殊处理

            //为空
            case NULL:
                whereCondition.append(prefix).append(filed).append(" is null ");
                return whereCondition;

            //不为空
            case NO_NULL:
                whereCondition.append(prefix).append(filed).append(" is not null ");
                return whereCondition;

            default:
                return whereCondition;
        }

        if (filterRule.getOp().equals(IN) || filterRule.getOp().equals(NO_IN)) {
            whereCondition.append(prefix).append(filed).append(op).append("(:").append(uniqueFiled).append(") ");
        } else {
            whereCondition.append(prefix).append(filed).append(op).append(':').append(uniqueFiled).append(' ');
        }

        if (filterRule.getOp().equals(IN) || filterRule.getOp().equals(NO_IN)) {
            //in和ni操作的值转化为list
            params.put(uniqueFiled, paramsStringToList(modelClass, filed, value.toString()));
        } else if (filterRule.getOp().equals("cn") || filterRule.getOp().equals("nc")) {
            //模糊查询
            params.put(uniqueFiled, "%" + typeCast(modelClass, filed, value) + "%");
        } else {
            params.put(uniqueFiled, typeCast(modelClass, filed, value));
        }

        return whereCondition;
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到查询记录语句头
    * */
    public static StringBuilder getQueryHqlHead(Class modelClass) {
        return getQueryHqlHead(null, modelClass);
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到查询记录语句头
    * */
    public static StringBuilder getQueryHqlHead(String alias, Class modelClass) {
        StringBuilder queryHqlHead = new StringBuilder("from ").append(modelClass.getName()).append(' ');
        if (Strings.isNullOrEmpty(alias)) {
            alias = "t";
        }
        queryHqlHead.append(alias).append(' ');

        return queryHqlHead;
    }


    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到删除记录语句头
    * */
    public static StringBuilder getDeleteHqlHead(Class modelClass) {
        return getDeleteHqlHead(null, modelClass);
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到删除记录语句头
    * */
    public static StringBuilder getDeleteHqlHead(String alias, Class modelClass) {
        return new StringBuilder("delete ").append(getQueryHqlHead(alias, modelClass));
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到查询记录条数语句头
    * */
    public static StringBuilder getCountHqlHead(Class modelClass) {
        return getCountHqlHead(null, modelClass);
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到查询记录条数语句头
    * */
    public static StringBuilder getCountHqlHead(String alias, Class modelClass) {
        return new StringBuilder("select count(*) ").append(getQueryHqlHead(alias, modelClass));
    }

    /*
* @author loser
* @date 2016/5/12
* @des 得到查询记录条数语句头
* */
    public static StringBuilder getSelectHqlHead(Class modelClass, Class targetClass,Collection<String> propertys) {
        return getSelectHqlHead(null, modelClass,targetClass, propertys);
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到查询记录条数语句头
    * */
    public static StringBuilder getSelectHqlHead(String alias, Class modelClass, Class targetClass,Collection<String> propertys) {
        StringBuilder selectHqlHead = new StringBuilder("select new ").append(targetClass.getName()).append('(');

        if (Strings.isNullOrEmpty(alias)) {
            alias = "t";
        }

        for(String property: propertys){
            selectHqlHead.append(alias).append('.').append(property).append(',');
        }
        selectHqlHead.setLength(selectHqlHead.length()-1);
        selectHqlHead.append(") ");

        selectHqlHead.append(getQueryHqlHead(alias, modelClass));

        return  selectHqlHead;
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到条件语句
    * */
    public static StringBuilder getWhereCondition(Class modelClass, FilterGroup filterGroup, Map<String, Object> params) {
        return getWhereCondition(null, modelClass, filterGroup, params);
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到条件语句
    * */
    public static StringBuilder getWhereCondition(String alias, Class modelClass, FilterGroup filterGroup, Map<String, Object> params) {
        StringBuilder prefix = new StringBuilder(' ');
        if (Strings.isNullOrEmpty(alias)) {
            alias = "t";
        }
        prefix.append(alias).append('.');

        StringBuilder whereCondition = new StringBuilder();
        filterGroup = cleanFilterGroup(filterGroup);

        if (filterGroup != null) {
            whereCondition = appendParameterGroup(modelClass, whereCondition.append(" where "), filterGroup, params, prefix);
        }

        return whereCondition;
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到排序语句
    * */
    public static StringBuilder getSortCondition(Sorter... sorters) {
        return getSortCondition(null, sorters);
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到排序语句
    * */
    public static StringBuilder getSortCondition(String alias, Sorter... sorters) {
        StringBuilder sortCondition = new StringBuilder();
        if (sorters.length == 0) {
            return sortCondition;
        }
        StringBuilder prefix = new StringBuilder(' ');
        if (Strings.isNullOrEmpty(alias)) {
            alias = "t";
        }
        prefix.append(alias).append('.');

        sortCondition.append(" order by ");

        for (Sorter sorter : sorters) {
            if(Strings.isNullOrEmpty(sorter.getSort())){
                sorter.setSort("id");
            }
            if(Strings.isNullOrEmpty(sorter.getOrder())){
                sorter.setOrder(HqlUtil.ASC);
            }
            sortCondition.append(prefix).append(sorter.getSort()).append(' ').append(sorter.getOrder()).append(',');
        }

        sortCondition.setLength(sortCondition.length() - 1);

        return sortCondition;
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到查询记录语句
    * */
    public static String getQueryHql(Class modelClass, FilterGroup filterGroup, Map<String, Object> params) {
        return getQueryHql(null, modelClass, filterGroup, params);
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到查询记录语句
    * */
    public static String getQueryHql(Class modelClass, FilterGroup filterGroup, Map<String, Object> params, Sorter... sorters) {
        return getQueryHql(null, modelClass, filterGroup, params, sorters);
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到查询记录语句
    * */
    public static String getQueryHql(String alias, Class modelClass, FilterGroup filterGroup, Map<String, Object> params, Sorter... sorters) {
        StringBuilder hql = getQueryHqlHead(alias, modelClass);

        hql.append(getWhereCondition(alias, modelClass, filterGroup, params))
                .append(getSortCondition(alias, sorters));

        return hql.toString();
    }

    /*
        * @author loser
        * @date 2016/5/12
        * @des 得到查询记录语句
        * */
    public static String getSelectHql(Class modelClass, Class targetClass,Collection<String> propertys, FilterGroup filterGroup, Map<String, Object> params) {
        return getSelectHql(null, modelClass,targetClass, propertys, filterGroup, params);
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到查询记录语句
    * */
    public static String getSelectHql(Class modelClass, Class targetClass,Collection<String> propertys, FilterGroup filterGroup, Map<String, Object> params, Sorter... sorters) {
        return getSelectHql(null, modelClass,targetClass, propertys, filterGroup, params, sorters);
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到查询记录语句
    * */
    public static String getSelectHql(String alias, Class modelClass, Class targetClass,Collection<String> propertys, FilterGroup filterGroup, Map<String, Object> params, Sorter... sorters) {
        StringBuilder hql = getSelectHqlHead(alias, modelClass,targetClass, propertys);

        hql.append(getWhereCondition(alias, modelClass, filterGroup, params))
                .append(getSortCondition(alias, sorters));

        return hql.toString();
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到删除记录语句
    * */
    public static String getDeleteHql(Class modelClass, FilterGroup filterGroup, Map<String, Object> params) {
        return getDeleteHql(null, modelClass, filterGroup, params);
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到删除记录语句
    * */
    public static String getDeleteHql(String alias, Class modelClass, FilterGroup filterGroup, Map<String, Object> params) {
        StringBuilder hql = getDeleteHqlHead(alias, modelClass);

        hql.append(getWhereCondition(alias, modelClass, filterGroup, params));

        return hql.toString();
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到查询记录条数语句
    * */
    public static String getCountHql(Class modelClass, FilterGroup filterGroup, Map<String, Object> params) {
        return getCountHql(null, modelClass, filterGroup, params);
    }

    /*
    * @author loser
    * @date 2016/5/12
    * @des 得到查询记录条数语句
    * */
    public static String getCountHql(String alias, Class modelClass, FilterGroup filterGroup, Map<String, Object> params) {
        StringBuilder hql = getCountHqlHead(alias, modelClass);

        hql.append(getWhereCondition(alias, modelClass, filterGroup, params));

        return hql.toString();
    }
}
