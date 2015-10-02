package com.myshow.common.controller;

import com.myshow.common.Result;
import com.reddy.my_show.server.model.Login;
import com.reddy.my_show.server.model.LoginSuccess;
import com.reddy.my_show.server.model.UserDetails;
import com.reddy.my_show.server.service.UserService;
import com.reddy.my_show.server.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.ResponseWrapper;

/**
 * Created by varshini on 27/9/15.
 */
@Controller
@RequestMapping("/user")
public class UserDetailsController{

    @Autowired
    private UserService userService;

    @Autowired
    private SessionManager sessionManager;


    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setSessionManager(SessionManager sessionManager){
        this.sessionManager = sessionManager;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result register(@RequestBody UserDetails userDetails){
        Result result = new Result();
        try{
            System.out.println("user "+userDetails.getEmail());
            this.userService.addUser(userDetails);
            result.setResult("true");

        }
        catch (Exception e) {
            result.setResult("false");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<LoginSuccess> getLoginDetails(@RequestBody Login login,HttpServletRequest request){
        String sessionId = null;
        UserDetails userDetails= null;
        LoginSuccess loginSuccess = new LoginSuccess();
        System.out.println(" vlogin" +login.getUserName()+" "+  login.getPassword());
        try{
                sessionId=sessionManager.createUserSession(login);
                userDetails=sessionManager.getUserSession(sessionId).getUserDetails();
                request.getSession().setAttribute("user", userDetails);

                loginSuccess.setStatus("true");
                loginSuccess.setLoginId(userDetails.getEmail());

        }
        catch (Exception e){
            e.printStackTrace();
            loginSuccess.setStatus("false");
            loginSuccess.setLoginId(userDetails.getEmail());

        }
        return ResponseEntity.ok().body(loginSuccess);

    }




}
