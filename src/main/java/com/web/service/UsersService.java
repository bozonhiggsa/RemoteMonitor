package com.web.service;


import com.web.entity.User;
import com.web.entity.UsersAuthoritie;

import java.util.List;

/**
 * Created on 03.09.15.
 */
public interface UsersService {

    User getUserByName(String userName);

    Boolean checkPassword(String userName, String password);

    List<UsersAuthoritie> findUserAuthorities(String username);
}
