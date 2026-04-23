package io.github.sfakaly.service;

import io.github.sfakaly.model.Task;
import io.github.sfakaly.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public void addTask(String title, boolean isDone, LocalDateTime createdAt) {
        Task task = new Task(0, title, createdAt, isDone);
        repository.saveTask(task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(repository.findAllTasks());
    }

    public void deleteTask(int id) {

    }
}
