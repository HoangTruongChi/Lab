package com.hust.labregister.controller.bean;

import java.util.Date;

public class RegisterRequest {

    private Integer roomType;
    private Integer groupSize;
    private Long equipmentId;
    private Integer numOfEquip;
    private Date date;
    private Double startTime;
    private Double endTime;

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public Integer getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getNumOfEquip() {
        return numOfEquip;
    }

    public void setNumOfEquip(Integer numOfEquip) {
        this.numOfEquip = numOfEquip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getStartTime() {
        return startTime;
    }

    public void setStartTime(Double startTime) {
        this.startTime = startTime;
    }

    public Double getEndTime() {
        return endTime;
    }

    public void setEndTime(Double endTime) {
        this.endTime = endTime;
    }
}
