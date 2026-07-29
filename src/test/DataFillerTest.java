package test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import com.team.studentsorter.input.ManualDataFiller;
import com.team.studentsorter.input.RandomDataFiller;
import com.team.studentsorter.model.Student;

public class DataFillerTest {

    static public void run() {
        testRandomFiller();
        testManualFiller();
        // testFromFileFiller();
    }

    static private void testRandomFiller() {
        RandomDataFiller target = new RandomDataFiller();

        SimpleAssert.assertEquals(
            5,
            target.fill(5).size(),
            "RandomDataFiller: создание списка из 5 элементов"
        );

        SimpleAssert.assertEquals(
            0,
            target.fill(0).size(),
            "RandomDataFiller: создание списка из 0 элементов"
        );

        SimpleAssert.assertThrows(
            IllegalArgumentException.class,
            () -> target.fill(-1),
            "RandomDataFiller: исключение при создании списка из -1 элементов"
        );

        System.out.println(target.fill(50));
    }

    static private void testManualFiller() {
        testDifferentListSizes();
        testWithMistakes();
        testWithErrorValidations();
    } 

    static private void testWithMistakes() {
        var students = simulateKeyboardEnter("123\n4,5\n 123\n4.5\nfoo\n 765\n3.5\n999999\n bar\n 2\n4\n455589\n", 2);
        SimpleAssert.assertEquals(
            2,
            students.size(),
            "ManualDataFiller: создание списка из 2 элементов при ошибках неправильных ввода цифр"
        );

        var record1 = students.get(0);
        var record2 = students.get(1);
        SimpleAssert.assertTrue(
            record1.getGroupNumber() == 765 && record1.getAverageGrade() == 3.5 && record1.getRecordBookNumber() == 999999 &&
            record2.getGroupNumber() == 2 && record2.getAverageGrade() == 4 && record2.getRecordBookNumber() == 455589,
            "ManualDataFiller: корректные значения в записях при ошибках неправильного ввода цифр"
        );
    }

    static private void testWithErrorValidations() {
        var students = simulateKeyboardEnter("-1\n-1\n-1 0\n0\n0\n 765\n3.5\n999999\n 9999999\n5.0\n100000 2\n4\n455589\n", 2);
        SimpleAssert.assertEquals(
            2,
            students.size(),
            "ManualDataFiller: создание списка из 2 элементов с учётом провала валидации"
        );

        var record1 = students.get(0);
        var record2 = students.get(1);
        SimpleAssert.assertTrue(
            record1.getGroupNumber() == 765 && record1.getAverageGrade() == 3.5 && record1.getRecordBookNumber() == 999999 &&
            record2.getGroupNumber() == 2 && record2.getAverageGrade() == 4 && record2.getRecordBookNumber() == 455589,
            "ManualDataFiller: корректные значения в записях с учётом провала валидации"
        );    
    }
    
    static private void testDifferentListSizes() {
        SimpleAssert.assertEquals(
            3,
            simulateKeyboardEnter("123\n4.5\n456789\n 765\n3.5\n999999\n 2\n4\n455589\n", 3).size(),
            "ManualDataFiller: создание списка из 3 элементов"
        );

        SimpleAssert.assertEquals(
            0,
            simulateKeyboardEnter("", 0).size(),
            "ManualDataFiller: создание списка из 0 элементов"
        );

        SimpleAssert.assertThrows(
            IllegalArgumentException.class,
            () -> simulateKeyboardEnter("", -1),
            "ManualDataFiller: исключение при создании списка из -1 элементов"
        );
    }

    static private List<Student> simulateKeyboardEnter(String input, int size) {
        ConsoleWrapper.muteOut();
        ConsoleWrapper.setInput(input);

        Scanner scanner = new Scanner(System.in);
        ManualDataFiller dataFiller = new ManualDataFiller(scanner);

        List<Student> result = dataFiller.fill(size);

        ConsoleWrapper.restore();

        return result;
    }

    static private void testFromFileFiller() {
        // FileDataFiller target = new FileDataFiller(null);
    }

}

class ConsoleWrapper {
    private static InputStream originInput = System.in;
    private static PrintStream originOut = System.out;

    public static void restore() {
        System.setOut(originOut);
        System.setIn(originInput);
    }

    public static void setInput(String text) {
        System.setIn(new ByteArrayInputStream(text.getBytes()));
    }

    public static void muteOut() {
        System.setOut(new PrintStream(new OutputStream() { public void write(int b) {} }));
    }
}
