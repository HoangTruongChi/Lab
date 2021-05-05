package com.hust.labregister.controller;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.service.EquipmentDivisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/lab/equipmentDivision")
public class EquipmentDivisionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EquipmentDivisionController.class);

    @Autowired
    EquipmentDivisionService equipmentDivisionService;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public ResponseEntity getEquipments(@RequestParam(name = "roomRegisId", required = false) String roomRegisId) {
        ResponseBean responseBean = new ResponseBean();
        try {
            equipmentDivisionService.fetch(responseBean, roomRegisId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }
}
