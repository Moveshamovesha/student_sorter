package com.team.studentsorter.menu.actions;

import com.team.studentsorter.menu.AppContext;
import com.team.studentsorter.menu.MenuAction;
import com.team.studentsorter.model.Student;

import java.util.List;

public class PrintAction implements MenuAction {

    @Override
    public String title() {
        return "Показать всех";
    }

    @Override
    public void execute(AppContext context) {
        if (!context.hasData()) return;
        List<Student> students = context.getStudents();
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }
}