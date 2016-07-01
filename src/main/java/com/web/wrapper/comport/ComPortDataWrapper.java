package com.web.wrapper.comport;

/**
 * Created on 01.07.16.
 */
public class ComPortDataWrapper {

    private boolean isLineOnOff;

    private boolean isWithMaterial;

    private Integer currentSpeed;

    private Integer impulseCounter;

    public boolean isLineOnOff() {
        return isLineOnOff;
    }

    public void setLineOnOff(boolean lineOnOff) {
        isLineOnOff = lineOnOff;
    }

    public boolean isWithMaterial() {
        return isWithMaterial;
    }

    public void setWithMaterial(boolean withMaterial) {
        isWithMaterial = withMaterial;
    }

    public Integer getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(Integer currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public Integer getImpulseCounter() {
        return impulseCounter;
    }

    public void setImpulseCounter(Integer impulseCounter) {
        this.impulseCounter = impulseCounter;
    }
}
