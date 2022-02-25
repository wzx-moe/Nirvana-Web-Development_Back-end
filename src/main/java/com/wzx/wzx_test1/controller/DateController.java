package com.wzx.wzx_test1.controller;

import com.wzx.wzx_test1.mapper.DateMapper;
import com.wzx.wzx_test1.model.Appointment;
import com.wzx.wzx_test1.model.AppointmentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DateController {

    @Resource
    private DateMapper dateMapper;

    @RequestMapping("/getDates")
    public List<Appointment> getDates() {
        List<Appointment> appointments = dateMapper.getAll();
        return appointments;
    }

    @RequestMapping("/deleteDate")
    public void delete(AppointmentVO appointmentVO) {
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(appointmentVO, appointment);
        dateMapper.delete(appointment);
    }
}
