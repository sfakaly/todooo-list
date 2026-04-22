package io.github.sfakaly.controller.commands;

import io.github.sfakaly.service.TaskService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListAction implements Action {
    private final TaskService service;

    @Override
    public String getCode() {
        return "2";
    }

    @Override
    public String getAlias() {
        return "list";
    }

    @Override
    public String getLongDescription() {
        return """
                Команда list выводит таблицу всех ваших задач с их описанием.
                Использование:
                  list — вывести список всех задач.\
                """;
    }

    @Override
    public String getShortDescription() {
        return "Вывести список всех задач";
    }

    @Override
    public void execute(String args) {

    }
}
