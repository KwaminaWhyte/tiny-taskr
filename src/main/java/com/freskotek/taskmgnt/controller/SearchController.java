package com.freskotek.taskmgnt.controller;

import com.freskotek.taskmgnt.model.Board;
import com.freskotek.taskmgnt.model.SearchResult;
import com.freskotek.taskmgnt.model.Task;
import com.freskotek.taskmgnt.model.Workspace;
import com.freskotek.taskmgnt.repository.BoardRepository;
import com.freskotek.taskmgnt.repository.TaskRepository;
import com.freskotek.taskmgnt.repository.WorkspaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/search")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class SearchController {
    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private  final BoardRepository boardRepository;
    @Autowired
    private final WorkspaceRepository workspaceRepository;
    @GetMapping
    public ResponseEntity<List<SearchResult>> search(@RequestParam("query") String query) {
        System.out.println(query);
        List<SearchResult> results = new ArrayList<>();

        // Search for matching tasks
        List<Task> tasks = taskRepository.findByTitleContainingIgnoreCase(query);
        for (Task task : tasks) {
            SearchResult result = new SearchResult();
            result.setType("task");
            result.setId(task.getId());
            result.setName(task.getTitle());
            result.setUrl("/tasks/" + task.getId());
            results.add(result);
        }

        // Search for matching boards
        List<Board> boards = boardRepository.findByNameContainingIgnoreCase(query);
        for (Board board : boards) {
            SearchResult result = new SearchResult();
            result.setType("board");
            result.setId(board.getId());
            result.setName(board.getName());
            result.setUrl("/boards/" + board.getId());
            results.add(result);
        }

        // Search for matching workspaces
        List<Workspace> workspaces = workspaceRepository.findByNameContainingIgnoreCase(query);
        for (Workspace workspace : workspaces) {
            SearchResult result = new SearchResult();
            result.setType("workspace");
            result.setId(workspace.getId());
            result.setName(workspace.getName());
            result.setUrl("/workspace/" + workspace.getId());
            results.add(result);
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
