package com.hust.labregister.controller;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.EquipmentOfRoom;
import com.hust.labregister.model.Room;
import com.hust.labregister.service.EquipmentService;
import com.hust.labregister.service.RoomService;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@CrossOrigin("*")
@RestController
@RequestMapping("/lab/room")
public class RoomController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    RoomService roomService;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public ResponseEntity getRooms() {
        ResponseBean responseBean = new ResponseBean();
        try {
            roomService.fetch(responseBean);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createRoom(@RequestBody Room room){
        ResponseBean responseBean = new ResponseBean();
        try {

            room.setCreatedAt(new Date());
            room.setUpdatedAt(new Date());
            room.setEquipmentOfRooms(new ArrayList<>());
            roomService.createRoom(responseBean,room);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity updateRoom(@RequestParam(name = "roomId",required = false) Long roomId,
            @RequestBody Room room){
        ResponseBean responseBean = new ResponseBean();
        try {
            roomService.updateRoom(responseBean,roomId,room);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteRoom(@RequestParam(name = "roomId", required = false) Long roomId){
        ResponseBean responseBean = new ResponseBean();
        try {
            roomService.deleteRoom(responseBean,roomId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

    @RequestMapping(value = "/addEquipment", method = RequestMethod.POST)
    public ResponseEntity addEquipment(@RequestParam(name = "roomId", required = false) Long roomId,
                                       @RequestBody EquipmentOfRoom equipmentOfRoom){
        ResponseBean responseBean = new ResponseBean();
        try {
            roomService.addEquipment(responseBean,roomId, equipmentOfRoom);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

}
