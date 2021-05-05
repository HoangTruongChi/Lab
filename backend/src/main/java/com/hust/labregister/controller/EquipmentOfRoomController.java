package com.hust.labregister.controller;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.Equipment;
import com.hust.labregister.model.EquipmentOfRoom;
import com.hust.labregister.service.EquipmentOfRoomService;
import com.hust.labregister.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/lab/equipmentOfRoom")
public class EquipmentOfRoomController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EquipmentOfRoomController.class);

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    EquipmentOfRoomService equipmentOfRoomService;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public ResponseEntity getEquipmentOfRooms(@RequestParam(name = "roomId", required = false) Long roomId) {
        ResponseBean responseBean = new ResponseBean();
        try {
            equipmentOfRoomService.getEquipmentOfRoom(responseBean, roomId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addEquipmentOfRooms(@RequestParam(name = "roomId", required = false) Long roomId,
            @RequestBody EquipmentOfRoom equipmentOfRoom) {
        ResponseBean responseBean = new ResponseBean();
        try {
            equipmentOfRoomService.addEquipmentOfRoom(responseBean,roomId, equipmentOfRoom);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteEquipmentOfRoom(@RequestParam(name = "id", required = false) Long id) {
        ResponseBean responseBean = new ResponseBean();
        try {
            equipmentOfRoomService.deleteEquipmentOfRoom(responseBean, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity updateEquipmentOrRoom(@RequestParam(name = "roomId", required = false) Long roomId,
                                           @RequestBody EquipmentOfRoom equipmentOfRoom) {
        ResponseBean responseBean = new ResponseBean();
        try {
            equipmentOfRoomService.updateEquipmentOfRoom(responseBean, roomId, equipmentOfRoom);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }
}
