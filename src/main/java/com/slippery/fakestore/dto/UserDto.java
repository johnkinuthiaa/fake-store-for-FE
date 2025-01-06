package com.slippery.fakestore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.fakestore.models.Address;
import com.slippery.fakestore.models.User;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String Message;
    private int statusCode;
    private User user;
    private Address address;
    private List<User> users;
    private String JwtToken;

}
