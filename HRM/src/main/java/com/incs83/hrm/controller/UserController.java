package com.incs83.hrm.controller;

import com.incs83.hrm.business.UserService;
import com.incs83.hrm.common.AuthenticationRequest;
import com.incs83.hrm.common.UserRequest;
import com.incs83.hrm.response.AuthenticationResponse;
import com.incs83.hrm.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect username orPassword", e);
            }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<HashMap<String, Object>> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public LinkedHashMap<String, Object> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void updateUser(@RequestBody UserRequest userRequest, @PathVariable UUID id) {
        userService.updateUser(userRequest, id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllUser() {
        userService.deleteAllUser();
    }
}
