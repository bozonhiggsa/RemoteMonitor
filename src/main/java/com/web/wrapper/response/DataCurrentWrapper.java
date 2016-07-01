package com.web.wrapper.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.web.util.CustomJsonSerializer;

import java.util.Date;

/**
 * Created on 17.06.16.
 */
public class DataCurrentWrapper {

    private Boolean materialOn;

    private Double currentSpeed;

    private Boolean lineOnOff;

    private Boolean withMaterial;

    private Double expenditureOfMaterial;

    private Long periodWorkWithMaterial;

    private Long downtime;

    @JsonSerialize(using = CustomJsonSerializer.class)
    private Date turnOnTimeToday;

    @JsonSerialize(using = CustomJsonSerializer.class)
    private Date turnOffTime;

    public Long getPeriodWorkWithMaterial() {
        return periodWorkWithMaterial;
    }

    public void setPeriodWorkWithMaterial(Long periodWorkWithMaterial) {
        this.periodWorkWithMaterial = periodWorkWithMaterial;
    }

    public Long getDowntime() {
        return downtime;
    }

    public void setDowntime(Long downtime) {
        this.downtime = downtime;
    }

    public Date getTurnOnTimeToday() {
        return turnOnTimeToday;
    }

    public void setTurnOnTimeToday(Date turnOnTimeToday) {
        this.turnOnTimeToday = turnOnTimeToday;
    }

    public Date getTurnOffTime() {
        return turnOffTime;
    }

    public void setTurnOffTime(Date turnOffTime) {
        this.turnOffTime = turnOffTime;
    }

    public Double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(Double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public Boolean getLineOnOff() {
        return lineOnOff;
    }

    public void setLineOnOff(Boolean lineOnOff) {
        this.lineOnOff = lineOnOff;
    }

    public Boolean getWithMaterial() {
        return withMaterial;
    }

    public void setWithMaterial(Boolean withMaterial) {
        this.withMaterial = withMaterial;
    }

    public Double getExpenditureOfMaterial() {
        return expenditureOfMaterial;
    }

    public void setExpenditureOfMaterial(Double expenditureOfMaterial) {
        this.expenditureOfMaterial = expenditureOfMaterial;
    }

    public Boolean getMaterialOn() {
        return materialOn;
    }

    public void setMaterialOn(Boolean materialOn) {
        this.materialOn = materialOn;
    }
}
