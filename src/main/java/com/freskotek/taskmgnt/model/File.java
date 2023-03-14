package com.freskotek.taskmgnt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    private String id;
    private String name;
    private String type;
    private Long size;
    private String url;
    private String userId;
    private String noteId;
    private String workspaceId;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
}
