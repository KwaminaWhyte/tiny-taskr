package com.freskotek.taskmgnt.controller;

import com.freskotek.taskmgnt.model.Workspace;
import com.freskotek.taskmgnt.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workspaces")
@CrossOrigin(origins = "*")
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;

    @GetMapping
    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        return new ResponseEntity<List<Workspace>>(workspaceService.allWorkspaces(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable("id") String id) {
        return new ResponseEntity<Workspace>(workspaceService.getWorkspaceById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Workspace>> getWorkspacesByUserId(@PathVariable("userId") String userId) {
        return new ResponseEntity<List<Workspace>>(workspaceService.getWorkspacesByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Workspace> createWorkspace(@RequestBody Workspace workspace) {
        return new ResponseEntity<Workspace>(workspaceService.createWorkspace(workspace), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workspace> updateWorkspace(@PathVariable("id") String id, @RequestBody Workspace workspace) {
        return new ResponseEntity<Workspace>(workspaceService.updateWorkspace(id, workspace), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkspace(@PathVariable("id") String id) {
        workspaceService.deleteWorkspace(id);
        return new ResponseEntity<String>("Workspace deleted successfully", HttpStatus.OK);
    }
}
