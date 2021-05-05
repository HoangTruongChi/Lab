package com.hust.labregister.service;

import com.hust.labregister.controller.bean.ResponseBean;

public interface EquipmentDivisionService {
    void fetch(ResponseBean bean, String roomRegisId) throws  Exception;
}
