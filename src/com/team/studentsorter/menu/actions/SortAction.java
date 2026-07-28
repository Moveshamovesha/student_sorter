package com.team.studentsorter.menu.actions;

import com.team.studentsorter.menu.AppContext;
import com.team.studentsorter.menu.MenuAction;
import com.team.studentsorter.model.Student;
import com.team.studentsorter.sort.SortStrategy;
import com.team.studentsorter.sort.StudentComparators;

import java.util.Comparator;

public class SortAction implements MenuAction {

    @Override
    public String title() {
        return "Отсортировать";
    }

    @Override
    public void execute(AppContext context) {
        if (!context.hasData()) return;

        System.out.println("Поле: 1 — группа, 2 — балл, 3 — зачётка, 4 — все три");
        Comparator<Student> comparator = switch (context.getInput().readInt("Ваш выбор: ")) {
            case 1 -> StudentComparators.BY_GROUP;
            case 2 -> StudentComparators.BY_GRADE;
            case 3 -> StudentComparators.BY_RECORD_BOOK;
            case 4 -> StudentComparators.BY_ALL_FIELDS;
            default -> null;
        };
        if (comparator == null) {
            System.out.println("Нет такого поля.");
            return;
        }

        SortStrategy<Student> strategy = StrategyPicker.choose(context.getInput());
        if (strategy == null) return;

        strategy.sort(context.getStudents(), comparator); // паттерн Стратегия
        System.out.println("Отсортировано. Пункт 7 — посмотреть результат.");
    }
}