package io.github.sfakaly.controller.commands;

public class ListAction implements Action {
    @Override
    public String getCode() {
        return "2";
    }

    @Override
    public String getAlias() {
        return "list";
    }

    @Override
    public String getDescription() {
        return """
                Команда list выводит таблицу всех ваших задач с их описанием.
                Использование:
                  list — вывести список всех задач.
                """;
    }

    @Override
    public void execute() {

    }
}
