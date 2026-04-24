package io.github.sfakaly.repository;

import io.github.sfakaly.model.Task;

import java.util.List;

public interface TaskRepository {
    void saveTask(Task task);
    List<Task> findAllTasks();
    void deleteTaskById(int id);
    Task findById(int id);
    void updateStorage();
}
