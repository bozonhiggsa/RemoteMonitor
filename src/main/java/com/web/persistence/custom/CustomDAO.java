package com.web.persistence.custom;


import com.web.exception.DaoException;
import com.web.util.JpaUtils;
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

    @Transactional
    public DataCurrentWrapper getDCurrentDataWrapper() throws DaoException {
        return JpaUtils.getMappingJpqlResultWithoutEntity(DataCurrentWrapper.class, JPQL_CURRENT_DATA, em);
    }

}
