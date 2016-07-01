package com.web.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.service.WebSocketService;
import com.web.wrapper.response.DataCurrentWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 29.06.16.
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {

    private static final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());

    private ObjectMapper mapper = new ObjectMapper();

    public boolean addSession(WebSocketSession session) {
        return sessions.add(session);
    }

    public boolean removeSession(WebSocketSession session) {
        return sessions.remove(session);
    }

    public void broadcastCurrentData(DataCurrentWrapper currentData) throws JsonProcessingException, IOException {

        String jsonInString = mapper.writeValueAsString(currentData);

        synchronized (sessions) {
            for (WebSocketSession s : sessions) {
                if (s.isOpen()) {
                    // send the current data to all the connected clients
                    s.sendMessage(new TextMessage(jsonInString.getBytes(StandardCharsets.UTF_8)));

                }
            }
        }
    }

}
