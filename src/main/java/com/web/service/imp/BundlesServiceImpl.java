package com.web.service.imp;

import org.springframework.stereotype.Service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created on 26.10.15.
 */
@Service
public class BundlesServiceImpl{

    public Map<String, String> getAllBundles(String properties) {
        ResourceBundle bundle = ResourceBundle.getBundle(properties);

        Enumeration<String> keys = bundle.getKeys();

        Map<String, String> result = new HashMap<String, String>();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();

            result.put(key, bundle.getString(key));
        }

        return result;
    }
}
