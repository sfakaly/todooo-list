package io.github.sfakaly.service;

import io.github.sfakaly.model.DataStorage;
import io.github.sfakaly.model.Task;
import io.github.sfakaly.repository.JsonHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> tasks;
    private int lastId;
    private final JsonHandler jsonHandler;
    private final DataStorage storage;

    public TaskService(JsonHandler jsonHandler) {
        this.jsonHandler = jsonHandler;
        this.storage = jsonHandler.loadStorage();
        this.tasks = storage.getTasks();
        this.lastId = storage.getLastId();
    }

    public void addTask(String title, boolean isDone, LocalDateTime createdAt) {
        lastId++;
        Task task = new Task(lastId, title, createdAt, isDone);
        tasks.add(task);
        jsonHandler.saveStorage(new DataStorage(tasks, lastId));
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(this.tasks);
    }

    public void deleteTask(int id) {

    }
}
