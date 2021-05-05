package com.hust.labregister.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "room_type")
    private Integer roomType;

    @Column(name = "open_time")
    private Double openTime;

    @Column(name = "close_time")
    private Double closeTime;

    @Column(name = "open_day_of_week")
    private Integer openDay;

    @Column(name = "close_day_of_week")
    private Integer closeDay;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "max_people")
    private Integer maxPeople;

    @OneToMany(mappedBy = "room")
    private List<EquipmentOfRoom> equipmentOfRooms;

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public Double getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Double openTime) {
        this.openTime = openTime;
    }

    public Double getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Double closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getOpenDay() {
        return openDay;
    }

    public void setOpenDay(Integer openDay) {
        this.openDay = openDay;
    }

    public Integer getCloseDay() {
        return closeDay;
    }

    public void setCloseDay(Integer closeDay) {
        this.closeDay = closeDay;
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

    public List<EquipmentOfRoom> getEquipmentOfRooms() {
        return equipmentOfRooms;
    }

    public void setEquipmentOfRooms(List<EquipmentOfRoom> equipmentOfRooms) {
        this.equipmentOfRooms = equipmentOfRooms;
    }
}
