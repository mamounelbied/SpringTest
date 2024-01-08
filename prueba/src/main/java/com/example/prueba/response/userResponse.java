package com.example.prueba.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userResponse {

    private Integer id;
    private String fullName;

    private String username;

    private String phone;
    private String address;

}
