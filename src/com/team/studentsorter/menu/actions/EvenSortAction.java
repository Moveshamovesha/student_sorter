package com.team.studentsorter.menu.actions;

import com.team.studentsorter.menu.AppContext;
import com.team.studentsorter.menu.MenuAction;
import com.team.studentsorter.model.Student;
import com.team.studentsorter.sort.EvenFieldSort;
import com.team.studentsorter.sort.SortStrategy;

public class EvenSortAction implements MenuAction {

    @Override
    public String title() {
        return "Сортировка чётных зачёток (доп. 1)";
    }

    @Override
    public void execute(AppContext context) {
        if (!context.hasData()) return;
        SortStrategy<Student> strategy = StrategyPicker.choose(context.getInput());
        if (strategy == null) return;
        EvenFieldSort.sortEvenByRecordBook(context.getStudents(), strategy);
        System.out.println("Чётные зачётки отсортированы, нечётные остались на местах.");
    }
}