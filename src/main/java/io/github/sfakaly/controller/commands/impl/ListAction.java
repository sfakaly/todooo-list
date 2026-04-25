package io.github.sfakaly.controller.commands.impl;

import io.github.sfakaly.controller.CommandRequest;
import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.controller.commands.BaseAction;
import io.github.sfakaly.model.Task;
import io.github.sfakaly.service.TaskService;

import java.util.List;

public class ListAction extends BaseAction {
    public ListAction(TaskService service, UserInteraction ui) {
        super(service, ui);
    }

    @Override
    public String getCode() {
        return "2";
    }

    @Override
    public String getAlias() {
        return "list";
    }

    @Override
    public String getLongDescription() {
        return """
                Команда list выводит таблицу всех ваших задач с их описанием.
                Использование:
                  list — вывести список всех задач.\
                """;
    }

    @Override
    public String getShortDescription() {
        return "Вывести список всех задач";
    }

    @Override
    public void protectedExecute(CommandRequest request) {
        List<Task> tasks = service.getAllTasks();
        service.isListEmpty();
        System.out.println();
        printList(tasks);
    }

    private void printList(List<Task> tasks) {
        System.out.println("📂 TASKS");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String prefix = (i == tasks.size() - 1) ? "└──" : "├──";
            String isDonePrefix = task.isCompleted() ? "✅" : "⏳";

            String taskInfo = String.format("%s [ID: %d] %s %s",
                    prefix,
                    task.getId(),
                    isDonePrefix,
                    task.getTitle());

            System.out.println(taskInfo);
        }

        System.out.println();
    }
}
