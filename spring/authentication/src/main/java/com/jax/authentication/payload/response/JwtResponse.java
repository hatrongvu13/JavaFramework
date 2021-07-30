package com.jax.authentication.payload.response;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private ObjectId id;
    private String username;
    private String email;
    private List<String> roles;
    public JwtResponse(String accessToken, ObjectId id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
