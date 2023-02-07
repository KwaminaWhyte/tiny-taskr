package com.freskotek.taskmgnt.repository;

import com.freskotek.taskmgnt.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
