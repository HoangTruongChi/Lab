package com.hust.labregister.service.impl;

import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.repository.UserRepository;
import com.hust.labregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public void getUserOfRoomRegistration(ResponseBean bean, String roomRegisId) throws Exception {
        List users = null;
        if(roomRegisId == null) {
            users = userRepository.findAll();
        }else{
            users = userRepository.findByRoomRegistrationId(roomRegisId);
        }
        bean.setErrorCode("0");
        bean.setMsg("Successfully !");
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("users", users);
        bean.setData(data);
    }
}
