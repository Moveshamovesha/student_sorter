package com.team.studentsorter.menu;

import com.team.studentsorter.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner = new Scanner(System.in);
    private List<Student> students = new ArrayList<>();
    private boolean running = true;

    public void run() {
        while (running) {                 // цикл — требование задания
            printMainMenu();
            int choice = readInt();
            switch (choice) {
                case 1 -> fillData();      // выбор: файл / рандом / вручную + длина
                case 2 -> sortData();      // выбор поля и стратегии
                case 3 -> sortEvenOnly();  // доп. 1
                case 4 -> findStudent();   // бинарный поиск
                case 5 -> writeToFile();   // доп. 2
                case 6 -> countOccurrences(); // доп. 4
                case 7 -> printAll();
                case 0 -> running = false; // ВЫХОД — только так
                default -> System.out.println("Нет такого пункта, попробуйте снова.");
            }
        }
        System.out.println("Программа завершена.");
    }

    private int readInt() {
        // TODO (Костя): безопасное чтение int из Scanner
        // (hasNextInt + «съедание» мусорной строки), чтобы меню не падало
        return 0;
    }

    // TODO (Костя): fillData() — подменю: 1-файл 2-рандом 3-вручную,
    //   спросить длину, выбрать нужный DataFiller:
    //   DataFiller filler = switch (choice) { case 1 -> new FileDataFiller(...), ... };
    //   students = filler.fill(length);   ← вот здесь паттерн «Стратегия» в действии
    //
    // sortData() — подменю: поле (группа/балл/зачётка/все три) +
    //   стратегия (выбором/быстрая), вызвать strategy.sort(students, comparator)
    // writeToFile() — ResultWriter.append(...)
    // countOccurrences() — спросить, какого студента искать (3 поля),
    //   собрать его Builder'ом, вызвать OccurrenceCounter.count(..., 2 потока)
}