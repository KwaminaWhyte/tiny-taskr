package com.freskotek.taskmgnt.service;

import com.freskotek.taskmgnt.model.Board;
import com.freskotek.taskmgnt.model.Task;
import com.freskotek.taskmgnt.repository.BoardRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Board> allBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(String id) {
        return boardRepository.findById(id).get();
    }

    public Board createBoard(Board board) {
        if (board.getName() == null || board.getName().isEmpty()) {
            throw new IllegalArgumentException("Board name is required");
        }
        return boardRepository.save(board);
    }

    public List<Board> getBoardsByWorkspaceId(String workspaceId) {
        return boardRepository.findByWorkspaceId(workspaceId);
    }

    public Board updateBoard(String id, Board workspace) {
        Board workspaceToUpdate = boardRepository.findById(id).get();
        workspaceToUpdate.setName(workspace.getName());
        workspaceToUpdate.setDescription(workspace.getDescription());
        return boardRepository.save(workspaceToUpdate);
    }

    public List<Board> getBoardsWithTasks(String workspaceId) {
        // Get the boards collection
        MongoCollection<Document> boardsCollection = mongoTemplate.getCollection("boards");

        // Get the tasks collection
        MongoCollection<Document> tasksCollection = mongoTemplate.getCollection("tasks");

        // Query the tasks collection to get all tasks that belong to the specified workspace
        FindIterable<Document> tasks = tasksCollection.find(new Document("workspaceId", workspaceId));

        // Create a map to store the tasks for each board
        Map<String, List<Task>> boardTasksMap = new HashMap<>();
        for (Document task : tasks) {
            String boardId = (String) task.get("boardId");
            Task t = new Task();
            // Convert the task document to a Task object and add it to the map
            t.setId(String.valueOf((ObjectId) task.get("_id")));
            t.setTitle((String) task.get("title"));
            t.setDescription((String) task.get("description"));
            t.setDueDate((String) task.get("dueDate"));
            t.setBoardId((String) task.get("boardId"));
            t.setWorkspaceId((String) task.get("workspaceId"));

            List<Task> tasksForBoard = boardTasksMap.get(boardId);
            if (tasksForBoard == null) {
                tasksForBoard = new ArrayList<Task>();
//                tasksForBoard = new ArrayList<>();
                boardTasksMap.put(boardId, tasksForBoard);
            }
            tasksForBoard.add(t);
        }

        // Query the boards collection to get all boards for the specified workspace
        FindIterable<Document> boards = boardsCollection.find(new Document("workspaceId", workspaceId));

        // Convert the result to the Board objects
        List<Board> result = new ArrayList<>();
        for (Document board : boards) {
            Board b = new Board();
            b.setId(String.valueOf((ObjectId) board.get("_id")));
            b.setName(String.valueOf(board.get("name")));
            b.setDescription((String) board.get("description"));
            b.setWorkspaceId((String) board.get("workspaceId"));
            b.setColor((String) board.get("color"));
            // ... add any other fields as needed
            b.setTasks(boardTasksMap.get(b.getId()));
            result.add(b);
        }

        return result;
    }

    public void deleteBoard(String id) {
        boardRepository.deleteById(id);
    }

}
