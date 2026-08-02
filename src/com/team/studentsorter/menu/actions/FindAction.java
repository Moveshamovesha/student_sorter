package com.team.studentsorter.menu.actions;

import com.team.studentsorter.menu.AppContext;
import com.team.studentsorter.menu.MenuAction;
import com.team.studentsorter.model.Student;
import com.team.studentsorter.search.BinarySearch;
import com.team.studentsorter.sort.QuickSortStrategy;
import com.team.studentsorter.sort.StudentComparators;

import java.util.ArrayList;
import java.util.List;

public class FindAction implements MenuAction {

    @Override
    public String title() {
        return "Найти студента по зачётке (бинарный поиск)";
    }

    @Override
    public void execute(AppContext context) {
        if (!context.hasData()) return;
        List<Student> students = new ArrayList<>(context.getStudents());

        new QuickSortStrategy<Student>().sort(students, StudentComparators.BY_RECORD_BOOK);

        int book = context.getInput().readPositiveInt("Номер зачётки для поиска: ");
        Student probe;
        try {
            probe = new Student.Builder()
                    .groupNumber(1).averageGrade(2.0).recordBookNumber(book)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Невалидный номер зачётки: " + e.getMessage());
            return;
        }

        int index = BinarySearch.indexOf(students, probe, StudentComparators.BY_RECORD_BOOK);
        if (index >= 0) {
            System.out.println("Найден: " + students.get(index));
        } else {
            System.out.println("Студент с зачёткой " + book + " не найден.");
        }
    }
}