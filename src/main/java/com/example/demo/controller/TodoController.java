package com.example.demo.controller;

import com.example.demo.dto.ToDo;
import com.example.demo.service.TodoService;
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
    public String save(@RequestBody ToDo toDo){
        return todoService.save(toDo);
    }

    @GetMapping("/title")
    public ToDo getTodoFromTitle(@RequestParam String title){
        return todoService.title(title);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestParam String title, @RequestParam String status) {
        try{
            todoService.updateStatus(title, status);
            return ResponseEntity.ok().body("Status updated");
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/lists")
    public List<ToDo> lists(){
        return todoService.lists();
    }


    @PutMapping("/updateDescription")
    public String updateD(@RequestParam String description , @RequestParam String title){
        return todoService.updateDescription(title,description);
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String title) {
        return todoService.delete(title);
    }
}
