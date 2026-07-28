package com.team.studentsorter.input;

import com.team.studentsorter.model.Student;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ManualDataFiller implements DataFiller {
    private final Scanner scanner;

    public ManualDataFiller(Scanner scanner) {
        this.scanner = scanner;
    }

    // TODO: проверить
    @Override
    public List<Student> fill(int size) {
        ConsoleHandler console = new ConsoleHandler(scanner);
        List<Student> students = new ArrayList<>();

        for (int i=0; i<size;) {
            int group = console.readInt("Введите группу студента: ");
            double average = console.readDouble("Введите среднюю оценку (допускается нецелое число через точку): ");
            int book = console.readInt("Введите номер зачётной книжки: ");

            try {
                students.add(new Student.Builder()
                    .groupNumber(group)
                    .averageGrade(average)
                    .recordBookNumber(book)
                    .build());
                i++;
                console.println("Студент успешно добавлен в список.");

            } catch (IllegalArgumentException e) {
                console.println(e.getMessage());
                console.println("Введите данные студента ещё раз.");
            }
        }

        console.println("Конец заполнения списка студентов.");
        return students;
    }
}

class ConsoleHandler {
    private final Scanner scanner;

    public ConsoleHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public void print(String text) {
        System.out.print(text);
    }

    public void println(String text) {
        System.out.println(text);
    }

    public int readInt(String askText) {
        print(askText);
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                printErrorMessage();
            }
        }
    }

    public double readDouble(String askText) {
        print(askText);
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                printErrorMessage();
            }
        }
    }

    private void printErrorMessage() {
       print("Ошибка ввода. Повторите попытку: ");
    }
}
