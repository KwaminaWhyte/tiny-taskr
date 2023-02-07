package com.freskotek.taskmgnt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "workspaces")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Workspace {
    @Id
    private String id;
    private String name;
    private String description;
    /**
     * user who created the workspace
     */
    private String userId;

    public Workspace(String name, String description, String userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }
}
