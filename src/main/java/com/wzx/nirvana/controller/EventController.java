package com.wzx.nirvana.controller;


import com.wzx.nirvana.annotation.UserLoginToken;
import com.wzx.nirvana.model.Event;
import com.wzx.nirvana.repository.EventRepository;
import com.wzx.nirvana.service.EventService;
import com.wzx.nirvana.utils.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/event")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventService eventService;

    @UserLoginToken
    @RequestMapping("add")
    @ResponseBody
    public CommonResult<Event> addEvent(@RequestBody Event event) {
        //logger.info(page.toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            df.parse(event.getDateTime());
            Integer.parseInt(event.getRepeatCount());
        } catch (Exception e) {
            throw new RuntimeException("Wrong format");
        }
        if (event.getRepeat() == null) throw new RuntimeException("Wrong format");
        if (!event.getRepeat().equals("DAILY") && !event.getRepeat().equals("WEEKLY") &&
                !event.getRepeat().equals("FORTNIGHTLY") && !event.getRepeat().equals("MONTHLY") &&
                !event.getRepeatCount().equals("1") ) throw new RuntimeException("Wrong format");
        event = eventRepository.addEvent(event);
        if (event != null) {
            return CommonResult.successReturn(event, "Add success");
        }
        return CommonResult.errorReturn(400, "Add failed");
    }

    @UserLoginToken
    @RequestMapping("update")
    @ResponseBody
    public CommonResult<String> updateEvent(@RequestBody Event event) {
        if (eventRepository.updateEvent(event) == 1) {
            return CommonResult.successReturn("0", "Update success");
        }
        return CommonResult.errorReturn(400, "Update failed");
    }

    @UserLoginToken
    @RequestMapping("getEvents")
    @ResponseBody
    public CommonResult<List<Event>> getEvents() {
        return CommonResult.successReturn(eventRepository.getEvents());
    }

    @RequestMapping("getEvents/{time}")
    @ResponseBody
    public CommonResult<List<Event>> getEvents(@PathVariable String time) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(time);
        return CommonResult.successReturn(eventService.calculateEvent(date));
    }

    @UserLoginToken
    @RequestMapping("delete/{id}")
    @ResponseBody
    public CommonResult<Long> deleteEvent(@PathVariable String id) {
        long result = eventRepository.deleteEvent(id);
        return result == 1 ? CommonResult.successReturn(result) : CommonResult.errorReturn(400, "Wrong id");
    }
}
