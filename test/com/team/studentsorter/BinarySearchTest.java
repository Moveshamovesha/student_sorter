package com.team.studentsorter;
import com.team.studentsorter.model.Student;
import com.team.studentsorter.search.BinarySearch;
import com.team.studentsorter.sort.StudentComparators;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTest {

    public static void run() {
        testSearchExistingElement();
        testSearchFirstElement();
        testSearchLastElement();
        testSearchMissingElement();
        testSearchSingleElement();
        testSearchEmptyList();

        System.out.println("\nВсе тесты завершены.");
    }

    private static void testSearchExistingElement() {
        List<Student> list = createSortListStudent();
        int result= BinarySearch.indexOf(list, list.get(list.size()/2), StudentComparators.BY_RECORD_BOOK);
        Student student = list.get(result);
        Student student1 = createStudent(1, 4.5, 100060);

        check(student.equals(student1), "Поиск элемента в середине");
    }

    private static void testSearchFirstElement() {
        List<Student> list = createSortListStudent();
        int result= BinarySearch.indexOf(list, list.get(0), StudentComparators.BY_RECORD_BOOK);
        Student student = list.get(result);
        Student student1 = createStudent(1, 4.5, 100010);
        check(student.equals(student1), "Поиск первого элемента");
    }

    private static void testSearchLastElement() {
        List<Student> list = createSortListStudent();
        int result= BinarySearch.indexOf(list, list.get(list.size() - 1), StudentComparators.BY_RECORD_BOOK);
        Student student = list.get(result);
        Student student1 = createStudent(1, 4.9, 100110);
        check(student.equals(student1), "Поиск последнего элемента");
    }

    private static void testSearchMissingElement() {
        List<Student> list = createSortListStudent();
        Student missing = createStudent(9, 4.0, 999999);
        int result = BinarySearch.indexOf(list, missing, StudentComparators.BY_RECORD_BOOK);
        check(result == -1, "Поиск отсутствующего элемента");
    }

    private static void testSearchSingleElement() {
        Student student = createStudent(1, 5.0, 100010);
        List<Student> list = List.of(student);
        int result = BinarySearch.indexOf(list, student, StudentComparators.BY_RECORD_BOOK);
        check(result ==  0, "Поиск в списке из одного элемента");
    }

    private static void testSearchEmptyList() {
        Student student = createStudent(1, 5.0, 100010);
        List<Student> list = new ArrayList<>();
        int result = BinarySearch.indexOf(list, student, StudentComparators.BY_RECORD_BOOK);
        check(result == -1, "Поиск в пустом списке");
    }

    private static void check(boolean condition, String testName) {
        SimpleAssert.assertTrue(condition, testName);
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
    private static List<Student> createSortListStudent() {
        return new ArrayList<>(List.of(
                createStudent(1, 4.5, 100010),
                createStudent(2, 4.7, 100020),
                createStudent(3, 3.9, 100030),
                createStudent(4, 5.0, 100040),
                createStudent(5, 4.7, 100050),
                createStudent(1, 4.5, 100060),
                createStudent(2, 4.2, 100070),
                createStudent(3, 3.9, 100080),
                createStudent(4, 5.0, 100090),
                createStudent(5, 4.7, 100100),
                createStudent(1, 4.9, 100110)
        ));
    }
}