package io.github.sfakaly.controller.commands;

public interface Action {
    String getCode();
    String getAlias();
    String getDescription();

    public void execute();
}
