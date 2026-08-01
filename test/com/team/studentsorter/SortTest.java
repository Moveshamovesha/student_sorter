import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class SortTest {
    public static void run(){
        SortStrategy<Student> quickSortStrategy = new QuickSortStrategy<>();
        List<Student> students = createStudents();
        boolean result = checkSortedStudents(students, quickSortStrategy);
        check(result, "QuickSortStrategy");

        SortStrategy<Student> selectionSortStrategy = new SelectionSortStrategy<>();
        List<Student> students = createStudents();
        boolean result = checkSortedStudents(students, selectionSortStrategy);
        check(result, "SelectionSortStrategy");

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

    private static boolean checkSortedStudents(List<Student> students, SortStrategy<Student> strategy) {
        List<Student> copyListStudents = new ArrayList<>(students);
        List<Student> quickSortedList = new ArrayList<>(students);
        strategy.sort(quickSortedList, StudentComparators.BY_RECORD_BOOK);
        copyListStudents.sort(Comparator.comparingInt(Student::getRecordBookNumber));

        return copyListStudents.equals(quickSortedList);
    }
}