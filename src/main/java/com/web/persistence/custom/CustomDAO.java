package com.web.persistence.custom;


import com.web.entity.EventType;
import com.web.exception.DaoException;
import com.web.util.JpaUtils;
import com.web.wrapper.dao.TagTimestampWrapper;
import com.web.wrapper.response.DataCurrentWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created on 21.06.16.
 */
@Repository
public class CustomDAO {

    @PersistenceContext
    private EntityManager em;

    @Value("${JPQL.CURRENT.DATA}")
    private String JPQL_CURRENT_DATA;

    @Value("${JPQL.FIRST.EVENT.TODAY}")
    private String JPQL_FIRST_EVENT_TODAY;

    @Value("${JPQL.LAST.EVENT.TODAY}")
    private String JPQL_LAST_EVENT_TODAY;


    @Transactional
    public DataCurrentWrapper getCurrentDataWrapper() throws DaoException {
        return JpaUtils.getMappingJpqlResultWithoutEntity(DataCurrentWrapper.class, JPQL_CURRENT_DATA, em);
    }

    @Transactional
    public TagTimestampWrapper getTimeLineOnToday() throws DaoException {
        return JpaUtils.getMappingJpqlResultWithoutEntity(TagTimestampWrapper.class, JPQL_FIRST_EVENT_TODAY, em, EventType.LINE_ON.name());
    }

    @Transactional
    public TagTimestampWrapper getTimeLineOffToday() throws DaoException {
        return JpaUtils.getMappingJpqlResultWithoutEntity(TagTimestampWrapper.class, JPQL_LAST_EVENT_TODAY, em, EventType.LINE_OFF.name());
    }

    @Transactional
    public DataCurrentWrapper getTotalTimeWorkWithMaterialOnToday() throws DaoException {
        return JpaUtils.getMappingJpqlResultWithoutEntity(DataCurrentWrapper.class, JPQL_CURRENT_DATA, em);
    }

    @Transactional
    public DataCurrentWrapper getLineDownTimeToday() throws DaoException {
        return JpaUtils.getMappingJpqlResultWithoutEntity(DataCurrentWrapper.class, JPQL_CURRENT_DATA, em);
    }

}
