package com.freskotek.taskmgnt.repository;

import com.freskotek.taskmgnt.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByUserId(String userId, Pageable pageable);
    List<Task> findByTitleContainingIgnoreCaseAndUserId(String title, String userId);
}
