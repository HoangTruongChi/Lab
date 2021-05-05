package com.hust.labregister.service.impl;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.repository.RoomDivisionResultsRepository;
import com.hust.labregister.service.RoomDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class RoomDivisionServiceImpl implements RoomDivisionService {
    @Autowired
    RoomDivisionResultsRepository roomDivisionResultsRepo;

    @Override
    public void fetch(ResponseBean bean, Long roomId, String date) throws Exception {

      /*  List roomDivisionResultsList = null;
        if(roomId == null && date == null) {
            roomDivisionResultsList = roomDivisionResultsRepo.findAll();
        }
        if(roomId == null && date != null ){
            if(!date.isEmpty()) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateSearch = simpleDateFormat.parse(date);
                roomDivisionResultsList = roomDivisionResultsRepo.findByDate(dateSearch);
            }
        }
        if(roomId != null && date != null){
            if(!roomId.equals("") && date.isEmpty()){
                roomDivisionResultsList = roomDivisionResultsRepo.findByRoomId(roomId);
            }
            if(!roomId.equals("") && !date.isEmpty()){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateSearch = simpleDateFormat.parse(date);
                roomDivisionResultsList = roomDivisionResultsRepo.findByRoomIdAndDate(roomId,dateSearch);
            }
        }*/

        List roomDivisionResultsList = null;
        if(date == null) {
            roomDivisionResultsList = roomDivisionResultsRepo.findAll();
        }
        if(date != null && !date.isEmpty() ){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateSearch = simpleDateFormat.parse(date);
            roomDivisionResultsList = roomDivisionResultsRepo.findByDate(dateSearch);

        }

        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("roomDivisionResultsList", roomDivisionResultsList);
        bean.setErrorCode("0");
        bean.setData(data);
        bean.setMsg("Successfully !");

    }

}
