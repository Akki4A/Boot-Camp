package com.incs83.hrm.controller;

import com.incs83.hrm.business.UserService;
import com.incs83.hrm.common.UserRequest;
import com.incs83.hrm.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
    }

    @RequestMapping(path = "/getAllUser",method = RequestMethod.GET)
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @RequestMapping(path = "/getUserById/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @RequestMapping(path = "/updateUser/{id}",method = RequestMethod.PUT)
    public void updateUser(@RequestBody UserRequest userRequest, @PathVariable String id){
        userService.updateUser(userRequest,id);
    }

    @RequestMapping(path = "/deleteUserById/{id}",method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
    }
}
