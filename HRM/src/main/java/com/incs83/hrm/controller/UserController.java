package com.incs83.hrm.controller;

import com.incs83.hrm.business.UserService;
import com.incs83.hrm.common.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserRequest> getAllUser(){
        return userService.getAllUser();
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public UserRequest getUserById(@PathVariable UUID id){
        return userService.getUserById(id);
    }

    @RequestMapping(path = "{id}",method = RequestMethod.PUT)
    public void updateUser(@RequestBody UserRequest userRequest, @PathVariable UUID id){
        userService.updateUser(userRequest,id);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable UUID id){
        userService.deleteUserById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllUser(){ userService.deleteAllUser();}
}
