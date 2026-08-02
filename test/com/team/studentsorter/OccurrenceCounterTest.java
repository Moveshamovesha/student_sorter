package com.team.studentsorter;

import com.team.studentsorter.model.Student;
import com.team.studentsorter.threads.OccurrenceCounter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class OccurrenceCounterTest {

    public static void run() {
        emptyList();
        noMatch();
        allMatch();
        knownCount();
        equalsBasedMatching();
        singleVsMultiThread();
        bigListMultiThread();
    }

    private static Student student(int group, double grade, int book) {
        return new Student.Builder()
                .groupNumber(group)
                .averageGrade(grade)
                .recordBookNumber(book)
                .build();
    }

    private static int countQuietly(List<Student> list, Student target, int threads) {
        try {
            return OccurrenceCounter.count(list, target, threads);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void emptyList() {
        int result = countQuietly(new ArrayList<>(), student(101, 4.0, 100001), 2);
        SimpleAssert.assertEquals(0, result, "Counter: пустой список — 0");
    }

    private static void noMatch() {
        List<Student> list = new ArrayList<>();
        list.add(student(101, 4.0, 100001));
        list.add(student(102, 4.1, 100002));
        int result = countQuietly(list, student(199, 3.0, 199999), 2);
        SimpleAssert.assertEquals(0, result, "Counter: совпадений нет — 0");
    }

    private static void allMatch() {
        Student target = student(101, 4.0, 100001);
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(student(101, 4.0, 100001));
        }
        SimpleAssert.assertEquals(5, countQuietly(list, target, 2),
                "Counter: все элементы совпадают — вернул размер");
    }

    private static void knownCount() {
        List<Student> list = new ArrayList<>();
        // 3 одинаковых среди 10
        for (int i = 0; i < 7; i++) {
            list.add(student(100 + i, 3.5, 100100 + i));
        }
        list.add(student(101, 4.0, 100001));
        list.add(student(101, 4.0, 100001));
        list.add(student(101, 4.0, 100001));
        SimpleAssert.assertEquals(3, countQuietly(list, student(101, 4.0, 100001), 2),
                "Counter: 3 вхождения среди 10 — вернул 3");
    }

    private static void equalsBasedMatching() {
        List<Student> list = new ArrayList<>();
        list.add(student(101, 4.0, 100001));
        // искомый объект — ДРУГОЙ экземпляр с теми же полями
        Student targetFromAnotherBuilder = student(101, 4.0, 100001);
        SimpleAssert.assertEquals(1, countQuietly(list, targetFromAnotherBuilder, 2),
                "Counter: совпадение по equals, а не по ссылке");
    }

    private static void singleVsMultiThread() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(student(101, 4.0, 100001)); // каждый третий — искомый
            list.add(student(102, 3.0, 100002));
            list.add(student(103, 3.5, 100003));
        }
        int single = countQuietly(list, student(101, 4.0, 100001), 1);
        int multi = countQuietly(list, student(101, 4.0, 100001), 4);
        SimpleAssert.assertEquals(single, multi,
                "Counter: 1 поток и 4 потока дают одинаковый результат (" + single + ")");
    }

    private static void bigListMultiThread() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            list.add(student(1 + i % 999, 2.0 + (i % 31) / 10.0, 100000 + i % 900000));
            if (i % 1000 == 0) {
                list.add(student(101, 4.0, 100001)); // ровно 100 вхождений
            }
        }
        long t1 = System.currentTimeMillis();
        int single = countQuietly(list, student(101, 4.0, 100001), 1);
        long singleTime = System.currentTimeMillis() - t1;

        long t2 = System.currentTimeMillis();
        int multi = countQuietly(list, student(101, 4.0, 100001), 4);
        long multiTime = System.currentTimeMillis() - t2;

        System.out.println("~100 000 элементов: 1 поток = " + singleTime
                + " мс, 4 потока = " + multiTime + " мс");
        SimpleAssert.assertTrue(single == 100 && multi == 100,
                "Counter: большой список, 4 потока — корректный результат 100");
    }
}