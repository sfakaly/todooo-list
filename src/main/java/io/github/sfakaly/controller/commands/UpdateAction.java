package io.github.sfakaly.controller.commands;

public class UpdateAction implements Action {
    @Override
    public String getCode() {
        return "3";
    }

    @Override
    public String getAlias() {
        return "update";
    }

    @Override
    public String getDescription() {
        return """
                Команда update используется для изменения уже существующих задач.
                Использование:
                  update                      — изменение сведений существующей задачи с вводом всех нужных данных.
                  update [ID/название задачи] — изменение значений существующей задачи по введенным данным.
                """;
    }

    @Override
    public void execute() {

    }
}
