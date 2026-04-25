package io.github.sfakaly.controller;

import io.github.sfakaly.controller.commands.*;
import io.github.sfakaly.controller.commands.impl.*;
import io.github.sfakaly.exceptions.OperationCancelledException;
import io.github.sfakaly.exceptions.EmptyListException;
import io.github.sfakaly.exceptions.InvalidCommandArgumentException;
import io.github.sfakaly.exceptions.TaskNotFoundException;
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
        commands.put("help", new HelpAction(service, ui, commands));
        commands.put("exit", new ExitAction(service, ui));
    }

    public void run() {
        ui.printTimeUntilDeadline();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String choice = ui.readString("Введите команду");

                String[] parts = choice.split("\\s+", 2);
                String command = parts[0];
                String args = parts.length > 1 ? parts[1] : "";

                CommandRequest request = new CommandRequest(args);

                handleCommand(command, request);
            } catch (OperationCancelledException oce) {
                ui.printError(oce.getMessage());
            }
        }
    }

    private void handleCommand(String command, CommandRequest request) {
        Action action = commands.get(command);

        if (action != null) {
            try {
                action.execute(request);
            } catch (TaskNotFoundException
                     | InvalidCommandArgumentException
                     | EmptyListException
                     | OperationCancelledException e) {
                ui.printError(e.getMessage());
            }
        } else ui.printError("Неизвестная команда! Введите 'help' для списка команд.");
    }
}