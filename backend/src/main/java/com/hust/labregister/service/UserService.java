package com.hust.labregister.service;

import com.hust.labregister.controller.bean.ResponseBean;

public interface UserService {
    void getUserOfRoomRegistration(ResponseBean bean, String roomRegisId) throws Exception;
}
