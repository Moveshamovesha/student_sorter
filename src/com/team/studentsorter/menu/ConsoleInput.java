package com.team.studentsorter.menu;

import java.util.Locale;
import java.util.Scanner;

public class ConsoleInput {
    // Locale.US — чтобы разделителем double всегда была точка, независимо от локали ОС
    private final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public Scanner getScanner() {
        return scanner; // отдать ManualDataFiller, чтобы не плодить сканеры на System.in
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // съесть перевод строки
                return value;
            }
            System.out.println("Нужно целое число, попробуйте ещё раз.");
            scanner.nextLine(); // съесть мусорный ввод
        }
    }

    public int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) return value;
            System.out.println("Число должно быть положительным.");
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            }
            System.out.println("Нужно число, например 4.5");
            scanner.nextLine();
        }
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}