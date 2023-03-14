package com.freskotek.taskmgnt.service;

import com.freskotek.taskmgnt.model.File;
import com.freskotek.taskmgnt.model.Note;
import com.freskotek.taskmgnt.repository.FileRepository;
import com.freskotek.taskmgnt.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public List<File> allNotes() {
        return fileRepository.findAll();
    }

    public List<File> allNotesFiles(String noteId ){
        return fileRepository.findByNoteIdOrderByUpdatedAtDesc(noteId);
    }

    public List<File> allUserNotes(String userId) {
        return fileRepository.findByUserIdOrderByUpdatedAtDesc(userId);
    }

    public File getFileById(String id) {
        return fileRepository.findById(id).get();
    }


    public File createNote(File note) {
        note.setCreatedAt(new Date());
        note.setUpdatedAt(new Date());
        return fileRepository.save(note);
    }

    public File updateNote(String id, File note) {
        File noteToUpdate = fileRepository.findById(id).get();
        noteToUpdate.setUpdatedAt(new Date());
        return fileRepository.save(noteToUpdate);
    }

    public void deleteTask(String id) {
        fileRepository.deleteById(id);
    }
}
