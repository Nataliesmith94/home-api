package com.application.homeapi.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    public final String firstName;
    public final String Surname;
}
