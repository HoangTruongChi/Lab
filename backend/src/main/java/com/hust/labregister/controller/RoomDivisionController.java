package com.hust.labregister.controller;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.RoomDivisionResults;
import com.hust.labregister.service.RoomDivisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("/lab/roomDivision")
public class RoomDivisionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomDivisionController.class);

    @Autowired
    RoomDivisionService roomDivisionService;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public ResponseEntity getRooms(@RequestParam(name = "roomId", required = false) Long roomId,
                                   @RequestParam(name = "date", required = false) String date) {
        ResponseBean responseBean = new ResponseBean();
        try {
            roomDivisionService.fetch(responseBean, roomId, date);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }
}
