package com.web.service;

import com.web.entity.Token;
import com.web.exception.DaoException;

/**
 * Created on 08.09.15.
 */
public interface CustomTokensService {

    Token allocateToken(String username) throws DaoException;

    Token verifyToken(String token);

    Token isTokenExist(String username);

    void deleteUserToken();
}
