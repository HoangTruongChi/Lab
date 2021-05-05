package com.hust.labregister.service;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.Equipment;
import com.hust.labregister.model.EquipmentOfRoom;

public interface EquipmentService {

    void fetch(ResponseBean bean, Long roomId) throws Exception;

    void createEquipment(ResponseBean bean, Equipment equipment) throws Exception;

    void updateEquipment(ResponseBean bean, Long equipmentId, Equipment equipment) throws Exception;

    void deleteEquipment(ResponseBean bean, Long equipmentId) throws Exception;
}


