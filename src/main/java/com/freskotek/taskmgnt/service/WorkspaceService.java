package com.freskotek.taskmgnt.service;

import com.freskotek.taskmgnt.model.Workspace;
import com.freskotek.taskmgnt.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceService {
    @Autowired
    private WorkspaceRepository workspaceRepository;

    public List<Workspace> allWorkspaces() {
        return workspaceRepository.findAll();
    }

    public Workspace getWorkspaceById(String id) {
        return workspaceRepository.findById(id).get();
    }

    public Workspace createWorkspace(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }

    public List<Workspace> getWorkspacesByUserId(String userId) {
        return workspaceRepository.findByUserId(userId);
    }

    public Workspace updateWorkspace(String id, Workspace workspace) {
        Workspace workspaceToUpdate = workspaceRepository.findById(id).get();
        workspaceToUpdate.setName(workspace.getName());
        workspaceToUpdate.setDescription(workspace.getDescription());
        return workspaceRepository.save(workspaceToUpdate);
    }

    public void deleteWorkspace(String id) {
        workspaceRepository.deleteById(id);
    }

}
