package com.wzx.wzx_test1.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Appointment implements Serializable {
    private static final long serialVersionUID = 1476383731773481640L;
    private String ID;
    private String patientName;
    private int phone;
    private String doctorName;
    private Date date;


}
