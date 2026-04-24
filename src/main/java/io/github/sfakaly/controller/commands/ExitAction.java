package io.github.sfakaly.controller.commands;

import io.github.sfakaly.controller.CommandRequest;

public class ExitAction implements Action {
    @Override
    public String getCode() {
        return "0";
    }

    @Override
    public String getAlias() {
        return "exit";
    }

    @Override
    public String getLongDescription() {
        return """
                Команда exit позволяет завершить работу программы.
                Использование:
                  exit — выход из программы.\
                """;
    }

    @Override
    public String getShortDescription() {
        return "Выход из программы";
    }

    @Override
    public void execute(CommandRequest request) {
        System.out.print("Завершение программы...");
        System.out.println();
        System.exit(0);
    }
}
