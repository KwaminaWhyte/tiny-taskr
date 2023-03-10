package com.freskotek.taskmgnt.service;

import com.freskotek.taskmgnt.model.Note;
import com.freskotek.taskmgnt.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public List<Note> allNotes() {
        return noteRepository.findAll();
    }

    public List<Note> allUserNotes(String userId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return noteRepository.findByUserId(userId, pageable);
    }

    public Note getNoteById(String id) {
        return noteRepository.findById(id).get();
    }

    public Note getNoteByWorkspaceId(String id) {
        return noteRepository.getNoteByWorkspaceId(id);
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(String id, Note note) {
        Note noteToUpdate = noteRepository.findById(id).get();
        noteToUpdate.setTitle(note.getTitle());
        noteToUpdate.setContent(note.getContent());
        return noteRepository.save(noteToUpdate);
    }

    public void deleteTask(String id) {
        noteRepository.deleteById(id);
    }
}
