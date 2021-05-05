package com.hust.labregister.service.impl;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.Equipment;
import com.hust.labregister.model.EquipmentOfRoom;
import com.hust.labregister.model.Room;
import com.hust.labregister.repository.EquipmentOfRoomRepository;
import com.hust.labregister.repository.EquipmentRepository;
import com.hust.labregister.repository.RoomRepository;
import com.hust.labregister.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    EquipmentOfRoomRepository equipmentOfRoomRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public void fetch(ResponseBean bean, Long roomId) throws Exception {
        List equipments = null;
        if(roomId == null) {
            equipments = equipmentRepository.findAll();
        }else{
            equipments = equipmentOfRoomRepository.findByRoomId(roomId);
        }
        bean.setErrorCode("0");
        bean.setMsg("Successfully !");
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("equipments", equipments);
        bean.setData(data);
    }

    @Override
    public void createEquipment(ResponseBean bean, Equipment equipment) throws Exception {
        equipment.setCreatedAt(new Date());
        equipment.setUpdatedAt(new Date());
        equipmentRepository.save(equipment);
        List<Equipment> equipments = equipmentRepository.findAll();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("equipments", equipments);
        bean.setErrorCode("0");
        bean.setData(data);
        bean.setMsg("Successfully !");
    }

    @Override
    public void updateEquipment(ResponseBean bean, Long equipmentId, Equipment equipment) throws Exception {
        Optional<Equipment> equip = equipmentRepository.findById(equipmentId);

        if(equip.isPresent()){
            Equipment _equip = equip.get();
            _equip.setName(equipment.getName());
            _equip.setTotalAmount(equipment.getTotalAmount());
            _equip.setDescription(equipment.getDescription());
            _equip.setUpdatedAt(new Date());
            equipmentRepository.save(_equip);
        }

        List<Equipment> equipments = equipmentRepository.findAll();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("equipments", equipments);
        bean.setErrorCode("0");
        bean.setData(data);
        bean.setMsg("Successfully !");
    }

    @Override
    public void deleteEquipment(ResponseBean bean, Long equipmentId) throws Exception {
        equipmentRepository.deleteById(equipmentId);
        List<Equipment> equipments = equipmentRepository.findAll();
        bean.setErrorCode("0");
        bean.setMsg("Successfully !");
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("equipments", equipments);
        bean.setData(data);
    }


}
