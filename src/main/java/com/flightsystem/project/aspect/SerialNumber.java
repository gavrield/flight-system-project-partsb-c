package com.flightsystem.project.aspect;

import org.springframework.data.annotation.Id;

public class SerialNumber {
    @Id
    private String id;
    private Long sn;

    public SerialNumber() {
    }

    public SerialNumber(String id, Long sn) {
        this.id = id;
        this.sn = sn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSn() {
        return sn;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return "Current Serial Number Value: " + getSn();
    }
}
