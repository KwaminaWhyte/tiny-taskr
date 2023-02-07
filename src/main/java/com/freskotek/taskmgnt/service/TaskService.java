package com.freskotek.taskmgnt.service;

import com.freskotek.taskmgnt.model.Task;
import com.freskotek.taskmgnt.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> allTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id).get();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // public List<Task> getTasksByBoardId(String boardId) {
    // return taskRepository.findByWorkspaceId(boardId);
    // }

    public Task updateTask(String id, Task task) {
        Task taskToUpdate = taskRepository.findById(id).get();
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setBoardId(task.getBoardId());
        return taskRepository.save(taskToUpdate);
    }

    public Task moveTask(String id, Task task) {
        Task taskToUpdate = taskRepository.findById(id).get();
        taskToUpdate.setBoardId(task.getBoardId());
        return taskRepository.save(taskToUpdate);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}
