package com.araffle.araffle.Controller;

import com.araffle.araffle.Config.RJSON;
import com.araffle.araffle.Service.ActivityService;
import com.araffle.araffle.Service.OnlineRewardsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ActivityController")
@RestController
@Slf4j
public class ActivityController {

    @Autowired
    ActivityService activityService;

}
