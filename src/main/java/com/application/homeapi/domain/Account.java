package com.application.homeapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Account {
    public final String email;
    public final String password;
    public final User user;
}
