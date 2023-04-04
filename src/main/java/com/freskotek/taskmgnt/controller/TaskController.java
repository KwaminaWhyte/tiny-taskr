package com.freskotek.taskmgnt.controller;

import com.freskotek.taskmgnt.model.Task;
import com.freskotek.taskmgnt.model.User;
import com.freskotek.taskmgnt.service.TaskService;
import com.freskotek.taskmgnt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        System.out.println(LocalDateTime.now());
        return new ResponseEntity<List<Task>>(taskService.allTasks(), HttpStatus.OK);
    }

    @GetMapping("/user/tasks")
    public ResponseEntity<List<Task>> getAllUserTasks(@RequestHeader("Authorization") String token) {
        String id = userService.getIdFromToken(token.replace("Bearer ", ""));
        User user = userService.getUserById(id);
        return new ResponseEntity<List<Task>>(taskService.allUserTasks(user.getId(), 7), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") String id) {
        return new ResponseEntity<Task>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Task>(taskService.createTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/move/{id}")
    public ResponseEntity<Task> moveTask(@PathVariable("id") String id, @RequestBody Task task) {
        return new ResponseEntity<Task>(taskService.moveTask(id, task), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") String id, @RequestBody Task task) {
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Task>(taskService.updateTask(id, task), HttpStatus.OK);
    }

    @PostMapping("/mark_done/{id}")
    public ResponseEntity<Task> markDone(@PathVariable("id") String id) {
        return new ResponseEntity<Task>(taskService.markDone(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") String id) {
        taskService.deleteTask(id);
        return new ResponseEntity<String>("Task deleted successfully", HttpStatus.OK);
    }
}
