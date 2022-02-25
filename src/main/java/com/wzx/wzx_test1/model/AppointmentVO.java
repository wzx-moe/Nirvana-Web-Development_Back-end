package com.wzx.wzx_test1.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppointmentVO implements Serializable {
    private static final long serialVersionUID = 1476383731773481640L;
    private String ID;
    private String patientName;
}
