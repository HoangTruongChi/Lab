package com.hust.labregister.service;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.EquipmentOfRoom;
import com.hust.labregister.model.Room;

public interface RoomService {
    void fetch(ResponseBean bean) throws Exception;
    void createRoom(ResponseBean bean, Room room) throws Exception;
    void updateRoom(ResponseBean bean, Long roomId, Room room) throws  Exception;
    void deleteRoom(ResponseBean bean, long roomId) throws  Exception;
    void addEquipment(ResponseBean bean,Long roomId, EquipmentOfRoom equipmentOfRoom) throws  Exception;
}
