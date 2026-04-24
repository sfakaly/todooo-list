package io.github.sfakaly.controller;

import io.github.sfakaly.exceptions.InvalidCommandArgumentException;

public class CommandRequest {
    private final String rawArgs;
    private final String[] parts;

    public CommandRequest(String rawArgs) {
        this.rawArgs = rawArgs;
        this.parts = rawArgs.isBlank() ? new String[0] : rawArgs.trim().split("\\s+");
    }

    public boolean hasArgs() {
        return !rawArgs.isBlank();
    }

    public String getPartOfArgs(int index) {
        if (index < 0 || index >= parts.length) return "";
        return parts[index];
    }

    public String getAllArgs() {
        return rawArgs.trim();
    }

    public int getId() {
        if (!hasArgs()) throw new InvalidCommandArgumentException("ID не указан.");
        try {
            return Integer.parseInt(rawArgs.trim());
        } catch (NumberFormatException nfe) {
            throw new InvalidCommandArgumentException("Ошибка! Вводить можно только целые числа.");
        }
    }
}
