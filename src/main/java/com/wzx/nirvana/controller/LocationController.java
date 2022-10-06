package com.wzx.nirvana.controller;

import com.wzx.nirvana.annotation.UserLoginToken;
import com.wzx.nirvana.model.Location;
import com.wzx.nirvana.repository.LocationRepository;
import com.wzx.nirvana.utils.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/location")
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    LocationRepository locationRepository;


    @RequestMapping("getLocation")
    @ResponseBody
    public CommonResult<Location> getLocation() {
        return CommonResult.successReturn(locationRepository.getLocation());
    }

    @UserLoginToken
    @RequestMapping("update")
    @ResponseBody
    public CommonResult<String> updateLocation(@RequestBody Location location) {
        if (locationRepository.updateLocation(location) == 1) {
            return CommonResult.successReturn("0", "Update success");
        }
        return CommonResult.successReturn("0", "Add success");
    }

}
