package com.web.service.imp;

import com.web.exception.DaoException;
import com.web.persistence.custom.CustomDAO;
import com.web.service.ArchiveService;
import com.web.wrapper.response.DataCurrentWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 21.06.16.
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private CustomDAO customDAO;

    @Override
    public DataCurrentWrapper getCurrentViewData() throws DaoException {
        return customDAO.getDCurrentDataWrapper();
    }
}
