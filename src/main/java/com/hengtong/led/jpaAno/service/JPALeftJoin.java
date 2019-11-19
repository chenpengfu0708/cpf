package com.hengtong.led.jpaAno.service;

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
public class JPALeftJoin<R, S> {

    @PersistenceContext
    private EntityManager em;


    /**
     * @param r:入参
     * @param s:响应
     */
    public MyResultDto test(S s, R r, PageParam pageParam) {
        MyResultDto resultDto = new MyResultDto();
        StringBuilder selectStr = new StringBuilder("select ");
        StringBuilder countStr = new StringBuilder("select count(*)");

        Class resultClass = s.getClass();
        Class requestClass = r.getClass();

        Field[] resultFields = resultClass.getDeclaredFields();
        Field[] requestFields = requestClass.getDeclaredFields();

        MySelect select = (MySelect) requestClass.getAnnotation(MySelect.class);
        MyFrom myFrom = (MyFrom) requestClass.getAnnotation(MyFrom.class);
        MyLeftJoin myLeftJoin = (MyLeftJoin) requestClass.getAnnotation(MyLeftJoin.class);

        //构造响应返回参数
        selectStr.append(select.select());
        selectStr.append(" from ").append(myFrom.from());
        countStr.append(" from ").append(myFrom.from());
        selectStr.append(" left join ").append(myLeftJoin.leftJoin());
        countStr.append(" left join ").append(myLeftJoin.leftJoin());
        getWhereStr(requestFields, selectStr, countStr, r);

        //构造排序
        getOrder(resultFields, selectStr);

        //构造分页
        getPage(pageParam, selectStr);

        Query selectQuery = em.createNativeQuery(selectStr.toString());
        Query countQuery = em.createNativeQuery(countStr.toString());
        selectQuery.unwrap(NativeQuery.class)
                .setResultTransformer(new AliasToBeanResultTransformer(resultClass));
        Long total = ((BigInteger) countQuery.getSingleResult()).longValue();
        resultDto.setData((List<S>) selectQuery.getResultList());
        resultDto.setTotal(total);
        System.out.println("resultDto = " + resultDto);
        return resultDto;
    }


    /**
     * 构造条件查询语句
     */
    private void getWhereStr(Field[] requestFields, StringBuilder selectStr, StringBuilder countStr, R r) {
        StringBuilder str = new StringBuilder();
        str.append(" where 1=1 ");

        for (int i = 0; i < requestFields.length; i++) {
            String queryParam = "";
            try {
                requestFields[i].setAccessible(true);
                Object value = requestFields[i].get(r);
                if (value != null) {
                    queryParam = value.toString();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (StringUtils.isNotBlank(queryParam)) {
                if (requestFields[i].isAnnotationPresent(MyWhere.class)) {
                    MyWhere result = requestFields[i].getAnnotation(MyWhere.class);
                    String where = " and " + result.where();
                    String replace = ":" + requestFields[i].getName();
                    where = where.replace(replace, queryParam);
                    str.append(where);
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
