package com.team.studentsorter;

import com.team.studentsorter.collection.StudentList;
import com.team.studentsorter.model.Student;

import java.util.Iterator;

public class StudentListTest {

    public static void run() {
        newListIsEmpty();
        addBeyondInitialCapacity();
        orderPreserved();
        getOutOfBounds();
        forEachIteratesAll();
        emptyIterator();
    }

    private static Student student(int group, double grade, int book) {
        return new Student.Builder()
                .groupNumber(group)
                .averageGrade(grade)
                .recordBookNumber(book)
                .build();
    }

    private static void newListIsEmpty() {
        SimpleAssert.assertEquals(0, new StudentList().size(), "StudentList: новый список пуст");
    }

    private static void addBeyondInitialCapacity() {
        // начальная ёмкость — 10, добавляем 15 → проверяем расширение
        StudentList list = new StudentList();
        for (int i = 0; i < 15; i++) {
            list.add(student(101, 4.0, 100000 + i));
        }
        SimpleAssert.assertEquals(15, list.size(), "StudentList: 15 добавлений, size = 15");
        boolean allReadable = true;
        for (int i = 0; i < 15; i++) {
            if (list.get(i).getRecordBookNumber() != 100000 + i) {
                allReadable = false;
                break;
            }
        }
        SimpleAssert.assertTrue(allReadable, "StudentList: все элементы читаются после расширения");
    }

    private static void orderPreserved() {
        StudentList list = new StudentList();
        list.add(student(103, 4.0, 100003));
        list.add(student(101, 4.0, 100001));
        list.add(student(102, 4.0, 100002));
        boolean order = list.get(0).getRecordBookNumber() == 100003
                && list.get(1).getRecordBookNumber() == 100001
                && list.get(2).getRecordBookNumber() == 100002;
        SimpleAssert.assertTrue(order, "StudentList: порядок добавления сохранён");
    }

    private static void getOutOfBounds() {
        StudentList list = new StudentList();
        list.add(student(101, 4.0, 100001));
        SimpleAssert.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(-1), "StudentList: get(-1) — исключение");
        SimpleAssert.assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(1), "StudentList: get(size) — исключение");
    }

    private static void forEachIteratesAll() {
        StudentList list = new StudentList();
        for (int i = 0; i < 7; i++) {
            list.add(student(101, 4.0, 100000 + i));
        }
        int count = 0;
        int expectedBook = 100000;
        boolean orderOk = true;
        for (Student s : list) {          // работает только при реализации Iterable
            if (s.getRecordBookNumber() != expectedBook) {
                orderOk = false;
            }
            expectedBook++;
            count++;
        }
        SimpleAssert.assertTrue(count == 7 && orderOk,
                "StudentList: for-each обошёл 7 элементов в порядке добавления");
    }

    private static void emptyIterator() {
        Iterator<Student> it = new StudentList().iterator();
        SimpleAssert.assertTrue(!it.hasNext(), "StudentList: итератор пустого списка — hasNext false");
    }
}