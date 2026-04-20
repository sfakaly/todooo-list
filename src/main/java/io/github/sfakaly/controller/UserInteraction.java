package io.github.sfakaly.controller;

import java.util.Scanner;

public class UserInteraction {
    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt + ": ");
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                printError("Ошибка! Вводить можно только целые числа");
            }
        }
    }

    public boolean confirm(String prompt) {
        System.out.print(prompt + ": ");
        while (true) {
            String input = scanner.nextLine();
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
        System.err.print(prompt + ": ");
    }
}
