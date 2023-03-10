package com.freskotek.taskmgnt.controller;

import com.freskotek.taskmgnt.model.Note;
import com.freskotek.taskmgnt.model.Task;
import com.freskotek.taskmgnt.service.NoteService;
import com.freskotek.taskmgnt.service.TaskService;
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
    public ResponseEntity<List<Note>> getAllTasks() {
        System.out.println(LocalDateTime.now());
        return new ResponseEntity<List<Note>>(noteService.allNotes(), HttpStatus.OK);
    }

//    @GetMapping("/{user_id}/tasks")
//    public ResponseEntity<List<Task>> getAllUserTasks(@PathVariable("user_id") String userId) {
//        return new ResponseEntity<List<Task>>(noteService.allNotes(userId, 7), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") String id) {
        return new ResponseEntity<Note>(noteService.getNoteById(id), HttpStatus.OK);
    }

    @GetMapping("/workspace/{id}")
    public ResponseEntity<Note> getNoteByWorkspaceId(@PathVariable("id") String id) {
        return new ResponseEntity<Note>(noteService.getNoteByWorkspaceId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<Note>(noteService.createNote(note), HttpStatus.CREATED);
    }


//    @PutMapping("/move/{id}")
//    public ResponseEntity<Task> moveTask(@PathVariable("id") String id, @RequestBody Task task) {
//        return new ResponseEntity<Task>(noteService.moveTask(id, task), HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") String id, @RequestBody Note note) {
        return new ResponseEntity<Note>(noteService.updateNote(id, note), HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteTask(@PathVariable("id") String id) {
//        taskService.deleteTask(id);
//        return new ResponseEntity<String>("Task deleted successfully", HttpStatus.OK);
//    }
}
