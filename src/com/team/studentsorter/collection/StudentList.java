package com.team.studentsorter.collection;

import com.team.studentsorter.model.Student;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StudentList implements Iterable<Student> {
    private Student[] data = new Student[10];
    private int size = 0;

    public void add(Student s) {
        if (size == data.length)
            data = Arrays.copyOf(data, data.length * 2);

        data[size++] = s;
    }

    public Student get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);

        return data[index];
    }

    public int size() { return size; }

    @Override
    public Iterator<Student> iterator() {
        return new Iterator<>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public Student next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                return data[current++];
            }
        };
    }
}