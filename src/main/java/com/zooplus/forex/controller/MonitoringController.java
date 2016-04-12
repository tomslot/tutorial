package com.zooplus.forex.controller;

import com.zooplus.forex.model.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("users")
    public String users(){
        return StringUtils.join(userRepository.findAll(), ",");
    }
}
