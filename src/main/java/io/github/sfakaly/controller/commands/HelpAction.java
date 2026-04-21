package io.github.sfakaly.controller.commands;

public class HelpAction implements Action {
    @Override
    public String getCode() {
        return "5";
    }

    @Override
    public String getAlias() {
        return "help";
    }

    @Override
    public String getDescription() {
        return """
                Команда help выводит список всех доступных команд с их кратким описанием.
                Использование:
                  help           — вывести список всех команд.
                  help [команда] — показать подробную инструкцию к конкретной команде.
                """;
    }

    @Override
    public void execute() {

    }
}
