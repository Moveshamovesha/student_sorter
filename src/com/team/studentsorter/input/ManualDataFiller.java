package com.team.studentsorter.input;

import com.team.studentsorter.model.Student;
import java.util.List;
import java.util.Scanner;

public class ManualDataFiller implements DataFiller {
    private final Scanner scanner;

    // Scanner передаём извне — второй сканер на System.in создавать нельзя
    public ManualDataFiller(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public List<Student> fill(int size) {
        // реализация Максима: цикл 0..size-1,
        // чтение полей через this.scanner, сборка через Builder,
        // при IllegalArgumentException — сообщение и повторный запрос
        return null; // заглушка
    }
}