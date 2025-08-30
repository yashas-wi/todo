package com.example.demo.service;

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
}
