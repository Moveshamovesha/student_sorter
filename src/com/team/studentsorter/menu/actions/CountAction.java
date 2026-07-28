package com.team.studentsorter.menu.actions;

import com.team.studentsorter.menu.AppContext;
import com.team.studentsorter.menu.MenuAction;
import com.team.studentsorter.model.Student;
import com.team.studentsorter.threads.OccurrenceCounter;

public class CountAction implements MenuAction {

    @Override
    public String title() {
        return "Посчитать вхождения студента (доп. 4)";
    }

    @Override
    public void execute(AppContext context) {
        if (!context.hasData()) return;
        System.out.println("Кого ищем? Введите три поля студента.");
        int group = context.getInput().readPositiveInt("Группа: ");
        double grade = context.getInput().readDouble("Средний балл (2.0–5.0): ");
        int book = context.getInput().readPositiveInt("Зачётка: ");

        Student target;
        try {
            target = new Student.Builder()
                    .groupNumber(group).averageGrade(grade).recordBookNumber(book)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Невалидные данные: " + e.getMessage());
            return;
        }

        try {
            int count = OccurrenceCounter.count(context.getStudents(), target, 2);
            System.out.println("Вхождений студента " + target + ": " + count);
        } catch (Exception e) {
            System.out.println("Ошибка при подсчёте: " + e.getMessage());
        }
    }
}