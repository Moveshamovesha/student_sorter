package com.team.studentsorter;

import com.team.studentsorter.model.Student;

public class StudentTest {

    public static void run() {
        builderSetsAllFields();
        builderChainAnyOrder();
        buildRejectsInvalidGroup();
        buildRejectsInvalidGrade();
        buildGradeBoundaries();
        buildRejectsInvalidRecordBook();
        buildRecordBookBoundaries();
        equalsAndHashCode();
        toStringContainsFields();
    }

    private static Student student(int group, double grade, int book) {
        return new Student.Builder()
                .groupNumber(group)
                .averageGrade(grade)
                .recordBookNumber(book)
                .build();
    }

    private static void builderSetsAllFields() {
        Student s = student(101, 4.5, 100234);
        boolean ok = s.getGroupNumber() == 101
                && s.getAverageGrade() == 4.5
                && s.getRecordBookNumber() == 100234;
        SimpleAssert.assertTrue(ok, "Builder: все поля установлены");
    }

    private static void builderChainAnyOrder() {
        Student s = new Student.Builder()
                .recordBookNumber(100234)
                .groupNumber(101)
                .averageGrade(4.5)
                .build();
        SimpleAssert.assertTrue(s.getGroupNumber() == 101
                        && s.getAverageGrade() == 4.5
                        && s.getRecordBookNumber() == 100234,
                "Builder: порядок вызовов в цепочке не важен");
    }

    private static void buildRejectsInvalidGroup() {
        SimpleAssert.assertThrows(IllegalArgumentException.class,
                () -> student(0, 4.0, 100234), "build: группа 0 отклонена");
        SimpleAssert.assertThrows(IllegalArgumentException.class,
                () -> student(-5, 4.0, 100234), "build: группа -5 отклонена");
        SimpleAssert.assertThrows(IllegalArgumentException.class,
                () -> student(1000, 4.0, 100234), "build: группа 1000 отклонена");
    }

    private static void buildRejectsInvalidGrade() {
        SimpleAssert.assertThrows(IllegalArgumentException.class,
                () -> student(101, 1.9, 100234), "build: балл 1.9 отклонён");
        SimpleAssert.assertThrows(IllegalArgumentException.class,
                () -> student(101, 5.1, 100234), "build: балл 5.1 отклонён");
        SimpleAssert.assertThrows(IllegalArgumentException.class,
                () -> student(101, Double.NaN, 100234), "build: балл NaN отклонён");
    }

    private static void buildGradeBoundaries() {
        try {
            student(101, 2.0, 100234);
            student(101, 5.0, 100234);
            SimpleAssert.assertTrue(true, "build: границы балла 2.0 и 5.0 допустимы");
        } catch (IllegalArgumentException e) {
            SimpleAssert.assertTrue(false, "build: границы балла 2.0 и 5.0 допустимы");
        }
    }

    private static void buildRejectsInvalidRecordBook() {
        SimpleAssert.assertThrows(IllegalArgumentException.class,
                () -> student(101, 4.0, 99999), "build: зачётка 99999 отклонена");
        SimpleAssert.assertThrows(IllegalArgumentException.class,
                () -> student(101, 4.0, 1000000), "build: зачётка 1000000 отклонена");
    }

    private static void buildRecordBookBoundaries() {
        try {
            student(101, 4.0, 100000);
            student(101, 4.0, 999999);
            SimpleAssert.assertTrue(true, "build: границы зачётки 100000 и 999999 допустимы");
        } catch (IllegalArgumentException e) {
            SimpleAssert.assertTrue(false, "build: границы зачётки допустимы");
        }
    }

    private static void equalsAndHashCode() {
        Student a = student(101, 4.5, 100234);
        Student b = student(101, 4.5, 100234);
        Student c = student(102, 4.5, 100234);
        SimpleAssert.assertTrue(a.equals(b) && b.equals(a), "equals: одинаковые поля — равны");
        SimpleAssert.assertTrue(!a.equals(c), "equals: разная группа — не равны");
        SimpleAssert.assertTrue(!a.equals(null) && !a.equals("строка"),
                "equals: null и другой тип — не равны");
        SimpleAssert.assertEquals(a.hashCode(), b.hashCode(), "hashCode равных объектов совпадает");
    }

    private static void toStringContainsFields() {
        String s = student(101, 4.5, 100234).toString();
        SimpleAssert.assertTrue(s.contains("101") && s.contains("4.5") && s.contains("100234"),
                "toString содержит все три поля");
    }
}