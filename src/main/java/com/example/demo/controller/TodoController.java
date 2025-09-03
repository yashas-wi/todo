package com.example.demo.controller;

import com.example.demo.dto.ToDo;
import com.example.demo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private final TodoService todoService;
    private List<ToDo> todoList;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/save")
    public ResponseEntity <String> save(@RequestBody ToDo toDo){
        try {
            String response = todoService.save(toDo);
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/title")
    public ResponseEntity <ToDo> getTodoFromTitle(@RequestParam String title){
        try {
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestParam String title, @RequestParam String status) {
        try{
            String response = todoService.updateStatus(title, status);
            return ResponseEntity.ok().body(response);
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/lists")
    public ResponseEntity<List<ToDo>> lists(){
        try {
            List<ToDo> response = todoService.lists();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/updateDescription")
    public ResponseEntity<String> updateD(@RequestParam String description , @RequestParam String title){
        try{
            String response = todoService.updateDescription(description, title);
            return ResponseEntity.ok().body(response);
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/delete")
    public ResponseEntity <String> delete(@RequestParam String title) {
        try {
            String response = todoService.delete(title);
            return ResponseEntity.ok().body(response);
        }catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
