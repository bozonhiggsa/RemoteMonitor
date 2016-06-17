package com.web.service.imp;

import com.web.entity.Tag;
import com.web.persistence.TagRepository;
import com.web.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.NetworkInterface;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created on 28.10.15.
 */
@Service
public class TagsServiceImpl implements TagsService {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Autowired
    TagRepository tagsRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagsRepository.findAll();
    }

    @Override
    public List<Tag> getTagsForPeriodTime(Date timeBegin, Date timeEnd) {
        return null;
    }

    @Scheduled(fixedRate = 5000)
    private void saveTags() {
//        NetworkInterface ni = NetworkInterface.getByName("eth0");
//        Socket socket = new Socket();
//        socket.bind(ni.getInetAddresses().nextElement());
//        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        //Socket echoSocket = new Socket(hostName, portNumber);

        Long now = new Date().getTime();


        Tag tag = new Tag();
        tag.setLineOnOff(true);
        tag.setWithMaterial(true);
        tag.setTimeStamp(now);

        //tagsRepository.saveAndFlush(tag);
    }
}
