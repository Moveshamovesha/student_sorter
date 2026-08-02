package com.team.studentsorter;
import com.team.studentsorter.model.Student;
import com.team.studentsorter.sort.EvenFieldSort;
import com.team.studentsorter.sort.QuickSortStrategy;
import com.team.studentsorter.sort.SelectionSortStrategy;
import com.team.studentsorter.sort.SortStrategy;
import java.util.*;


public class EvenFieldSortTest {

    public static void run() {

        testWithQuickSort();
        testWithSelectionSort();
        testEmptyList();

        System.out.println("Все тесты завершены");
    }

    private static void testWithQuickSort() {
        QuickSortStrategy<Student> strategy = new QuickSortStrategy<>();
        List<Student> students = createStudents();
        List<Student> before = new ArrayList<>(students);   // снимок ДО сортировки
        EvenFieldSort.sortEvenByRecordBook(students, strategy);
        check(checkSortedEvenStudents(before, students), "EvenFieldSort + QuickSortStrategy");
    }


    private static void testWithSelectionSort() {
        SelectionSortStrategy<Student> strategy = new SelectionSortStrategy<>();
        List<Student> students = createStudents();
        List<Student> before = new ArrayList<>(students);
        EvenFieldSort.sortEvenByRecordBook(students, strategy);
        check(checkSortedEvenStudents(before, students), "EvenFieldSort + SelectionSortStrategy");
    }


    private static void testEmptyList() {

        List<Student> students = new ArrayList<>();

        EvenFieldSort.sortEvenByRecordBook(
                students,
                new QuickSortStrategy<>()
        );


        check(students.isEmpty(), "Пустой список");
    }


    private static List<Student> createStudents() {
        return new ArrayList<>(List.of(
                createStudent(1, 4.5, 100556),
                createStudent(2, 4.7, 100310),
                createStudent(3, 3.9, 100245),
                createStudent(4, 5.0, 100700),
                createStudent(5, 4.7, 100425),
                createStudent(1, 4.5, 100569),
                createStudent(2, 4.2, 100800),
                createStudent(3, 3.9, 100211),
                createStudent(4, 5.0, 100787),
                createStudent(5, 4.7, 100118)
        ));
    }

    private static boolean checkSortedEvenStudents(List<Student> before, List<Student> after) {
        for (int i = 0; i < after.size(); i++) {
            if (after.get(i).getRecordBookNumber() % 2 != 0
                    && !after.get(i).equals(before.get(i))) {
                return false; // нечётный съехал с места
            }
        }
        int prev = Integer.MIN_VALUE;
        for (Student s : after) {
            int book = s.getRecordBookNumber();
            if (book % 2 == 0) {
                if (book < prev) return false; // чётные не упорядочены
                prev = book;
            }
        }
        return true;
    }


    private static Student createStudent(
            int group,
            double grade,
            int recordBook
    ) {

        return new Student.Builder()
                .groupNumber(group)
                .averageGrade(grade)
                .recordBookNumber(recordBook)
                .build();
    }


    private static void check(boolean condition, String testName) {
        SimpleAssert.assertTrue(condition, testName);
    }
}