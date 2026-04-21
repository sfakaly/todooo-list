package io.github.sfakaly.controller.commands;

public class AddAction implements Action {
    @Override
    public String getCode() {
        return "1";
    }

    @Override
    public String getAlias() {
        return "add";
    }

    @Override
    public String getDescription() {
        return """
                Команда add позволяет быстро создать новую задачу.
                Использование:
                  add            — создание новой задачи с заполнением всех требуемых данных.
                  add [название] — добавление новой задачи с заполнением всех нужных данных, кроме названия задачи.
                """;
    }

    @Override
    public void execute() {

    }
}
