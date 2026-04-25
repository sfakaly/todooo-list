package io.github.sfakaly.controller.commands.impl;

import io.github.sfakaly.controller.CommandRequest;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.controller.commands.BaseAction;
import io.github.sfakaly.model.Task;
import io.github.sfakaly.service.TaskService;

public class DeleteAction extends BaseAction {
    public DeleteAction(TaskService service, UserInteraction ui) {
        super(service, ui);
    }

    @Override
    protected boolean isCancelCheckEnable() {
        return true;
    }

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
                  delete list                 — удаление всего списка.
                
                Будьте осторожны, это действие нельзя отменить.\
                """;
    }

    @Override
    public String getShortDescription() {
        return "Удаление задачи с заполнением нужных данных (ID либо название)";
    }

    @Override
    public void protectedExecute(CommandRequest request) {
        service.isListEmpty();

        if ("list".equals(request.getPartOfArgs(0))) {
            deleteList();
            return;
        }

        int id = request.hasArgs() ? request.getId() : ui.readInt("Введите ID удаляемой задачи (либо 0 для отмены)");

        Task task = service.findById(id);

        if (!ui.confirm("Вы действительно хотите удалить задачу '" + task.getTitle() + "'?")) {
            ui.printError("Удаление задачи отменено.");
            return;
        }

        service.deleteTask(id);
        ui.printSuccessMessage("Задача '" + task.getTitle() + "' успешно стерта.");
        System.out.println();
    }

    private void deleteList() {
        if (ui.confirm("Вы действительно хотите удалить весь список задач?")) {
            service.deleteAllTasks();
            ui.printSuccessMessage("Список задач успешно очищен.");
        } else {
            ui.printError("Очистка списка отменена.");
        }
    }
}