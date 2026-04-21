package io.github.sfakaly.controller.commands;

public class DeleteAction implements Action {
    @Override
    public String getCode() {
        return "4";
    }

    @Override
    public String getAlias() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return """
                Команда delete безвозвратно удаляет задачу из вашего списка.
                Использование:
                  delete                      — удаление задачи с заполнением нужных данных (ID либо название).
                  delete [ID/название задачи] — стирание задачи по введеным данным.
                
                Будьте осторожны, это действие нельзя отменить.
                """;
    }

    @Override
    public void execute() {

    }
}
