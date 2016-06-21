package com.web.util;


import com.web.exception.DaoException;
import net.sf.oval.internal.util.ReflectionUtils;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 21.06.16.
 */
public final class JpaUtils {
    private JpaUtils() {

    }

    public static String getParametersForList(List<Long> list) {
        StringBuilder parameters = new StringBuilder();
        String valueSeparator = ",";
        Integer lastElement = list.size() - 1;

        for (int i = 0; i < list.size(); i++) {
            parameters.append(list.get(i));
            if (i < lastElement) parameters.append(valueSeparator);
        }

        return parameters.toString();
    }

    public static <T> List<T> getMappingResultListWithEntityAndPagination(Class<T> klazz,
                                                                          String jpqlQuery,
                                                                          EntityManager em,
                                                                          Integer from,
                                                                          Integer countElements,
                                                                          Object... parameters) {
        List<T> listResult = new ArrayList<T>();
        Query result = em.createNativeQuery(jpqlQuery, klazz);

        if (from != null && countElements != null) {
            result.setFirstResult(from);
            result.setMaxResults(countElements);
        }

        setQueryParameter(result, parameters);

        return processJpqlResultList(klazz, listResult, result);
    }

    public static <T> List<T> getJpqlResultList(Class<T> klazz,
                                                String jpqlQuery,
                                                EntityManager em,
                                                Object... parameters) {
        List<T> listResult = new ArrayList<T>();
        Query result = em.createNativeQuery(jpqlQuery);

        setQueryParameter(result, parameters);

        return processJpqlResultList(klazz, listResult, result);
    }

    public static <T> List<T> getNamedQueryResultList(Class<T> klazz, String jpqlQuery, EntityManager em, Map<String, ?> params) {
        List<T> listResult = new ArrayList<T>();
        Query result = em.createNativeQuery(jpqlQuery);

        for (String paramName : params.keySet()) {
            result.setParameter(paramName, params.get(paramName));
        }

        return processJpqlResultList(klazz, listResult, result);
    }

    public static <T> List<T> getMappingResultListWithEntity(Class<T> klazz,
                                                             String jpqlQuery,
                                                             EntityManager em,
                                                             Object... parameters) {
        return getMappingResultListWithEntityAndPagination(klazz, jpqlQuery, em, null, null, parameters);
    }

    public static <T> T getSingleJpqlResultWithEntity(Class<T> klazz, String jpqlQuery, EntityManager em, Object... parameters) {
        Query result = em.createNativeQuery(jpqlQuery, klazz);
        setQueryParameter(result, parameters);

        return processSingleResult(klazz, result);
    }

    public static Integer countQueryExecute(String query, EntityManager em) {
        Query result = em.createNativeQuery(query);
        List objects = result.getResultList();
        return (Integer) objects.get(0);
    }

    public static <T> List<T> getMappingJpqlResultListWithoutEntity(Class<T> klazz,
                                                                    String jpqlQuery,
                                                                    EntityManager em,
                                                                    Object... parameters) throws DaoException {
        List<T> listResult = new ArrayList<T>();
        Connection cnn;
        try {
            cnn = getConnection(em);
            ResultSet rs = executeQuery(cnn, jpqlQuery, parameters);
            ResultSetMetaData rsm = rs.getMetaData();
            int allColumnCount = rsm.getColumnCount();
            while (rs.next()) {
                listResult.add(fillModel(klazz, allColumnCount, rs, rsm));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return listResult;
    }

    public static <T> T getMappingJpqlResultWithoutEntity(Class<T> klazz,
                                                          String jpqlQuery,
                                                          EntityManager em,
                                                          Object... parameters) throws DaoException {
        Connection cnn;
        T wrap = null;
        try {
            cnn = getConnection(em);
            ResultSet rs = executeQuery(cnn, jpqlQuery, parameters);
            ResultSetMetaData rsm = rs.getMetaData();
            int allColumnCount = rsm.getColumnCount();
            if (rs.next()) {
                wrap = fillModel(klazz, allColumnCount, rs, rsm);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return wrap;
    }

    private static void setQueryParameter(Query result, Object... parameters) {
        Integer parameterNumber = 1;
        for (Object parameter : parameters) {
            result.setParameter(parameterNumber, parameter);
            parameterNumber++;
        }
    }

    private static <T> T fillModel(Class<T> klazz,
                                   Integer allColumnCount,
                                   ResultSet rs,
                                   ResultSetMetaData rsm) throws DaoException {
        int columnCount = 1;
        T wrap;
        try {
            wrap = klazz.newInstance();
            while (allColumnCount >= columnCount) {
                Field field = wrap.getClass().getDeclaredField(rsm.getColumnName(columnCount));
                field.setAccessible(true);

                Object value = rs.getObject(rsm.getColumnName(columnCount));
                field.set(wrap, value);

                columnCount++;
            }
        } catch (IllegalAccessException e) {
            throw new DaoException(e);
        } catch (InstantiationException e) {
            throw new DaoException(e);
        } catch (NoSuchFieldException e) {
            throw new DaoException(e);
        } catch (SQLException e) {
            throw new DaoException(e);
        }


        return wrap;
    }

    private static ResultSet executeQuery(Connection cnn,
                                          String query,
                                          Object... parameters) throws SQLException {
        if (parameters != null) {
            PreparedStatement st = createPreparedStatement(cnn, query, parameters);
            return st.executeQuery();
        } else {
            Statement st = cnn.createStatement();
            return st.executeQuery(query);
        }

    }

    private static Connection getConnection(EntityManager em) {
        Session hibernateSession = em.unwrap(Session.class);
        SessionImpl sessionImpl = (SessionImpl) hibernateSession;

        return sessionImpl.connection();
    }

    private static PreparedStatement createPreparedStatement(Connection cnn,
                                                             String jpqlQuery,
                                                             Object... parameters) throws SQLException {
        PreparedStatement st = cnn.prepareStatement(jpqlQuery);
        Integer parameterNumber = 1;
        for (Object parameter : parameters) {
            st.setObject(parameterNumber, parameter);
            parameterNumber++;
        }

        return st;
    }

    private static <T> List<T> processJpqlResultList(Class<T> klazz, List<T> listResult, Query result) {
        List resultSet = null;
        try {
            resultSet = result.getResultList();
        } catch (NoResultException ignore) {

        }
        if (resultSet != null) {
            for (Object row : resultSet) {
                listResult.add(klazz.cast(row));
            }

            return listResult;
        } else {
            return null;
        }
    }

    private static <T> T processSingleResult(Class<T> klazz, Query result) {
        Object singleResult = null;

        try {
            singleResult = result.getSingleResult();
        } catch (NoResultException ignore) {

        }

        if (singleResult != null) {
            return klazz.cast(singleResult);
        } else {
            return null;
        }
    }

    public static String[] modelIntoStringArray(Object wrapper, String[] fieldNames) {
        String[] result = new String[fieldNames.length];

        for (int i = 0; i < fieldNames.length; i++) {
            Field field = ReflectionUtils.getField(wrapper.getClass(), fieldNames[i]);
            if (field != null) {

                Object fieldValue = ReflectionUtils.getFieldValue(field, wrapper);
                if (fieldValue == null) {
                    result[i] = null;
                } else {
                    result[i] = fieldValue.toString();
                }

            }

        }
        return result;
    }
}
