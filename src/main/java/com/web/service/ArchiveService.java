package com.web.service;

import com.web.exception.DaoException;
import com.web.wrapper.response.DataCurrentWrapper;

/**
 * Created on 21.06.16.
 */
public interface ArchiveService {
    DataCurrentWrapper getCurrentViewData() throws DaoException;
}
