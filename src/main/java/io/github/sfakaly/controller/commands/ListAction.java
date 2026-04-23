package io.github.sfakaly.controller.commands;

import io.github.sfakaly.controller.UserInteraction;
import io.github.sfakaly.model.Task;
import io.github.sfakaly.service.TaskService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListAction implements Action {
    private final TaskService service;
    private final UserInteraction ui;

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
    public void execute(String args) {
        List<Task> tasks = service.getAllTasks();
        System.out.println();

        if (tasks.isEmpty()) {
            String output = """
                    🏖️ Кажется, всё чисто!
                       На данный момент у вас нет задач.
                    """;

            System.out.println(output);
            return;
        }

        System.out.println("📂 TASKS");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String prefix = (i == tasks.size() - 1) ? "└──" : "├──";
            String isDonePrefix = task.isDone() ? "✅" : "⏳";

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
