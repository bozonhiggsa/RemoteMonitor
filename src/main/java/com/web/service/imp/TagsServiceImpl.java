package com.web.service.imp;

import com.web.entity.Event;
import com.web.entity.EventType;
import com.web.entity.Tag;
import com.web.persistence.EventRepository;
import com.web.persistence.TagRepository;
import com.web.service.TagsService;
import jssc.SerialPort;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created on 28.10.15.
 */
@Service
public class TagsServiceImpl implements TagsService {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static final Logger logger = Logger.getLogger(TagsServiceImpl.class);

    private static final SerialPort serialPort;

    private boolean isFormerLineOnOff = false;

    private boolean isFormerWithMaterial = false;

    @Autowired
    TagRepository tagsRepository;

    @Autowired
    EventRepository eventRepository;

    static {
        serialPort = new SerialPort("/dev/ttyUSB0");
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE, false, false);

            logger.debug("Open COM port '/dev/ttyUSB0' OK");
        } catch (Exception e) {
            logger.error("Open COM port error");
        }
    }

    @Override
    public List<Tag> getAllTags() {
        return tagsRepository.findAll();
    }

    @Override
    public List<Tag> getTagsForPeriodTime(Date timeBegin, Date timeEnd) {
        return null;
    }

    @Scheduled(fixedRate = 5000)
    @Transactional
    private void saveTags() {

        try {
            byte[] bytes = serialPort.readBytes();
        } catch (Exception e) {
            logger.error("Read COM port error" + e.getMessage());
        }

        boolean isLineOnOff = true;
        boolean isWithMaterial = true;
        Long now = new Date().getTime();

        Tag tag = new Tag();

        tag.setLineOnOff(isLineOnOff);
        tag.setWithMaterial(isWithMaterial);
        tag.setTimeStamp(now);
        tag.setCurrentSpeed(34.2);
        tag.setExpenditureOfMaterial(25.5);

        tagsRepository.saveAndFlush(tag);

        if (isLineOnOff && !this.isFormerLineOnOff) {
            saveEvent(EventType.LINE_ON.name(), tag);
        }
        if (!isLineOnOff && this.isFormerLineOnOff) {
            saveEvent(EventType.LINE_OFF.name(), tag);
        }
        if (isWithMaterial && !this.isFormerWithMaterial) {
            saveEvent(EventType.MATERIAL_ON.name(), tag);
        }
        if (!isWithMaterial && this.isFormerWithMaterial) {
            saveEvent(EventType.MATERIAL_OFF.name(), tag);
        }

        this.isFormerLineOnOff = isLineOnOff;
        this.isFormerWithMaterial = isWithMaterial;


        byte[] d = {23, 4, 56};
        try {
            serialPort.writeBytes(d);
        } catch (Exception e) {
            logger.error("Write COM port error" + e.getMessage());
        }
    }

    private Event saveEvent(String description, Tag tag) {
        Event event = new Event();
        event.setTag(tag);
        event.setDescription(description);

        return eventRepository.save(event);
    }
}
