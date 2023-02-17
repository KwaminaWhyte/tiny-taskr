package com.freskotek.taskmgnt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "boards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    private String id;
    private String name;
    private String description;
//    @DocumentReference
    private String userId;
    private String color;
//    @DocumentReference
    private String workspaceId;
    private List<Task> tasks;

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
