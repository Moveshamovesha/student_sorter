package com.team.studentsorter.input;

import com.team.studentsorter.model.Student;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ManualDataFiller implements DataFiller {
    private final Scanner scanner;

    public ManualDataFiller(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public List<Student> fill(int size) throws IllegalArgumentException{
        if (size < 0) {
            throw new IllegalArgumentException("Отрицательный размер списка");
        }

        ConsoleHandler console = new ConsoleHandler(scanner);
        List<Student> students = new ArrayList<>();

        for (int i=0; i<size;) {
            try {
                int group = console.readInt("Группа: ");
                double average = console.readDouble("Средняя оценка (нецелое число через точку): ");
                int book = console.readInt("Номер зачётной книжки: ");
            
                students.add(new Student.Builder()
                    .groupNumber(group)
                    .averageGrade(average)
                    .recordBookNumber(book)
                    .build());
                
                i++;
                console.println("Студент успешно добавлен в список.");

            } catch (IllegalArgumentException e) {
                console.println("Запись студента не создана:");
                console.println(e.getMessage());
                console.println("Введите данные студента ещё раз.");
            } catch (InputMismatchException e){
                console.println("Ошибка ввода. Повторите попытку.");
                scanner.next();
            } catch (NoSuchElementException e) {
                console.println("Ошибка: стрим ввода данных неожиданно прервался. Остановка ввода новых записей.");
                return students;
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
        scanner.useLocale(Locale.US);
    }

    public void print(String text) {
        System.out.print(text);
    }

    public void println(String text) {
        System.out.println(text);
    }

    public int readInt(String askText) throws InputMismatchException {
        print(askText);
        return scanner.nextInt();
    }

    public double readDouble(String askText) throws InputMismatchException {
        print(askText);
        return scanner.nextDouble();
    }

}
