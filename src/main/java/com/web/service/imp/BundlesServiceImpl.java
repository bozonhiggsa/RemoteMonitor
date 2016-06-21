package com.web.service.imp;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;

/**
 * Created on 26.10.15.
 */
@Service
public class BundlesServiceImpl {

    public Map<String, String> getAllBundles(String properties) {

        try {
            Reader reader = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream(properties), "utf-8"));
            PropertyResourceBundle bundles = new PropertyResourceBundle(reader);
            Enumeration<String> keys = bundles.getKeys();

            Map<String, String> result = new HashMap<>();

            while (keys.hasMoreElements()) {
                String key = keys.nextElement();

                result.put(key, (String) bundles.handleGetObject(key));
            }

            return result;
        } catch (Exception ex) {
            return null;
        }


    }
}
