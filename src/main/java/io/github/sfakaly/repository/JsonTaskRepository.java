package io.github.sfakaly.repository;

import io.github.sfakaly.exceptions.TaskNotFoundException;
import io.github.sfakaly.model.DataStorage;
import io.github.sfakaly.model.Task;

import java.util.ArrayList;
import java.util.List;

public class JsonTaskRepository implements TaskRepository {
    private final JsonHandler jsonHandler;
    private final DataStorage storage;

    public JsonTaskRepository(JsonHandler jsonHandler) {
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

    public void deleteTaskById(int id) {
        storage.getTasks()
                .removeIf(t -> t.getId() == id);
        jsonHandler.saveStorage(storage);
    }

    public Task findById(int id) {
        for (Task t : storage.getTasks()) {
            if (t.getId() == id) {
                return t;
            }
        }

        throw new TaskNotFoundException("Задачи с ID " + id + " не существует.");
    }

    public void updateStorage() {
        jsonHandler.saveStorage(storage);
    }

    public void deleteAllTasks() {
        storage.getTasks().clear();
        jsonHandler.saveStorage(storage);
    }
}
