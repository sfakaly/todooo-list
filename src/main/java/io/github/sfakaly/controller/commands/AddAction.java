package io.github.sfakaly.controller.commands;

import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.service.TaskService;
import lombok.RequiredArgsConstructor;

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
    public void execute(String args) {

    }

}


