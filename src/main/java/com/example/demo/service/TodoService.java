package com.example.demo.service;

import com.example.demo.dto.Status;
import com.example.demo.dto.ToDo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private List<ToDo> todoList;

    public TodoService() {
        this.todoList = new ArrayList<>();
    }

    public String save(ToDo toDo) {
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
            todoList.add(toDo);
            return "Saved Successfully";
        }
        catch(Exception e) {
            return "Failed due to error, " + e.getMessage();
        }
    }

    public List<ToDo> lists() {
        return todoList;
    }

    public ToDo title(String title){
            for(ToDo todo: todoList){
                if(todo.getTitle().equalsIgnoreCase(title))
                    return todo;
            }
            return null;
    }

    public String updateStatus(String title, String status ){
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
        throw new IllegalArgumentException("Invalid title");
    }

    public String updateDescription(String title, String description){
        for(ToDo todo : todoList){
            if(todo.getTitle().equalsIgnoreCase(title)){
                todo.setDescription(description);
                todo.setUpdated();
                return "Updated Successfully";
            }
        }
        throw new IllegalArgumentException("Title not found");
    }

    public  String delete(String title){
        for(ToDo todo : todoList){
            if(todo.getTitle().equalsIgnoreCase(title)){
//               todo.setDescription(description);
                todoList.remove(todo);
                todo.setUpdated();
                return "Deleted Successfully";
            }
        }
        return "Title not found";
    }


}
