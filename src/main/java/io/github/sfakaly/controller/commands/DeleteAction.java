package io.github.sfakaly.controller.commands;

import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.model.Task;
import io.github.sfakaly.service.TaskService;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    public void execute(String args) {
        List<Task> tasks = service.getAllTasks();
        if (tasks.isEmpty()) {
            ui.printError("🚫 Нечего удалять: список пуст.");
            return;
        }

        int id = getInputId(args);
        Task task = searchTaskById(tasks, id);

        if (task == null) {
            System.out.println();
            return;
        }

        boolean deletionConfirm = ui.confirm("Вы действительно хотите удалить эту задачу?");
        if (deletionConfirm) {
            service.deleteTask(task.getId());
            ui.printSuccessMessage("Задача '" + task.getTitle() + "' успешно стерта.");
            System.out.println();
        } else ui.printError("Действие отменено");
    }

    private Task searchTaskById(List<Task> tasks, int id) {
        while (true) {
            if (isZero(id)) return null;
            for (Task t : tasks) {
                if (t.getId() == id) {
                    return t;
                }
            }

            ui.printError("Задачи с таким ID не существует");
            id = getInputId("");
        }
    }

    private int getInputId(String args) {
        if (!args.isEmpty()) {
            try {
                return Integer.parseInt(args);
            } catch (NumberFormatException nfe) {
                ui.printError("Ошибка! Вводить можно только целые числа.");
            }
        }
            System.out.println();
            return ui.readInt("Введите ID удаляемой задачи (либо '0' для отмены)");
    }

    private boolean isZero(int id) {
        return id == 0;
    }
}
