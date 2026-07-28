package com.team.studentsorter.menu;

import com.team.studentsorter.model.Student;
import java.util.ArrayList;
import java.util.List;

/** Общее состояние приложения, передаётся в каждую команду. */
public class AppContext {
    private List<Student> students = new ArrayList<>();
    private boolean running = true;
    private final ConsoleInput input = new ConsoleInput();

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        this.running = false;
    }

    public ConsoleInput getInput() {
        return input;
    }

    /** Проверка «данные загружены» с сообщением пользователю. */
    public boolean hasData() {
        if (students.isEmpty()) {
            System.out.println("Данные не загружены. Сначала заполните массив (пункт 1).");
            return false;
        }
        return true;
    }
}