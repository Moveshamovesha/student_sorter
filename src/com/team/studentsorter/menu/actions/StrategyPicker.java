package com.team.studentsorter.menu.actions;

import com.team.studentsorter.menu.ConsoleInput;
import com.team.studentsorter.model.Student;
import com.team.studentsorter.sort.QuickSortStrategy;
import com.team.studentsorter.sort.SelectionSortStrategy;
import com.team.studentsorter.sort.SortStrategy;

class StrategyPicker {
    static SortStrategy<Student> choose(ConsoleInput input) {
        System.out.println("Алгоритм: 1 — выбором, 2 — быстрая");
        return switch (input.readInt("Ваш выбор: ")) {
            case 1 -> new SelectionSortStrategy<>();
            case 2 -> new QuickSortStrategy<>();
            default -> {
                System.out.println("Нет такого алгоритма.");
                yield null;
            }
        };
    }
}