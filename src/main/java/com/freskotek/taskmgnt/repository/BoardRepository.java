package com.freskotek.taskmgnt.repository;

import com.freskotek.taskmgnt.model.Board;
import com.freskotek.taskmgnt.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends MongoRepository<Board, String> {
     List<Board> findByWorkspaceId(String workspaceId);
     List<Board> findByNameContainingIgnoreCase(String name);
}
