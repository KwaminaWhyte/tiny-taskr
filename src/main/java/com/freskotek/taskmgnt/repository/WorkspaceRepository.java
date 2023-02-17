package com.freskotek.taskmgnt.repository;

import com.freskotek.taskmgnt.model.Workspace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends MongoRepository<Workspace, String> {
     List<Workspace> findByUserId(String userId);
     List<Workspace> findByNameContainingIgnoreCaseAndUserId(String name, String userId);
}
