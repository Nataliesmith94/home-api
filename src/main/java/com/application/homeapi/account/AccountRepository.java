package com.application.homeapi.account;

import com.application.homeapi.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByEmail (String email);

    Account findByUserId (String id);

}
