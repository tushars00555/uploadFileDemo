package com.srajan.repository;

import com.srajan.domain.User;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by tushar on 3/4/17.
 */

public interface UserRepo extends MongoRepository<User,String> {


}
