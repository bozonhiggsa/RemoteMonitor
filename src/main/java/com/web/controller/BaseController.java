package com.web.controller;

import com.web.wrapper.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

/**
 * Created on 02.09.15.
 */
@Controller
public class BaseController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Errors handleException(Exception ex) {
        Errors errors = new Errors();
        errors.setMessage(ex.getMessage());
        errors.setStackTrace(Arrays.toString(ex.getStackTrace()));
        return errors;
    }


}
