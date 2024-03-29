package com.freskotek.taskmgnt.service;

import com.freskotek.taskmgnt.model.Task;
import com.freskotek.taskmgnt.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> allTasks() {
        return taskRepository.findAll();
    }

    public List<Task> allUserTasks(String userId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return taskRepository.findByUserIdOrderByDueDate(userId, pageable);
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id).get();
    }

    public Task createTask(Task task) {
        task.setCreatedAt(new Date());
        task.setUpdatedAt(new Date());
        return taskRepository.save(task);
    }

    public Task updateTask(String id, Task task) {
        Task taskToUpdate = taskRepository.findById(id).get();
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setDueDate(task.getDueDate());
        taskToUpdate.setUpdatedAt(new Date());
        return taskRepository.save(taskToUpdate);
    }

    public Task markDone(String id) {
        Task taskToUpdate = taskRepository.findById(id).get();
        taskToUpdate.setDone(true);
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
