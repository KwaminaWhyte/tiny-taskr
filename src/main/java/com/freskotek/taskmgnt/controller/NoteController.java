package com.freskotek.taskmgnt.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.freskotek.taskmgnt.model.File;
import com.freskotek.taskmgnt.model.Note;
import com.freskotek.taskmgnt.repository.FileRepository;
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
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private FileRepository fileRepository;

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

//    upload file to note
    @PostMapping(path = "/file/upload_file", consumes = "multipart/form-data")
    public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file,
                                           @RequestParam String userId,
                                           @RequestParam String workspaceId,
                                           @RequestParam String noteId ) throws IOException {
        System.out.println("upload file");
        if (!file.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            String publicId = (String) uploadResult.get("public_id");
            String cloudinaryUrl = cloudinary.url().format(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1)).generate(publicId);
            System.out.println(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));

            File fileUpload = new File();
            fileUpload.setUrl(cloudinaryUrl);
            fileUpload.setName(file.getOriginalFilename());
            fileUpload.setType(file.getContentType());
            fileUpload.setSize(file.getSize());
            fileUpload.setUserId(userId);
            fileUpload.setWorkspaceId(workspaceId);
            fileUpload.setNoteId(noteId);
            fileUpload.setCreatedAt(new Date());
            fileUpload.setUpdatedAt(new Date());

            return new ResponseEntity<File>(fileRepository.save(fileUpload), HttpStatus.OK);

        } else {
            System.out.println("error uploading file");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    // @DeleteMapping("/{id}")
    // public ResponseEntity<String> deleteTask(@PathVariable("id") String id) {
    // taskService.deleteTask(id);
    // return new ResponseEntity<String>("Task deleted successfully",
    // HttpStatus.OK);
    // }
}
