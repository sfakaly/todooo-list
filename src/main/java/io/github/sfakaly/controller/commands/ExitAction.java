package io.github.sfakaly.controller.commands;

import io.github.sfakaly.controller.CommandRequest;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.service.TaskService;

public class ExitAction extends BaseAction {
    public ExitAction(TaskService service, UserInteraction ui) {
        super(service, ui);
    }

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
    public void protectedExecute(CommandRequest request) {
        System.out.print("Завершение программы...");
        System.out.println();
        System.exit(0);
    }
}
