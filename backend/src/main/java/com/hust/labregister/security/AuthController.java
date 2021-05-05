package com.hust.labregister.security;

import com.hust.labregister.controller.bean.ChangePassBean;
import com.hust.labregister.controller.bean.ResponseBean;
import com.hust.labregister.model.User;
import com.hust.labregister.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    static final long EXPIRATION_TIME = 300_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String HEADER_STRING = "Authorization";

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseEntity signIn(@RequestBody AccountCredentials credentials){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword(),
                            Collections.emptyList()
                    )
            );
            SimpleGrantedAuthority sga = (SimpleGrantedAuthority) authentication.getAuthorities().iterator().next();
            String role = sga.getAuthority();
            String JWT = Jwts.builder()
                    .setSubject(credentials.getUsername())
                    .setIssuer(role)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
            ResponseBean responseBean = new ResponseBean();
            responseBean.setErrorCode("0");
            responseBean.setMsg("SignIn successfully !");
            LinkedHashMap<String, Object> wsResponse = new LinkedHashMap<>();
            wsResponse.put("username", credentials.getUsername());
            wsResponse.put("token", JWT);
            wsResponse.put("role", role);
            responseBean.setData(wsResponse);
            return ResponseEntity.ok(responseBean);
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/user/change_pass", method = RequestMethod.POST)
    public ResponseEntity changePass(@RequestBody ChangePassBean changePassBean, HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);
        String oldPass = changePassBean.getOldPass();
        String pass = changePassBean.getPass();
        String checkPass = changePassBean.getCheckPass();
        ResponseBean response = new ResponseBean();

        User user = TokenAuthenticationService.getAuthentication(token);
        if(user != null){
            user = userRepository.findByEmail(user.getEmail());
            if(StringUtils.isEmpty(oldPass) || StringUtils.isEmpty(pass)
                    || StringUtils.isEmpty(checkPass) || !pass.equals(checkPass)){
                response.setErrorCode("1");
                response.setMsg("Invalid input");
                return ResponseEntity.ok(response);
            }else{
                if(!passwordEncoder.matches(oldPass, user.getPassword())){
                    response.setErrorCode("1");
                    response.setMsg("Old Password is not correct !");
                    return ResponseEntity.ok(response);
                }
                user.setPassword(passwordEncoder.encode(pass));
                userRepository.save(user);
                response.setErrorCode("0");
                response.setMsg("Change pass successfully !");
                return ResponseEntity.ok(response);
            }
        }else{
            return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public ResponseEntity getInfo(@RequestParam String token){
        ResponseBean responseBean = new ResponseBean();
        try{
            User user = TokenAuthenticationService.getAuthentication(token);
            if(user != null) {
                LinkedHashMap<String, Object> data = new LinkedHashMap<>();
                data.put("roles", Arrays.asList("role"));
                data.put("introduction", "");
                data.put("avatar", "");
                data.put("name", user.getEmail());
                responseBean.setErrorCode("0");
                responseBean.setMsg("Successfully !");
                responseBean.setData(data);
            }
        }catch (Exception e){
            responseBean.setErrorCode("1");
        }
        return ResponseEntity.ok(responseBean);
    }

}
