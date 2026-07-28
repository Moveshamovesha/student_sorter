package com.team.studentsorter.menu.actions;

import com.team.studentsorter.input.DataFiller;
import com.team.studentsorter.input.FileDataFiller;
import com.team.studentsorter.input.ManualDataFiller;
import com.team.studentsorter.input.RandomDataFiller;
import com.team.studentsorter.menu.AppContext;
import com.team.studentsorter.menu.MenuAction;

import java.nio.file.Path;

public class FillAction implements MenuAction {

    @Override
    public String title() {
        return "Заполнить данные (файл / рандом / вручную)";
    }

    @Override
    public void execute(AppContext context) {
        System.out.println("Источник данных: 1 — файл, 2 — рандом, 3 — вручную");
        int source = context.getInput().readInt("Ваш выбор: ");
        int length = context.getInput().readPositiveInt("Длина массива: ");

        DataFiller filler = switch (source) {
            case 1 -> {
                String path = context.getInput().readLine("Путь к файлу [data/students.txt]: ");
                yield new FileDataFiller(Path.of(path.isEmpty() ? "data/students.txt" : path));
            }
            case 2 -> new RandomDataFiller();
            case 3 -> new ManualDataFiller(context.getInput().getScanner());
            default -> {
                System.out.println("Нет такого источника.");
                yield null;
            }
        };
        if (filler == null) {
            return;
        }

        context.setStudents(filler.fill(length)); // паттерн Стратегия
        System.out.println("Загружено студентов: " + context.getStudents().size());
    }
}