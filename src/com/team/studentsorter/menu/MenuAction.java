package com.team.studentsorter.menu;

public interface MenuAction {
    String title();
    void execute(AppContext context);
}