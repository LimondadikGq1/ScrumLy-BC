package com.example.demo.unknown.websockets;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Principal;
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class UserPrincipal implements Principal {
    private String username;
    private String email;
    @Override
    public String getName() {
        return username;
    }
    public String email(){
        return email;
    }
}
