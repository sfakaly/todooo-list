package io.github.sfakaly.controller.commands;

import io.github.sfakaly.service.TaskService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteAction implements Action {
    private final TaskService service;

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

    }
}
