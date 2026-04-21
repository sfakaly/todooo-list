package io.github.sfakaly.controller.commands;

/**
 * AddAction - 1, add [name]
 * ListAction - 2, list
 * UpdateAction - 3, update [id/title]
 * DeleteAction - 4, delete [id/title]
 * HelpAction - 5, help [command]
 * */

public interface Action {
    String getCode();
    String getAlias();
    String getDescription();

    public void execute();
}
