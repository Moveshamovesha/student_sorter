package com.team.studentsorter;

import com.team.studentsorter.model.Student;
import com.team.studentsorter.sort.QuickSortStrategy;
import com.team.studentsorter.sort.SelectionSortStrategy;
import com.team.studentsorter.sort.SortStrategy;
import com.team.studentsorter.sort.StudentComparators;

import java.util.ArrayList;
import java.util.List;

public class SortTest {

    public static void run() {
        SortStrategy<Student> quickSortStrategy = new QuickSortStrategy<>();
        List<Student> students1 = createStudents();
        boolean result1 = checkSortedStudents(students1, quickSortStrategy);
        check(result1, "QuickSortStrategy");

        SortStrategy<Student> selectionSortStrategy = new SelectionSortStrategy<>();
        List<Student> students2 = createStudents();
        boolean result2 = checkSortedStudents(students2, selectionSortStrategy);
        check(result2, "SelectionSortStrategy");
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

    private static Student createStudent(int group, double grade, int recordBook) {
        return new Student.Builder()
                .groupNumber(group)
                .averageGrade(grade)
                .recordBookNumber(recordBook)
                .build();
    }

    private static void check(boolean condition, String testName) {
        SimpleAssert.assertTrue(condition, testName);
    }

    private static boolean checkSortedStudents(List<Student> students, SortStrategy<Student> strategy) {
        List<Student> sorted = new ArrayList<>(students);
        strategy.sort(sorted, StudentComparators.BY_RECORD_BOOK);
        for (int i = 0; i < sorted.size() - 1; i++) {
            if (sorted.get(i).getRecordBookNumber() > sorted.get(i + 1).getRecordBookNumber()) {
                return false;
            }
        }
        return true;
    }
}