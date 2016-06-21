package com.web.service.imp;

import com.web.entity.Token;
import com.web.entity.User;
import com.web.exception.DaoException;
import com.web.persistence.TokenRepository;
import com.web.persistence.UserRepository;
import com.web.service.CustomTokensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * Created on 08.09.15.
 */
@Service
public class CustomTokensServiceImpl implements CustomTokensService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Token allocateToken(String username) throws DaoException {

        Token token = tokenRepository.findTokenByUsername(username);

        if (token == null) {
            User user = userRepository.findUserByUsername(username);

            token = new Token();
            token.setUser(user);
            token.setDate(new Date());
            token.setTokenValue(getNewToken());
        }

        return tokenRepository.save(token);

    }

    @Override
    public Token verifyToken(String token) {
        return tokenRepository.findTokenByTokenValue(token);
    }

    @Override
    public Token isTokenExist(String username) {
        return tokenRepository.findTokenByUsername(username);
    }

    private String getNewToken() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();

    }
}
