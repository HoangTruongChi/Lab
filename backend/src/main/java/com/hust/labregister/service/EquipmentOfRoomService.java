package com.hust.labregister.service;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.EquipmentOfRoom;

public interface EquipmentOfRoomService {
    void getEquipmentOfRoom(ResponseBean bean, Long roomId) throws  Exception;
    void addEquipmentOfRoom(ResponseBean bean, Long roomId, EquipmentOfRoom equipmentOfRoom) throws Exception;
    void updateEquipmentOfRoom(ResponseBean bean, Long roomId, EquipmentOfRoom equipmentOfRoom) throws Exception;
    void deleteEquipmentOfRoom(ResponseBean bean,Long id) throws Exception;
}
