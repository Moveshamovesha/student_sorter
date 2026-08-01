import com.team.studentsorter.model.Student;
import com.team.studentsorter.sort.EvenFieldSort;
import com.team.studentsorter.sort.QuickSortStrategy;
import com.team.studentsorter.sort.SelectionSortStrategy;
import com.team.studentsorter.sort.SortStrategy;
import java.util.*;
import java.util.stream.IntStream;

public class EvenFieldSortTest {

    public static void run() {

        testWithQuickSort();
        testWithSelectionSort();
        testEmptyList();

        System.out.println("Все тесты завершены");
    }

    private static void testWithQuickSort() {
        QuickSortStrategy<Student> quickSortStrategy= new QuickSortStrategy<>();

        List<Student> students = createStudents();

        EvenFieldSort.sortEvenByRecordBook(
                students,
                quickSortStrategy
        );

        boolean result = checkSortedEvenStudents(students, quickSortStrategy);

        check(result, "EvenFieldSort + QuickSortStrategy");
    }


    private static void testWithSelectionSort() {

        SelectionSortStrategy<Student> selectionSortStrategy= new SelectionSortStrategy<>();

        List<Student> students = createStudents();

        EvenFieldSort.sortEvenByRecordBook(
                students, selectionSortStrategy

        );

        boolean result = checkSortedEvenStudents(students, selectionSortStrategy);

        check(result, "EvenFieldSort + SelectionSortStrategy");
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

                createStudent(1, 4.5, 556),
                createStudent(2, 4.7, 310),
                createStudent(3, 3.9, 245),
                createStudent(4, 5.0, 700),
                createStudent(5, 4.7, 425),
                createStudent(1, 4.5, 569),
                createStudent(2, 4.2, 800),
                createStudent(3, 3.9, 211),
                createStudent(4, 5.0, 787),
                createStudent(5, 4.7, 118)
        ));
    }


    private static boolean checkSortedEvenStudents(List<Student> students, SortStrategy<Student> strategy) {
        List<Student> copyListStudents = new ArrayList<>(students);
        List<Integer> listIndex = IntStream.range(0, students.size())
                .filter(i -> students.get(i).getRecordBookNumber() % 2 == 0)
                .boxed()
                .toList();
        List<Student> sortedList = listIndex.stream()
                .map(students::get)
                .sorted(Comparator.comparing(Student::getRecordBookNumber))
                .toList();
        IntStream.range(0, sortedList.size())
                .forEach(i -> students.set(listIndex.get(i), sortedList.get(i)));

        return IntStream.range(0, students.size())
                .allMatch(i -> students.get(i).equals(copyListStudents.get(i)));
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

        if (condition) {
            System.out.println(testName + " - пройден");
        } else {
            System.out.println(testName + " - НЕ пройден");
        }
    }
}