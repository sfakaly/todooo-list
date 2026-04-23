package io.github.sfakaly.controller;

import io.github.sfakaly.controller.commands.*;
import io.github.sfakaly.service.TaskService;

import java.util.LinkedHashMap;
import java.util.Map;

public class TaskController {
    private final Map<String, Action> commands = new LinkedHashMap<>();
    private final UserInteraction ui;

    public TaskController(TaskService service, UserInteraction ui) {
        this.ui = ui;
        commands.put("add", new AddAction(service, ui));
        commands.put("list", new ListAction(service, ui));
        commands.put("update", new UpdateAction(service, ui));
        commands.put("delete", new DeleteAction(service, ui));
        commands.put("help", new HelpAction(commands, ui));
        commands.put("exit", new ExitAction());
    }

    public void run() {
        ui.printTimeUntilDeadline();

        while (true) {
            String choice = ui.readString("Введите команду");

            String[] parts = choice.split("\\s+", 2);
            String command = parts[0];
            String args = parts.length > 1 ? parts[1] : "";

            handleCommand(command, args);
        }
    }

    private void handleCommand(String command, String args) {
        Action action = commands.get(command);

        if (action != null) {
            System.out.println();
            action.execute(args);
        } else {
            ui.printError("Неизвестная команда! Введите 'help' для списка команд");
            System.out.println();
        }
    }
}
