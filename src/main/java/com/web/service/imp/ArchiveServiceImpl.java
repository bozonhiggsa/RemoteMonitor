package com.web.service.imp;

import com.web.entity.Event;
import com.web.entity.EventType;
import com.web.entity.Tag;
import com.web.exception.DaoException;
import com.web.persistence.EventRepository;
import com.web.persistence.TagRepository;
import com.web.persistence.custom.CustomDAO;
import com.web.service.ArchiveService;
import com.web.wrapper.comport.ComPortDataWrapper;
import com.web.wrapper.dao.TagTimestampWrapper;
import com.web.wrapper.response.DataCurrentWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created on 21.06.16.
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private CustomDAO customDAO;

    @Autowired
    private TagRepository tagsRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagsRepository.findAll();
    }

    @Override
    public List<Tag> getTagsForPeriodTime(Date timeBegin, Date timeEnd) {
        return null;
    }

    @Override
    public DataCurrentWrapper getCurrentViewData() throws DaoException {
        DataCurrentWrapper responseWrapper = customDAO.getCurrentDataWrapper();
        TagTimestampWrapper timeLineOnToday = customDAO.getTimeLineOnToday();
        TagTimestampWrapper timeLineOFFtoday = customDAO.getTimeLineOffToday();

        if (timeLineOnToday != null) {
            responseWrapper.setTurnOnTimeToday(timeLineOnToday.getTimestamp());
        }
        if (timeLineOFFtoday != null) {
            responseWrapper.setTurnOffTime(timeLineOFFtoday.getTimestamp());
        }


        responseWrapper.setTurnOnTimeToday(new Date());
        responseWrapper.setTurnOffTime(new Date());
        responseWrapper.setPeriodWorkWithMaterial(3000000L);
        responseWrapper.setDowntime(12675L);

        return responseWrapper;
    }

    @Override
    @Transactional
    public Tag saveNewTag(ComPortDataWrapper comPortDataCurrent, ComPortDataWrapper comPortFormer) {
        Tag lastTag = tagsRepository.findLastTag();
        boolean isNewDay = true;
        if (lastTag != null) {
            isNewDay = !isDayTheSame(lastTag.getTimeStamp());
        }

        Tag tag = saveTag(isNewDay, comPortDataCurrent);

        if (comPortDataCurrent.isLineOnOff() && !comPortFormer.isLineOnOff()) {
            saveEvent(EventType.LINE_ON.name(), tag);
        }
        if (!comPortDataCurrent.isLineOnOff() && comPortFormer.isLineOnOff()) {
            saveEvent(EventType.LINE_OFF.name(), tag);
        }
        if (comPortDataCurrent.isWithMaterial() && !comPortFormer.isWithMaterial()) {
            saveEvent(EventType.MATERIAL_ON.name(), tag);
        }
        if (!comPortDataCurrent.isWithMaterial() && comPortFormer.isWithMaterial()) {
            saveEvent(EventType.MATERIAL_OFF.name(), tag);
        }

        return tag;
    }

    private Event saveEvent(String description, Tag tag) {
        Event event = new Event();
        event.setTag(tag);
        event.setDescription(description);

        return eventRepository.save(event);
    }

    private boolean isDayTheSame(Date lastTagTimeStamp) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(lastTagTimeStamp);
        int lastTagDay = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(new Date());
        int nowDay = cal.get(Calendar.DAY_OF_MONTH);

        return nowDay == lastTagDay;
    }

    private Tag saveTag(boolean isNewDay, ComPortDataWrapper comPortDataCurrent) {
        Tag tag = new Tag();

        tag.setLineOnOff(true);
        tag.setWithMaterial(true);
        tag.setTimeStamp(new Date());
        tag.setCurrentSpeed(34.2);
        tag.setExpenditureOfMaterial(48.0);

        return tagsRepository.saveAndFlush(tag);
    }
}
