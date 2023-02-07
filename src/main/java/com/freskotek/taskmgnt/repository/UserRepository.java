package com.freskotek.taskmgnt.repository;

import com.freskotek.taskmgnt.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);

    User findByEmail(String email);
}
