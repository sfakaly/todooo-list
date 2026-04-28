package io.github.sfakaly.controller.commands.impl;

import io.github.sfakaly.controller.CommandRequest;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.controller.commands.BaseAction;
import io.github.sfakaly.service.TaskService;

import java.time.LocalDateTime;

public class AddAction extends BaseAction {
    public AddAction(TaskService service, UserInteraction ui) {
        super(service, ui);
    }

    @Override
    protected boolean isCancelCheckEnable() {
        return true;
    }

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
    public void protectedExecute(CommandRequest request) {
        String title = request.getAllArgs().isBlank()
                ? ui.readNotEmptyString("Введите название задачи (либо 0 для отмены)")
                : request.getAllArgs();

        service.addTask(title);
        ui.printSuccessMessage("Задача успешно была добавлена!");
        System.out.println();
    }
}


