package com.team.studentsorter.menu;

import com.team.studentsorter.menu.actions.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleMenu {

    private final AppContext context = new AppContext();
    // LinkedHashMap — пункты меню печатаются в порядке добавления
    private final Map<Integer, MenuAction> actions = new LinkedHashMap<>();

    public ConsoleMenu() {
        actions.put(1, new FillAction());
        actions.put(2, new SortAction());
        actions.put(3, new EvenSortAction());
        actions.put(4, new FindAction());
        actions.put(5, new WriteAction());
        actions.put(6, new CountAction());
        actions.put(7, new PrintAction());
        actions.put(0, new ExitAction());
    }

    public void run() {
        System.out.println("=== Student Sorter ===");
        while (context.isRunning()) {
            printMenu();
            int choice = context.getInput().readInt("Ваш выбор: ");
            MenuAction action = actions.get(choice);
            if (action == null) {
                System.out.println("Нет такого пункта, попробуйте снова.");
            } else {
                action.execute(context);
            }
        }
        System.out.println("Программа завершена.");
    }

    private void printMenu() {
        System.out.println("\n--- Главное меню --- (загружено студентов: "
                + context.getStudents().size() + ")");
        actions.forEach((key, action) -> System.out.println(key + ". " + action.title()));
    }
}