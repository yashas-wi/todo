package com.example.demo.controller;

import com.example.demo.dto.Status;
import com.example.demo.dto.ToDo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    private final List<ToDo> todoList ;

    public TodoController(List<ToDo> todoList){
        this.todoList = new ArrayList<>(todoList);
    }

    @PostMapping("/save")
    public String save(@RequestBody ToDo toDo){
        try {
            if(toDo.getTitle().isBlank()){
                String title = toDo.getTitle();
            }
        } catch (Exception e) {
            return "Invalid Entry. Please provide title." + e.getMessage();
        }
        try{
            for(ToDo existing: todoList){
                if(existing.getTitle().equalsIgnoreCase(toDo.getTitle())){
                    return "Title already exists";
                }
            }
                if(false) {
                    return "Incomplete Information";
                }
                    todoList.add(toDo);
                    return "Saved Succesfully";
        }
        catch(Exception e) {
            return "Failed due to error, " + e.getMessage();
        }
    }

    private boolean ifIncomplete(ToDo toDo) {
        return toDo.getTitle().isBlank()
                || toDo.getDescription().isBlank()
                || toDo.getTitle() == null
                || toDo.getDescription() == null
                || toDo.getStatus() == null;
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
