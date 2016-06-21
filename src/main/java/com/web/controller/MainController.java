package com.web.controller;

import com.web.exception.ApplicationException;
import com.web.service.ArchiveService;
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
    private BundlesServiceImpl bundlesService;

    @Autowired
    private ArchiveService archiveService;

    @RequestMapping({"/", "/home"})
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/getBundles")
    @ResponseBody
    private Map<String, String> getBundles() {
        return bundlesService.getAllBundles("/label_ru.properties");
    }

    @RequestMapping("/data/current")
    @ResponseBody
    private DataCurrentWrapper getCurrentData() throws ApplicationException {
        return archiveService.getCurrentViewData();
    }
}
