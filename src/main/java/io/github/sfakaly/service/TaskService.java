package io.github.sfakaly.service;

import io.github.sfakaly.exceptions.EmptyListException;
import io.github.sfakaly.model.Task;
import io.github.sfakaly.repository.JsonTaskRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TaskService {
    private final JsonTaskRepository repository;

    public void addTask(String title, boolean isDone, LocalDateTime createdAt) {
        Task task = new Task(0, title, createdAt, isDone);
        repository.saveTask(task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(repository.findAllTasks());
    }

    public void deleteTask(int id) {
        repository.findById(id);
        repository.deleteTaskById(id);
    }

    public Task findById(int id) {
        return repository.findById(id);
    }

    public void updateTask(Task task, String newTitle, boolean newIsDone) {
        if (!newTitle.isBlank()) task.setTitle(newTitle);
        task.setIsDone(newIsDone);
        repository.updateStorage();
    }

    public void deleteAllTasks() {
        repository.deleteAllTasks();
    }

    public void isListEmpty() {
        if (getAllTasks().isEmpty()) {
            throw new EmptyListException("Список задач пуст!");
        }
    }
}
