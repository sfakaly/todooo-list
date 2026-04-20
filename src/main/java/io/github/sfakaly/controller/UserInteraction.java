package io.github.sfakaly.controller;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class UserInteraction {
    private final Scanner scanner = new Scanner(System.in);
    private final TaskController controller;

    public void run() {
        printFullMenu();

        while (true) {
            String choice = readString("Введите номер команды");

            if (choice.equals("exit")) {
                System.out.println("Завершение программы...");
                System.exit(0);
            }

            controller.handleCommand(choice);
        }
    }

    public String readString(String prompt) {
        System.out.print("> " + prompt + ": ");
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                String input = readString(prompt);
                return Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                printError("Ошибка! Вводить можно только целые числа");
            }
        }
    }

    public boolean confirm(String prompt) {
        while (true) {
            String input = readString(prompt);
            switch (input) {
                case "yes" -> {
                    return true;
                }
                case "no" -> {
                    return false;
                }
                default -> printError("Ошибка! Вводить можно только yes/no");
            }
        }
    }

    public void printError(String prompt) {
        System.out.println("\n[!] " + prompt + ".");
    }

    public void printFullMenu() {

    }
}
