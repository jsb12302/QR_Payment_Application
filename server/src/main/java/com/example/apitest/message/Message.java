package com.example.apitest.message;

import com.example.apitest.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL) //객체 NULL 인것 json에 포함하지 않는다.
@Data
public class Message {
    private String message;
    private String id;
    private Role role;
}
