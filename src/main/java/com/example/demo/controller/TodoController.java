package com.example.demo.controller;

import com.example.demo.dto.Status;
import com.example.demo.dto.ToDo;
import com.example.demo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/save")
    public String save(@RequestBody ToDo toDo){
        return todoService.save(toDo);
    }

    @GetMapping("/title")
    public ToDo getTodoFromTitle(@RequestParam String title){
        for(ToDo todo: todoList){
            if(todo.getTitle().equalsIgnoreCase(title)) return todo;
        }
        return null;
    }

    @PutMapping("/updateStatus")
    public String update(@RequestParam String status ,@RequestParam String title ){
        Status currentStatus = switch (status) {
            case "pending" -> Status.PENDING;
            case "completed" -> Status.COMPLETED;
            case "in progress" -> Status.IN_PROGRESS;
            default -> throw new IllegalArgumentException("Invalid status");
        };
        for(ToDo todo: todoList){
            if(todo.getTitle().equalsIgnoreCase(title)){
                todo.setStatus(currentStatus);
                return "Updated Successfully";
            }
        }
        return "Title not found";
    }

    @GetMapping("/lists")
    public List<ToDo> lists(){
        return todoList;
    }

    @GetMapping("/updateDescription")
    public String updateD(@RequestParam String description , @RequestParam String title){
        for(ToDo todo : todoList){
            if(todo.getTitle().equalsIgnoreCase(title)){
                todo.setDescription(description);
                todo.setUpdated();
                return "Updated Successfully";
            }
        }
        return "Title not found";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String title){
        for(ToDo todo: todoList){
            if(todo.getTitle().equalsIgnoreCase(title)){
                todoList.remove(todo);
                return "Todo removed";
            }
        }
        return "Title not found";
    }
}
