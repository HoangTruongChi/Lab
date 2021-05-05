package com.hust.labregister.service;

import com.hust.labregister.controller.bean.RegisterRequest;
import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.RoomRegistration;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface RegistrationService {

    void register(ResponseBean bean, RegisterRequest request, Authentication authentication) throws Exception;

    void  viewByUserId(ResponseBean bean, Long userId) throws Exception;

}