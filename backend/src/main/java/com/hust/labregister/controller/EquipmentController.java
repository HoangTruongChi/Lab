package com.hust.labregister.controller;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.Equipment;
import com.hust.labregister.model.User;
import com.hust.labregister.security.TokenAuthenticationService;
import com.hust.labregister.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("/lab/equipment")
public class EquipmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EquipmentController.class);

    @Autowired
    EquipmentService equipmentService;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public ResponseEntity getEquipments(@RequestParam(name = "roomId", required = false) Long roomId) {
        ResponseBean responseBean = new ResponseBean();
        try {
            equipmentService.fetch(responseBean, roomId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createEquipment(@RequestBody Equipment equipment) {
        ResponseBean responseBean = new ResponseBean();
        try {
            equipmentService.createEquipment(responseBean, equipment);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteEquipment(@RequestParam(name = "equipmentId", required = false) Long equipmentId) {
        ResponseBean responseBean = new ResponseBean();
        try {
            equipmentService.deleteEquipment(responseBean, equipmentId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity updateEquipments(@RequestParam(name = "equipmentId", required = false) Long equipmentId,
                                           @RequestBody Equipment equipment) {
        ResponseBean responseBean = new ResponseBean();
        try {
            equipmentService.updateEquipment(responseBean, equipmentId, equipment);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }
}
