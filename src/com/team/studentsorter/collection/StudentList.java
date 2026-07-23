package com.team.studentsorter.collection;

import com.team.studentsorter.model.Student;
import java.util.Arrays;
import java.util.Iterator;

public class StudentList implements Iterable<Student> {
    private Student[] data = new Student[10];
    private int size = 0;

    public void add(Student s) {
        // TODO (Аркадий): если массив полон — Arrays.copyOf(data, data.length * 2)
    }

    public Student get(int index) {
        // TODO: проверка границ, вернуть data[index]
        return null;
    }

    public int size() { return size; }

    @Override
    public Iterator<Student> iterator() {
        // TODO: вернуть итератор по первым size элементам
        return null;
    }
}