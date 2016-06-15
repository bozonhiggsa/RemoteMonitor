package com.web.service;

import com.web.entity.Tag;

import java.util.Date;
import java.util.List;

/**
 * Created on 28.10.15.
 */
public interface TagsService {

    List<Tag> getAllTags();

    List<Tag> getTagsForPeriodTime(Date timeBegin, Date timeEnd);
}
