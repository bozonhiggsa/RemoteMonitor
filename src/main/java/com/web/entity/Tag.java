package com.web.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created on 27.10.15.
 */
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Date timeStamp;

    @NotNull
    private Double currentSpeed;

    @NotNull
    private Boolean lineOnOff;

    @NotNull
    private Boolean withMaterial;

    @NotNull
    private Double expenditureOfMaterial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return id != null ? id.equals(tag.id) : tag.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
