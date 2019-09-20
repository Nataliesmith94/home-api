package com.application.homeapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EntityScan
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "Home_Collection")
public class Account {

    @Id
    public String userId;
    public String email;
    public String password;
    public User user;

    @Override
    public String toString() {
        return getEmail() + " " + getPassword() + " " + getUser();
    }
}
