package com.web.service;

import com.web.entity.Tag;
import com.web.exception.DaoException;
import com.web.wrapper.comport.ComPortDataWrapper;
import com.web.wrapper.response.DataCurrentWrapper;

import java.util.Date;
import java.util.List;

/**
 * Created on 21.06.16.
 */
public interface ArchiveService {
    DataCurrentWrapper getCurrentViewData() throws DaoException;

    Tag saveNewTag(ComPortDataWrapper comPortDataWrapper, ComPortDataWrapper comPortFormer);

    List<Tag> getAllTags();

    List<Tag> getTagsForPeriodTime(Date timeBegin, Date timeEnd);
}
