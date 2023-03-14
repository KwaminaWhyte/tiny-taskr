package com.freskotek.taskmgnt.repository;

import com.freskotek.taskmgnt.model.File;
import com.freskotek.taskmgnt.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileRepository extends MongoRepository<File, String> {
    List<File> findByNoteIdOrderByUpdatedAtDesc(String noteId);

    List<File> findByUserIdOrderByUpdatedAtDesc(String userId);

    List<File> getFileByWorkspaceId(String workspaceId);

    List<File> getFileByWorkspaceIdOrderByUpdatedAtDesc(String workspaceId);

    List<File> findByNameContainingIgnoreCaseAndUserId(String title, String userId);

}
