package com.hust.labregister.service.impl;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.repository.EquipmentDivisionResultRepository;
import com.hust.labregister.service.EquipmentDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class EquipmentDivisionServiceImpl implements EquipmentDivisionService {
    @Autowired
    EquipmentDivisionResultRepository equipmentDivisionResultRepo;
    @Override
    public void fetch(ResponseBean bean, String roomRegisId) throws Exception {
        List equipmentDivisionResults= null;
        if(roomRegisId == null) {
            equipmentDivisionResults = equipmentDivisionResultRepo.findAll();
        }else{
            equipmentDivisionResults = equipmentDivisionResultRepo.findByRoomId(roomRegisId);
        }
        bean.setErrorCode("0");
        bean.setMsg("Successfully !");
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("equipmentDivisionResults", equipmentDivisionResults);
        bean.setData(data);
    }
}
