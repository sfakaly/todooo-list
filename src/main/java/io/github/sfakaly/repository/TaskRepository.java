package io.github.sfakaly.repository;

import io.github.sfakaly.model.DataStorage;
import io.github.sfakaly.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private final JsonHandler jsonHandler;
    private final DataStorage storage;

    public TaskRepository(JsonHandler jsonHandler) {
        this.jsonHandler = jsonHandler;
        this.storage = jsonHandler.loadStorage();
    }

    public void saveTask(Task task) {
        int newId = storage.getLastId() + 1;
        task.setId(newId);
        storage.setLastId(newId);

        storage.getTasks().add(task);
        jsonHandler.saveStorage(storage);
    }

    public List<Task> findAllTasks() {
        return new ArrayList<>(storage.getTasks());
    }
}
