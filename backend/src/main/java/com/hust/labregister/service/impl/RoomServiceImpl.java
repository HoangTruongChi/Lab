package com.hust.labregister.service.impl;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.EquipmentOfRoom;
import com.hust.labregister.model.Room;
import com.hust.labregister.repository.EquipmentOfRoomRepository;
import com.hust.labregister.repository.RoomRepository;
import com.hust.labregister.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    EquipmentOfRoomRepository equipmentOfRoomRepository;

    @Override
    public void fetch(ResponseBean bean) throws Exception {
        List<Room> rooms = roomRepository.findAll();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("rooms", rooms);
        bean.setErrorCode("0");
        bean.setData(data);
        bean.setMsg("Successfully !");
    }

    @Override
    public void createRoom(ResponseBean bean, Room room) throws Exception {
        roomRepository.save(room);
        List<Room> rooms = roomRepository.findAll();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("rooms", rooms);
        bean.setErrorCode("0");
        bean.setData(data);
        bean.setMsg("Successfully !");
    }

    @Override
    public void updateRoom(ResponseBean bean, Long roomId, Room room) throws Exception {
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if(roomOptional.isPresent()){
            Room roomUpdate = roomOptional.get();
            roomUpdate.setName(room.getName());
            roomUpdate.setCode(room.getCode());
            roomUpdate.setRoomType(room.getRoomType());
            roomUpdate.setOpenTime(room.getOpenTime());
            roomUpdate.setCloseTime(room.getCloseTime());
            roomUpdate.setOpenDay(room.getOpenDay());
            roomUpdate.setCloseDay(room.getCloseDay());
            roomUpdate.setUpdatedAt(new Date());
            roomUpdate.setMaxPeople(room.getMaxPeople());

            roomRepository.save(roomUpdate);
        }
        List<Room> rooms = roomRepository.findAll();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("rooms", rooms);
        bean.setErrorCode("0");
        bean.setData(data);
        bean.setMsg("Successfully !");
    }

    @Override
    public void deleteRoom(ResponseBean bean, long roomId) throws Exception {
        roomRepository.deleteById(roomId);
        List<Room> rooms = roomRepository.findAll();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("rooms", rooms);
        bean.setErrorCode("0");
        bean.setData(data);
        bean.setMsg("Successfully !");
    }

    @Override
    public void addEquipment(ResponseBean bean,Long roomId, EquipmentOfRoom equipmentOfRoom) throws Exception {
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if(roomOptional.isPresent()){
            Room room = roomOptional.get();
            equipmentOfRoom.setCreatedAt(new Date());
            equipmentOfRoom.setUpdatedAt(new Date());
            equipmentOfRoom.setRoom(room);

            equipmentOfRoomRepository.save(equipmentOfRoom);
            List<Room> rooms = roomRepository.findAll();
            LinkedHashMap<String, Object> data = new LinkedHashMap<>();
            data.put("rooms", rooms);
            bean.setErrorCode("0");
            bean.setData(data);
            bean.setMsg("Successfully !");
        }
    }
}
