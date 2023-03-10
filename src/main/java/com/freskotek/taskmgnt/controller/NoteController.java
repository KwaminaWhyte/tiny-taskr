package com.freskotek.taskmgnt.controller;

import com.freskotek.taskmgnt.model.Note;
import com.freskotek.taskmgnt.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        System.out.println(LocalDateTime.now());
        return new ResponseEntity<List<Note>>(noteService.allNotes(), HttpStatus.OK);
    }

    @GetMapping("user/{user_id}")
    public ResponseEntity<List<Note>> getAllUserNotes(@PathVariable("user_id") String user_id) {
        return new ResponseEntity<List<Note>>(noteService.allUserNotes(user_id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") String id) {
        return new ResponseEntity<Note>(noteService.getNoteById(id), HttpStatus.OK);
    }

    @GetMapping("/workspace/{id}")
    public ResponseEntity<List<Note>> getNotesByWorkspaceId(@PathVariable("id") String id) {
        return new ResponseEntity<List<Note>>(noteService.getNotesByWorkspaceId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<Note>(noteService.createNote(note), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") String id, @RequestBody Note note) {
        return new ResponseEntity<Note>(noteService.updateNote(id, note), HttpStatus.OK);
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<String> deleteTask(@PathVariable("id") String id) {
    // taskService.deleteTask(id);
    // return new ResponseEntity<String>("Task deleted successfully",
    // HttpStatus.OK);
    // }
}
