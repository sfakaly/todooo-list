package io.github.sfakaly.controller;

public class TaskController {
    public void handleCommand(String choice) {
        switch (choice) {
            case "add" -> {
                // AddAction.execute
                break;
            }
            case "list" -> {
                // ListAction.execute
                break;
            }
            default -> {
                System.out.println("\n[!] Неизвестная команда! Введите 'help' для списка команд.");
                break;
            }
        }
    }
}
