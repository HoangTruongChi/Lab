package com.hust.labregister.controller;

import com.hust.labregister.controller.bean.RegisterRequest;
import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.User;
import com.hust.labregister.repository.UserRepository;
import com.hust.labregister.security.TokenAuthenticationService;
import com.hust.labregister.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/lab/registration")
public class RegisterController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity register(@RequestBody RegisterRequest request, HttpServletRequest servletRequest) {
        ResponseBean responseBean = new ResponseBean();
        try {
            Authentication authentication = TokenAuthenticationService.getAuthentication(servletRequest);
            registrationService.register(responseBean, request, authentication);
        } catch (Exception e) {
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

    @GetMapping
    public ResponseEntity viewInfo(HttpServletRequest servletRequest){
        ResponseBean responseBean = new ResponseBean();
        try {
            Authentication authentication = TokenAuthenticationService.getAuthentication(servletRequest);
            String email = authentication.getPrincipal().toString();
            User user = userRepository.findByEmail(email);
            registrationService.viewByUserId(responseBean, user.getId());
        } catch (Exception e) {
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

}
