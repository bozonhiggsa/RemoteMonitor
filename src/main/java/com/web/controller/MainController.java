package com.web.controller;

import com.web.service.imp.BundlesServiceImpl;
import com.web.wrapper.response.DataCurrentWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created on 26.10.15.
 */

@Controller
public class MainController extends BaseController {

    @Autowired
    BundlesServiceImpl bundlesService;

    @RequestMapping({"/", "/home"})
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/getBundles")
    @ResponseBody
    private Map<String, String> getBundles() {
        return bundlesService.getAllBundles("label");
    }

    @RequestMapping("/data/current")
    @ResponseBody
    private DataCurrentWrapper getCurrentData() {
//        if(true){
//            throw new NullPointerException();
//        }
        return null;
    }
}
