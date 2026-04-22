package io.github.sfakaly.controller.commands;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class HelpAction implements Action {
    private final Map<String, Action> commands;

    @Override
    public String getCode() {
        return "5";
    }

    @Override
    public String getAlias() {
        return "help";
    }

    @Override
    public String getLongDescription() {
        return """
                Команда help выводит список всех доступных команд с их кратким описанием.
                Использование:
                  help           — вывести список всех команд.
                  help [команда] — показать подробную инструкцию к конкретной команде.\
                """;
    }

    @Override
    public String getShortDescription() {
        return "Вывести список всех команд";
    }

    @Override
    public void execute(String args) {
        if (args.isBlank()) printAllCommand();
        else printSpecificCommand(args);
    }

    private void printAllCommand() {
        System.out.println("Доступные команды:");
        for (Action command : commands.values()) {
            System.out.println("  " +  command.getCode() + ". " + command.getAlias() + " — " + command.getShortDescription().toLowerCase() + ".");
        }
        System.out.println();
    }

    private void printSpecificCommand(String command) {
        Action specificCommand = commands.get(command);
        try {
            System.out.println(specificCommand.getLongDescription() + "\n");
        } catch (NullPointerException npe) {
            System.out.println("[!] Неизвестный аргумент! Введите 'help' для списка команд.");
        }
    }
}
