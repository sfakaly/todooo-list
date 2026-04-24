package io.github.sfakaly.controller.commands;

import io.github.sfakaly.controller.CommandRequest;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.model.Task;
import io.github.sfakaly.service.TaskService;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ConcurrentMap;

@RequiredArgsConstructor
public class DeleteAction implements Action {
    private final TaskService service;
    private final UserInteraction ui;

    @Override
    public String getCode() {
        return "4";
    }

    @Override
    public String getAlias() {
        return "delete";
    }

    @Override
    public String getLongDescription() {
        return """
                Команда delete безвозвратно удаляет задачу из вашего списка.
                Использование:
                  delete                      — удаление задачи с заполнением нужных данных (ID либо название).
                  delete [ID/название задачи] — стирание задачи по введеным данным.
                
                Будьте осторожны, это действие нельзя отменить.\
                """;
    }

    @Override
    public String getShortDescription() {
        return "Удаление задачи с заполнением нужных данных (ID либо название)";
    }

    @Override
    public void execute(CommandRequest request) {
        int id = request.hasArgs() ? request.getId() : ui.readInt("Введите ID удаляемой задачи (либо 0 для отмены)");
        if (id == 0) return;

        Task task = service.findById(id);

        if (ui.confirm("Вы действительно хотите удалить эту задачу?")) {
            service.deleteTask(id);
            ui.printSuccessMessage("Задача '" + task.getTitle() + "' успешно стерта.");
            System.out.println();
        } else ui.printError("Действие отменено");
    }
}