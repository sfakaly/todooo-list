package io.github.sfakaly.service;

import io.github.sfakaly.exceptions.TaskNotFoundException;
import io.github.sfakaly.model.Task;
import io.github.sfakaly.repository.JsonTaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private JsonTaskRepository repository;

    @InjectMocks
    private TaskService service;

    @Test
    void shouldDeleteTaskWhenTaskExists() {
        int taskId = 8;
        Task mockTask = new Task();
        when(repository.findById(taskId)).thenReturn(mockTask);
        service.deleteTask(taskId);
        verify(repository).deleteTaskById(taskId);
    }

    @Test
    void shouldNotInvokeDeleteWhenTaskNotFound() {
        int taskId = 8;
        when(repository.findById(taskId)).thenReturn(null);
        assertThrows(TaskNotFoundException.class, () -> service.deleteTask(taskId));
        verify(repository, never()).deleteTaskById(taskId);
    }

    @Test
    void shouldSaveCorrectTaskWhenAddingTask() {
        String taskTitle = "Saveliyyy";
        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);

        service.addTask(taskTitle);
        verify(repository).saveTask(taskCaptor.capture());
        Task savedTask = taskCaptor.getValue();

        assertAll("Проверка полей задачи",
                () -> assertEquals(taskTitle, savedTask.getTitle(), "При сохранении задачи название поменялось"),
                () -> assertFalse(savedTask.isCompleted(), "При инициализации статус не был false")
        );
    }

    @Test
    void shouldCorrectUpdateTaskDataWhenUpdateTask() {
        Task task = new Task();
        task.setCompleted(false);
        String newTitle = "Saveliyyy";

        service.updateTask(task, newTitle, true);

        verify(repository, times(1)).updateStorage();
        assertAll("Проверка полей задачи",
                () -> assertEquals(newTitle, task.getTitle(), "Название не обновилось"),
                () -> assertTrue(task.isCompleted(), "Статус не изменился на true")
        );
    }
}
