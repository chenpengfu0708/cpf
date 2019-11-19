package com.hengtong.led.jpaAno;

import com.hengtong.led.jpaAno.annotation.*;
import com.hengtong.led.jpaAno.dto.MyResultDto;
import com.hengtong.led.jpaAno.dto.PageParam;
import com.hengtong.led.utils.MyStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class TestRepository<R, S> {

    @PersistenceContext
    private EntityManager em;


    /**
     * @param r:入参
     * @param s:响应
     */
    public MyResultDto test(S s, R r, PageParam pageParam) {
        MyResultDto resultDto = new MyResultDto();
        StringBuilder selectStr = new StringBuilder();
        StringBuilder countStr = new StringBuilder("select count(*)");

        Class resultClass = s.getClass();
        Class requestClass = r.getClass();

        Field[] resultFields = resultClass.getDeclaredFields();

        //构造响应返回参数
        selectStr.append(getResultStr(resultFields));

        Field[] requestFields = requestClass.getDeclaredFields();

        //构造联表语句
        getLeftJoinStr(selectStr, countStr, requestClass);

        //构造条件语句
        getWhereStr(requestFields, selectStr, countStr, r);

        //构造排序
        getOrder(resultFields, selectStr);

        //构造分页
        getPage(pageParam, selectStr);

        Query selectQuery = em.createNativeQuery(selectStr.toString());
        Query countQuery = em.createNativeQuery(countStr.toString());
        System.out.println(selectStr);
        System.out.println(countStr);
        selectQuery.unwrap(NativeQuery.class)
                .setResultTransformer(new AliasToBeanResultTransformer(resultClass));
        Long total = ((BigInteger) countQuery.getSingleResult()).longValue();
        resultDto.setData((List<S>) selectQuery.getResultList());
        resultDto.setTotal(total);
        return resultDto;
    }


    /**
     * 构造响应返回参数
     */
    private String getResultStr(Field[] fields) {

        StringBuilder str = new StringBuilder();

        boolean isIgnore = false;

        str.append("select ");
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(MyIgnore.class)) {
                isIgnore = true;
                continue;
            }
            isIgnore = false;
            if (fields[i].isAnnotationPresent(MyResult.class)) {
                MyResult result = fields[i].getAnnotation(MyResult.class);
                String otherName = "";
                if (StringUtils.isNotBlank(result.table())) {
                    otherName = result.table() + ".";
                }
                str.append(otherName);
                if (i == fields.length - 1) {
                    str.append(result.name() + " as " + fields[i].getName());
                } else {
                    str.append(result.name() + " as " + fields[i].getName() + ",");
                }
            } else {
                if (i == fields.length - 1) {
                    str.append(MyStringUtil.toLowerCase(fields[i].getName()) + " as " + fields[i].getName());
                } else {
                    str.append(MyStringUtil.toLowerCase(fields[i].getName()) + " as " + fields[i].getName() + ",");
                }
            }
        }
        if (isIgnore) {
            str.deleteCharAt(str.length() - 1);
        }

        return str.toString();
    }


    /**
     * 构造联表语句
     */
    private void getLeftJoinStr(StringBuilder selectStr, StringBuilder countStr, Class requestClass) {
        StringBuilder str = new StringBuilder();
        MyTable param = (MyTable) requestClass.getAnnotation(MyTable.class);
        MyLeftJoin leftJoins = (MyLeftJoin) requestClass.getAnnotation(MyLeftJoin.class);
        str.append(" from `" + param.table() + "` " + param.otherName());

        for (int i = 0; i < leftJoins.mainTableOtherName().length; i++) {
            str.append(" left join " + "`" + leftJoins.leftJoinTable()[i] + "` " + leftJoins.leftJoinTableOtherName()[i] +
                    " on " + leftJoins.mainTableOtherName()[i] + "." + leftJoins.mainTableColumnName()[i] + " = "
                    + leftJoins.leftJoinTableOtherName()[i] + "." + leftJoins.leftJoinTableColumnName()[i]);
        }
        selectStr.append(str);
        countStr.append(str);

    }


    /**
     * 构造条件查询语句
     */
    private void getWhereStr(Field[] requestFields, StringBuilder selectStr, StringBuilder countStr, R t) {
        StringBuilder str = new StringBuilder();
        str.append(" where 1=1 ");

        for (int i = 0; i < requestFields.length; i++) {
            String queryParam = "";
            try {
                requestFields[i].setAccessible(true);
                Object value = requestFields[i].get(t);
                if (value != null) {
                    queryParam = value.toString();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (StringUtils.isNotBlank(queryParam)) {
                if (requestFields[i].isAnnotationPresent(MyWhere.class)) {
                    MyWhere result = requestFields[i].getAnnotation(MyWhere.class);
                    str.append(" and " + result.tableOtherName() + "." + result.columnName());
                    if (result.isLike()) {
                        str.append(" like '%" + queryParam + "%' ");
                    } else if (result.isGreaterThan()) {
                        str.append(" > " + queryParam + " ");
                    } else if (result.isGreaterThanEqual()) {
                        str.append(" >= " + queryParam + " ");
                    } else if (result.isLessThan()) {
                        str.append(" < " + queryParam + " ");
                    } else if (result.isLessThanEqual()) {
                        str.append(" <= " + queryParam + " ");
                    } else {
                        str.append(" = " + queryParam + " ");
                    }
                }
            }
        }
        selectStr.append(str);
        countStr.append(str);
    }


    /**
     * 构造排序
     */
    private void getOrder(Field[] fields, StringBuilder selectStr) {
        Map<Integer, String> numMap = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(MyOrder.class)) {
                MyOrder result = fields[i].getAnnotation(MyOrder.class);
                numMap.put(result.num(), fields[i].getName() + " " + result.order().name() + ",");
            }
        }
        if (numMap.size() > 0) {
            selectStr.append(" order by ");
        }
        for (Integer order : numMap.keySet()) {
            selectStr.append(numMap.get(order));
        }
        if (numMap.size() > 0) {
            selectStr.deleteCharAt(selectStr.length() - 1);
        }
    }


    /**
     * 构造分页
     */
    public void getPage(PageParam pageParam, StringBuilder selectStr) {
        if (pageParam != null) {
            selectStr.append(" limit " + pageParam.getPageOffSet() + "," + pageParam.getCount());
        }
    }

}
