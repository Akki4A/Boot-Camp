package com.bootcamp.hrm.response;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 8621286897427117337L;
    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getToken() {
        return this.jwtToken;
    }
}
