package com.example.demo.dto;

import java.time.LocalDateTime;

public class ToDo {
    private String title;
    private String description;
    private Status status;
    private LocalDateTime created;
    private LocalDateTime updated;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ToDo() {
        this.status = Status.PENDING;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public ToDo(Status status){
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated() {
        this.created = LocalDateTime.now();
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated() {
        this.updated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}

