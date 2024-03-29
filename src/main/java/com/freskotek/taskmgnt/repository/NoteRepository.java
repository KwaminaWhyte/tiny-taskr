package com.freskotek.taskmgnt.repository;

import com.freskotek.taskmgnt.model.Note;
import com.freskotek.taskmgnt.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByUserIdOrderByUpdatedAtDesc(String userId);

    List<Note> getNotesByWorkspaceId(String workspaceId);

    List<Note> getNotesByWorkspaceIdOrderByUpdatedAtDesc(String workspaceId);

    List<Note> findByTitleContainingIgnoreCaseAndUserId(String title, String userId);
}
