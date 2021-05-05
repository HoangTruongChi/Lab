package com.hust.labregister.service;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.Room;
import com.hust.labregister.model.RoomDivisionResults;

import java.util.Date;

public interface RoomDivisionService {
    void fetch(ResponseBean bean, Long roomId, String date) throws Exception;
}
