package io.github.sfakaly.controller.commands;

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
                  exit — выход из программы.
                """;
    }

    @Override
    public String getShortDescription() {
        return "Выход из программы";
    }

    @Override
    public void execute(String args) {
        System.out.print("Завершение программы...");
        System.exit(0);
    }
}
