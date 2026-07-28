package com.team.studentsorter.menu.actions;

import com.team.studentsorter.menu.AppContext;
import com.team.studentsorter.menu.MenuAction;

public class ExitAction implements MenuAction {

    @Override
    public String title() {
        return "Выход";
    }

    @Override
    public void execute(AppContext context) {
        context.stop(); // выход из цикла только здесь — требование задания
    }
}