package test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import com.team.studentsorter.input.FileDataFiller;
import com.team.studentsorter.input.ManualDataFiller;
import com.team.studentsorter.input.RandomDataFiller;
import com.team.studentsorter.model.Student;

public class DataFillerTest {

    static public void run() {
        testRandomFiller();
        testManualFiller();
        testFromFileFiller();
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
        testFileTrows();
        testFile();
    }

    static private void testFile() {
        TestFileMock mock = new TestFileMock(
            "./temp.txt",
            
            "1;4.5;100234\n" +
            "102;3.8;100237\n" +
            "это не студент\n" +
            "105;4.9\n" +
            "210;7.5;100301\n" +
            "308;5.0;99\n" +
            "115;три;100278\n" +
            ";4.0;100300\n" +
            "999;4.4;100399"
        );

        try {
            mock.createFile();
        } catch (IOException e) {
            SimpleAssert.assertTrue(false, "FileDataFiller: Ошибка при создании тестового файла: " + e.getMessage());
            return;
        } 

        FileDataFiller target;
        try {
            target = new FileDataFiller(mock.getPath());
        } catch (IllegalArgumentException e) {
            SimpleAssert.assertTrue(false, "FileDataFiller: Ошибка при открытии тестового файла: " + e.getMessage());
            return;
        }

        ConsoleWrapper.muteOut();
        List<Student> students = target.fill(3);
        ConsoleWrapper.restore();

        SimpleAssert.assertEquals(
            3,
            students.size(),
            "FileDataFiller: в файле успешно найдены 3 правильных записей"
        );

        var record1 = students.get(0);
        var record2 = students.get(1);
        var record3 = students.get(2);
        SimpleAssert.assertTrue(
            record1.getGroupNumber() == 1 && record1.getAverageGrade() == 4.5 && record1.getRecordBookNumber() == 100234 &&
            record2.getGroupNumber() == 102 && record2.getAverageGrade() == 3.8 && record2.getRecordBookNumber() == 100237 &&
            record3.getGroupNumber() == 999 && record3.getAverageGrade() == 4.4 && record3.getRecordBookNumber() == 100399,
            "FileDataFiller: все значения в 3 записях правильно прочитаны"
        );

        try {
            mock.deleteFile();
        } catch (IOException e) {
            SimpleAssert.assertTrue(false, "FileDataFiller: Ошибка при удалении тестового файла: " + e.getMessage());
        }

    }

    static private void testFileTrows() {
        SimpleAssert.assertThrows(
            IllegalArgumentException.class,
            () -> new FileDataFiller(Path.of("./foo.bar")), 
            "FileDataFiller: исключение при ссылке на несуществующий файл.");
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

class TestFileMock {
    final private String tempFileName;
    final private Path tempFile;
    final private String testContentFile;

    public TestFileMock(String path, String content) {
        this.testContentFile = content;
        this.tempFileName = path;
        this.tempFile = Path.of(path);
    }

    public void createFile() throws IOException {
        if (Files.exists(tempFile)) {
            throw new FileAlreadyExistsException("Тестовый файл \"" + tempFileName + "\" существует. Удалите для продолжения теста.");
        }
        
        Files.createFile(tempFile);
        Files.writeString(tempFile, testContentFile);
    }

    public Path getPath() {
        return tempFile;
    }

    public void deleteFile() throws IOException {
        Files.delete(tempFile);
    }
}
