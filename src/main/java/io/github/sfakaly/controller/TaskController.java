package io.github.sfakaly.controller;

import io.github.sfakaly.controller.commands.*;
import io.github.sfakaly.service.TaskService;

import java.util.LinkedHashMap;
import java.util.Map;

public class TaskController {
    private final Map<String, Action> commands = new LinkedHashMap<>();

    public TaskController(TaskService service) {
        commands.put("add", new AddAction(service));
        commands.put("list", new ListAction(service));
        commands.put("update", new UpdateAction(service));
        commands.put("delete", new DeleteAction(service));
        commands.put("help", new HelpAction(commands));
        commands.put("exit", new ExitAction());
    }

    public void handleCommand(String command, String args) {
        Action action = commands.get(command);

        if (action != null) {
            System.out.println();
            action.execute(args);
        } else System.out.println("\n[!] Неизвестная команда! Введите 'help' для списка команд.");
    }
}
