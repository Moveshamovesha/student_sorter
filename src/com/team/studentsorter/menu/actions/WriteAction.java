package com.team.studentsorter.menu.actions;

import com.team.studentsorter.io.ResultWriter;
import com.team.studentsorter.menu.AppContext;
import com.team.studentsorter.menu.MenuAction;

import java.nio.file.Path;

public class WriteAction implements MenuAction {

    @Override
    public String title() {
        return "Записать результат в файл (доп. 2)";
    }

    @Override
    public void execute(AppContext context) {
        if (!context.hasData()) return;
        ResultWriter writer = new ResultWriter(Path.of("output/results.txt"));
        writer.append("Запись из меню, студентов: " + context.getStudents().size(),
                context.getStudents());
        System.out.println("Результат дописан в output/results.txt");
    }
}