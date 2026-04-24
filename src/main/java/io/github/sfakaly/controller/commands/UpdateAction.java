package io.github.sfakaly.controller.commands;

import io.github.sfakaly.controller.CommandRequest;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.model.Task;
import io.github.sfakaly.service.TaskService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateAction implements Action {
    private final TaskService service;
    private final UserInteraction ui;

    @Override
    public String getCode() {
        return "3";
    }

    @Override
    public String getAlias() {
        return "update";
    }

    @Override
    public String getLongDescription() {
        return """
                Команда update используется для изменения уже существующих задач.
                Использование:
                  update                      — изменение сведений существующей задачи с вводом всех нужных данных.
                  update [ID/название задачи] — изменение значений существующей задачи по введенным данным.\
                """;
    }

    @Override
    public String getShortDescription() {
        return "Изменение сведений существующей задачи с вводом всех нужных данных";
    }

    @Override
    public void execute(CommandRequest request) {
        System.out.println();

        int id = request.hasArgs() ? request.getId() : ui.readInt("Введите ID изменяемой задачи (либо 0 для отмены)");
        if (id == 0) {
            ui.printError("Действие отменено");
            return;
        }

        Task task = service.findById(id);
        String newTitle = ui.readString("Введите новое название (либо enter для отмены)");
        boolean newIsDone = ui.confirm("Выполнена ли задача?");
        service.updateTask(task, newTitle, newIsDone);
        ui.printSuccessMessage("Задача успешно изменена!");

        System.out.println();
    }
}
