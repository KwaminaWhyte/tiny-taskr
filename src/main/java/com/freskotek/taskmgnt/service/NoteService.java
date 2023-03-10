package com.freskotek.taskmgnt.service;

import com.freskotek.taskmgnt.model.Note;
import com.freskotek.taskmgnt.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public List<Note> allNotes() {
        return noteRepository.findAll();
    }

    public List<Note> allUserNotes(String userId) {
//        Pageable pageable = PageRequest.of(0, limit);
        return noteRepository.findByUserIdOrderByUpdatedAtDesc(userId);
    }

    public Note getNoteById(String id) {
        return noteRepository.findById(id).get();
    }

    public List<Note> getNotesByWorkspaceId(String id) {
        return noteRepository.getNotesByWorkspaceIdOrderByUpdatedAtDesc(id);
    }

    public Note createNote(Note note) {
        note.setCreatedAt(new Date());
        note.setUpdatedAt(new Date());
        return noteRepository.save(note);
    }

    public Note updateNote(String id, Note note) {
        Note noteToUpdate = noteRepository.findById(id).get();
        noteToUpdate.setTitle(note.getTitle());
        noteToUpdate.setContent(note.getContent());
        noteToUpdate.setUpdatedAt(new Date());
        noteToUpdate.setColor(note.getColor());
        return noteRepository.save(noteToUpdate);
    }

    public void deleteTask(String id) {
        noteRepository.deleteById(id);
    }
}
