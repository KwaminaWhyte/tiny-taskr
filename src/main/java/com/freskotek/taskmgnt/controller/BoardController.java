package com.freskotek.taskmgnt.controller;

import com.freskotek.taskmgnt.model.Board;
import com.freskotek.taskmgnt.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.Document;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@CrossOrigin(origins = "*")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping
    public ResponseEntity<List<Board>> getAllBoards() {
        return new ResponseEntity<List<Board>>(boardService.allBoards(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable("id") String id) {
        System.out.println("get by id");
        return new ResponseEntity<>(boardService.getBoardById(id), HttpStatus.OK);
    }

    @GetMapping("/inc_tasks/{workspaceId}")
    public List<Board> getBoardsWithTasks(@PathVariable String workspaceId) {
        return boardService.getBoardsWithTasks(workspaceId);
    }

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<Board>> getBoardsByWorkspaceId(@PathVariable("workspaceId") String workspaceId) {
        return new ResponseEntity<List<Board>>(boardService.getBoardsByWorkspaceId(workspaceId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        return new ResponseEntity<Board>(boardService.createBoard(board), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable("id") String id, @RequestBody Board board) {
        return new ResponseEntity<Board>(boardService.updateBoard(id, board), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("id") String id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<String>("Board deleted successfully", HttpStatus.OK);
    }
}
