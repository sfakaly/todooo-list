package io.github.sfakaly.controller.commands.impl;

import io.github.sfakaly.controller.CommandRequest;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.controller.commands.Action;
import io.github.sfakaly.controller.commands.BaseAction;
import io.github.sfakaly.service.TaskService;

import java.util.Map;

public class HelpAction extends BaseAction {
    private final Map<String, Action> commands;

    public HelpAction(TaskService service, UserInteraction ui, Map<String, Action> commands) {
        super(service, ui);
        this.commands = commands;
    }

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
    public void protectedExecute(CommandRequest request) {
        String args = request.getPartOfArgs(0);
        if (args.isBlank()) printAllCommand();
        else printSpecificCommand(args);
    }

    private void printAllCommand() {
        System.out.println("\nДоступные команды (используйте 'help [команда]' для деталей):");
        for (Action command : commands.values()) {
            System.out.println("  " +  command.getCode() + ". " + command.getAlias() + " — " + command.getShortDescription().toLowerCase() + ".");
        }
        System.out.println();
    }

    private void printSpecificCommand(String command) {
        Action specificCommand = commands.get(command);
        if (specificCommand != null) System.out.println("\n" + specificCommand.getLongDescription() + "\n");
        else ui.printError("Неизвестный аргумент! Введите 'help' для списка команд.");
    }
}
