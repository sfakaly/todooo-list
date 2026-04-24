package io.github.sfakaly.controller.commands;

import io.github.sfakaly.controller.CommandRequest;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.service.TaskService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AddAction implements Action {
    private final TaskService service;
    private final UserInteraction ui;

    @Override
    public String getCode() {
        return "1";
    }

    @Override
    public String getAlias() {
        return "add";
    }

    @Override
    public String getLongDescription() {
        return """
                Команда add позволяет быстро создать новую задачу.
                Использование:
                  add            — создание новой задачи с заполнением всех требуемых данных.
                  add [название] — добавление новой задачи с заполнением всех нужных данных, кроме названия задачи.\
                """;
    }

    @Override
    public String getShortDescription() {
        return "Создание новой задачи с заполнением всех требуемых данных";
    }

    @Override
    public void execute(CommandRequest request) {
        String title = request.getAllArgs();
        if (request.getPartOfArgs(0).equals("0")) {
            System.out.println();
            return;
        }

        if (title.isBlank()) {
            System.out.println();

            while (true) {
                title = ui.readString("Введите название задачи (либо 0 для отмены)");

                if (title.isBlank()) {
                    ui.printError("Нельзя оставлять название пустым!");
                    continue;
                }

                break;
            }
        }

        if (title.equals("0")) {
            ui.printError("Действие отменено");
            return;
        }

        LocalDateTime createdAt = LocalDateTime.now();
        boolean isDone = false; // при инициализации задача не может быть выполнена
        service.addTask(title, isDone, createdAt);
        ui.printSuccessMessage("Задача успешно была добавлена!");
        System.out.println();
    }
}


