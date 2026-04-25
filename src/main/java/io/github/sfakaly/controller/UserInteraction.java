package io.github.sfakaly.controller;

import io.github.sfakaly.exceptions.OperationCancelledException;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Scanner;

@RequiredArgsConstructor
public class UserInteraction {
    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.print("> " + prompt + ": ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("0")) throw new OperationCancelledException("Действие отменено.");
        return input;
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                String input = readString(prompt);
                return Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                printError("Ошибка! Вводить можно только целые числа.");
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
                default -> printError("Ошибка! Вводить можно только yes/no.");
            }
        }
    }

    public void printError(String prompt) {
        System.out.println("[!] " + prompt + "\n");
    }

    public void printSuccessMessage(String prompt) {
        System.out.println("[✓] " + prompt);
    }

    // temporary method, потом организовать постановку дедлайна любой задачи загружаться первым
    public void printTimeUntilDeadline() {
        LocalDateTime deadline = LocalDateTime.of(2026, Month.JULY, 12, 0, 0); // for test
        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(now, deadline);

        if (diff.isZero() || diff.isNegative()) {
            System.out.println("⌛️ Обратный отсчет был приостановлен...");
            String output = String.format("Событие '%s' наступило!", "Летние каникулы"); // "Летние каникулы" for test
            System.out.println(output + "\n");
            return;
        }

        System.out.println("⏳ Обратный отсчет запущен...");
        String output = String.format("До события '%s' осталось: %d дн. %02d:%02d:%02d",
                "Летние каникулы", // for test
                diff.toDaysPart(),
                diff.toHoursPart(),
                diff.toMinutesPart(),
                diff.toSecondsPart());

        System.out.println(output + "\n");
    }

    public String readNotEmptyString(String prompt) {
        while (true) {
            String input = readString(prompt);
            if (!input.isBlank()) return input;
            printError("Поле не может быть пустым!");
        }
    }
}
