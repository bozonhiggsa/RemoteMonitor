package com.web.service.imp;


import com.web.entity.Tag;
import com.web.service.ArchiveService;
import com.web.service.TagsService;
import com.web.service.WebSocketService;
import com.web.wrapper.comport.ComPortDataWrapper;
import com.web.wrapper.response.DataCurrentWrapper;
import jssc.SerialPort;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created on 28.10.15.
 */
@Service
public class TagsServiceImpl implements TagsService {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static final Logger logger = Logger.getLogger(TagsServiceImpl.class);

    private static final SerialPort serialPort;

    private ComPortDataWrapper comPortDataFormer = new ComPortDataWrapper();

    @Value("${meters.per.every.impulse}")
    private Double metersPerEveryImpulse;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private ArchiveService archiveService;

    static {
        serialPort = new SerialPort("/dev/ttyUSB0");
//        try {
//            serialPort.openPort();
//            serialPort.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
//                    SerialPort.PARITY_NONE, false, false);
//
//            logger.debug("Open COM port '/dev/ttyUSB0' OK");
//        } catch (Exception e) {
//            logger.error("Open COM port error");
//        }
    }


    @Scheduled(fixedRate = 30000)
    private void saveTags() {

//        try {
//            byte[] bytes = serialPort.readBytes();
//        } catch (Exception e) {
//            logger.error("Read COM port error" + e.getMessage());
//        }

        ComPortDataWrapper comPortDataCurrent = new ComPortDataWrapper();
        comPortDataCurrent.setLineOnOff(true);
        comPortDataCurrent.setWithMaterial(true);

        Tag tag = archiveService.saveNewTag(comPortDataCurrent, this.comPortDataFormer);

        this.comPortDataFormer.setLineOnOff(comPortDataCurrent.isLineOnOff());
        this.comPortDataFormer.setWithMaterial(comPortDataCurrent.isWithMaterial());

        sendCurrentDataToViewClients(tag);


//        byte[] d = {23, 4, 56};
//        try {
//            serialPort.writeBytes(d);
//        } catch (Exception e) {
//            logger.error("Write COM port error" + e.getMessage());
//        }
    }


    private void sendCurrentDataToViewClients(Tag tag) {
        DataCurrentWrapper currentViewData = new DataCurrentWrapper();
        currentViewData.setTurnOffTime(new Date());
        currentViewData.setTurnOnTimeToday(new Date());
        currentViewData.setExpenditureOfMaterial(12.4);
        currentViewData.setDowntime(12333345L);
        currentViewData.setPeriodWorkWithMaterial(234234234L);
        currentViewData.setCurrentSpeed(tag.getCurrentSpeed());
        currentViewData.setLineOnOff(tag.getLineOnOff());
        currentViewData.setMaterialOn(tag.getWithMaterial());
        try {
            webSocketService.broadcastCurrentData(currentViewData);
        } catch (Exception ex) {

        }
    }
}
