package com.bootcamp.hrm.business;

import com.bootcamp.hrm.entities.Login;
import com.bootcamp.hrm.entities.LoginDetails;
import com.bootcamp.hrm.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

//        return new User("userName","password", new ArrayList<>());

        Optional<Login> login = loginRepository.findByUserName(userName);

        login.orElseThrow(() -> new UsernameNotFoundException("Not Found" + userName));

        return login.map(LoginDetails::new).get();
    }
}
