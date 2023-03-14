package com.freskotek.taskmgnt.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.freskotek.taskmgnt.model.File;
import com.freskotek.taskmgnt.model.Note;
import com.freskotek.taskmgnt.repository.FileRepository;
import com.freskotek.taskmgnt.service.FileService;
import com.freskotek.taskmgnt.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
public class FileController {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileService fileService;

    @GetMapping
    public ResponseEntity<List<File>> getAllFiles() {
        System.out.println(LocalDateTime.now());
        return new ResponseEntity<List<File>>(fileService.allNotes(), HttpStatus.OK);
    }

    @GetMapping("note/{note_id}")
    public ResponseEntity<List<File>> getAllNotesFiles(@PathVariable("note_id") String note_id) {
        return new ResponseEntity<List<File>>(fileService.allNotesFiles(note_id), HttpStatus.OK);
    }

    @GetMapping("user/{user_id}")
    public ResponseEntity<List<File>> getAllUserFiles(@PathVariable("user_id") String user_id) {
        return new ResponseEntity<List<File>>(fileService.allUserFiles(user_id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<File> getNoteById(@PathVariable("id") String id) {
        return new ResponseEntity<File>(fileService.getFileById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<File> createNote(@RequestBody File note) {
        return new ResponseEntity<File>(fileService.createNote(note), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<File> updateNote(@PathVariable("id") String id, @RequestBody File note) {
        return new ResponseEntity<File>(fileService.updateNote(id, note), HttpStatus.OK);
    }

}
