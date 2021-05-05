package com.hust.labregister.service.impl;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.Equipment;
import com.hust.labregister.model.EquipmentOfRoom;
import com.hust.labregister.model.Room;
import com.hust.labregister.repository.EquipmentOfRoomRepository;
import com.hust.labregister.repository.EquipmentRepository;
import com.hust.labregister.repository.RoomRepository;
import com.hust.labregister.service.EquipmentOfRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentOfRoomServiceImpl implements EquipmentOfRoomService {
    @Autowired
    EquipmentOfRoomRepository equipmentOfRoomRepository;

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public void getEquipmentOfRoom(ResponseBean bean, Long roomId) throws Exception {
        List equipmentOfRooms = null;
        if(roomId == null) {
            equipmentOfRooms = equipmentOfRoomRepository.findAll();
        }else{
            equipmentOfRooms = equipmentOfRoomRepository.findByRoomId(roomId);
        }
        bean.setErrorCode("0");
        bean.setMsg("Successfully !");
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("equipmentOfRooms", equipmentOfRooms);
        bean.setData(data);
    }

    @Override
    public void addEquipmentOfRoom(ResponseBean bean, Long roomId, EquipmentOfRoom equipmentOfRoom) throws Exception {
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(equipmentOfRoom.getEquipment().getId());
        if(equipmentOptional.isPresent()) {
            Equipment equipment = equipmentOptional.get();
            if(equipment.getTotalAmount() >= equipmentOfRoom.getAmount() && equipmentOfRoom.getAmount() >= 1){
                equipmentOfRoom.setEquipment(equipmentOptional.get());
                Optional<Room> roomOptional = roomRepository.findById(roomId);
                if(roomOptional.isPresent()) {
                    Room room = roomOptional.get();
                    equipmentOfRoom.setRoom(room);
                    equipmentOfRoom.setCreatedAt(new Date());
                    equipmentOfRoom.setUpdatedAt(new Date());
                    equipmentOfRoomRepository.save(equipmentOfRoom);
                }
                List<EquipmentOfRoom> equipmentOfRooms = equipmentOfRoomRepository.findAll();
                LinkedHashMap<String, Object> data = new LinkedHashMap<>();
                data.put("equipmentOfRooms", equipmentOfRooms);
                bean.setErrorCode("0");
                bean.setData(data);
                bean.setMsg("Successfully !");
            }
        }
    }

    @Override
    public void updateEquipmentOfRoom(ResponseBean bean, Long roomId,  EquipmentOfRoom equipmentOfRoom) throws Exception {
        Optional<EquipmentOfRoom> equipmentOfRoomOptional = equipmentOfRoomRepository.findById(equipmentOfRoom.getId());
        if(equipmentOfRoomOptional.isPresent()){
            EquipmentOfRoom equipmentOfRoomAfter = equipmentOfRoomOptional.get();

            // get room
            Optional<Room> room = roomRepository.findById(roomId);
            equipmentOfRoomAfter.setRoom(room.get());

            Optional<Equipment> equipment = equipmentRepository.findById(equipmentOfRoom.getEquipment().getId());
            equipmentOfRoomAfter.setEquipment(equipment.get());

            equipmentOfRoomAfter.setAmount(equipmentOfRoom.getAmount());
            equipmentOfRoomAfter.setUpdatedAt(new Date());

            equipmentOfRoomRepository.save(equipmentOfRoomAfter);
            LinkedHashMap<String, Object> data = new LinkedHashMap<>();
            bean.setErrorCode("0");
            bean.setData(data);
            bean.setMsg("Successfully !");
        }
    }

    @Override
    public void deleteEquipmentOfRoom(ResponseBean bean, Long id) throws Exception {
        if(id != null){
            equipmentOfRoomRepository.deleteById(id);
            LinkedHashMap<String, Object> data = new LinkedHashMap<>();
            bean.setErrorCode("0");
            bean.setData(data);
            bean.setMsg("Successfully !");
        }
    }
}
