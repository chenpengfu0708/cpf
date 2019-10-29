package com.hengtong.led.jpa.repository;

import com.hengtong.led.jpa.dto.JpaDto;
import com.hengtong.led.jpa.dto.JpaRequestDto;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

public class JpaEntityExtRepositoryImpl implements JpaEntityExtRepository {

    @PersistenceContext
    private EntityManager em;

    private Query searchCommon(String querySQL, JpaRequestDto request, boolean isCount) {
        querySQL += " from jpa_entity where 1=1 ";

        if (StringUtils.isNotBlank(request.getName())) {
            querySQL += " and `name` like :name ";
        }
        if (!isCount) {
            querySQL += " order by id desc Limit :start, :count";
        }
        Query query = em.createNativeQuery(querySQL);

        if (StringUtils.isNotBlank(request.getName())) {
            query.setParameter("name", "%" + request.getName() + "%");
        }
        return query;
    }


    @Override
    public List<JpaDto> findByCondition(JpaRequestDto request) {
        String querySQL = "select id as id,name as name ";
        Query query = searchCommon(querySQL, request, false);
        query.setParameter("start", request.getStart());
        query.setParameter("count", request.getCount());
        query.unwrap(NativeQuery.class)
                .setResultTransformer(new AliasToBeanResultTransformer(JpaDto.class));
        return (List<JpaDto>) query.getResultList();
    }


    @Override
    public Long countByCondition(JpaRequestDto request) {
        String querySQL = "select count(*) ";
        Query query = searchCommon(querySQL, request, true);
        return ((BigInteger) query.getSingleResult()).longValue();
    }

}
