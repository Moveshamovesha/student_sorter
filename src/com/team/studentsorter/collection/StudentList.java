package com.team.studentsorter.collection;

import com.team.studentsorter.model.Student;

import java.util.AbstractList;
import java.util.Arrays;

public class StudentList extends AbstractList<Student> {
    private Student[] data = new Student[10];
    private int size = 0;

    @Override
    public Student get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        return data[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Student set(int index, Student element) {
        Student old = get(index);
        data[index] = element;
        return old;
    }

    @Override
    public void add(int index, Student element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        if (size == data.length)
            data = Arrays.copyOf(data, data.length * 2);
        for (int i = size; i > index; i--)
            data[i] = data[i - 1];
        data[index] = element;
        size++;
        modCount++;
    }
}