package com.hust.labregister.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "room_division_results")
public class RoomDivisionResults {
    @Id
    @Column(name = "room_registration_id")
    private String roomRegisId;

    @Column(name = "status")
    private Integer status;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "start_time")
    private Double startTime;

    @Column(name = "end_time")
    private Double endTime;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "group_size")
    private Integer groupSize;

    @OneToMany(mappedBy = "roomDivisionResults")
    private List<EquipmentDivisionResult> equipmentDivisionResults;

    public Integer getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    public String getRoomRegisId() {
        return roomRegisId;
    }

    public void setRoomRegisId(String roomRegisId) {
        this.roomRegisId = roomRegisId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<EquipmentDivisionResult> getEquipmentDivisionResults() {
        return equipmentDivisionResults;
    }

    public void setEquipmentDivisionResults(List<EquipmentDivisionResult> equipmentDivisionResults) {
        this.equipmentDivisionResults = equipmentDivisionResults;
    }
}

