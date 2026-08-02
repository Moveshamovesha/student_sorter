package com.team.studentsorter;

import com.team.studentsorter.io.ResultWriter;
import com.team.studentsorter.model.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class ResultWriterTest {

    public static void run() {
        createsFileIfMissing();
        appendDoesNotOverwrite();
        secondWriteGoesAfterFirst();
        headerPresent();
    }

    private static Student student(int group, double grade, int book) {
        return new Student.Builder()
                .groupNumber(group)
                .averageGrade(grade)
                .recordBookNumber(book)
                .build();
    }

    /** Временный путь, которого точно не существует. */
    private static Path freshTempPath() throws IOException {
        Path p = Files.createTempFile("results-test", ".txt");
        Files.delete(p); // удаляем, чтобы проверить создание с нуля
        p.toFile().deleteOnExit();
        return p;
    }

    private static void createsFileIfMissing() {
        try {
            Path p = freshTempPath();
            new ResultWriter(p).append("Тест", Arrays.asList(student(101, 4.0, 100001)));
            SimpleAssert.assertTrue(Files.exists(p) && Files.size(p) > 0,
                    "ResultWriter: файл создан, в него записаны данные");
        } catch (IOException e) {
            SimpleAssert.assertTrue(false, "ResultWriter: создание файла (IOException)");
        }
    }

    private static void appendDoesNotOverwrite() {
        try {
            Path p = freshTempPath();
            ResultWriter writer = new ResultWriter(p);
            writer.append("Первая запись", Arrays.asList(student(101, 4.0, 100001)));
            long sizeAfterFirst = Files.size(p);
            writer.append("Вторая запись", Arrays.asList(student(102, 4.1, 100002)));
            long sizeAfterSecond = Files.size(p);
            SimpleAssert.assertTrue(sizeAfterSecond > sizeAfterFirst,
                    "ResultWriter: append — файл вырос, старое содержимое не затёрто");
        } catch (IOException e) {
            SimpleAssert.assertTrue(false, "ResultWriter: append (IOException)");
        }
    }

    private static void secondWriteGoesAfterFirst() {
        try {
            Path p = freshTempPath();
            ResultWriter writer = new ResultWriter(p);
            writer.append("ААА первая", Arrays.asList(student(101, 4.0, 100001)));
            writer.append("БББ вторая", Arrays.asList(student(102, 4.1, 100002)));
            String content = new String(Files.readAllBytes(p));
            int firstPos = content.indexOf("ААА первая");
            int secondPos = content.indexOf("БББ вторая");
            SimpleAssert.assertTrue(firstPos >= 0 && secondPos > firstPos,
                    "ResultWriter: вторая запись идёт ПОСЛЕ первой");
        } catch (IOException e) {
            SimpleAssert.assertTrue(false, "ResultWriter: порядок записей (IOException)");
        }
    }

    private static void headerPresent() {
        try {
            Path p = freshTempPath();
            new ResultWriter(p).append("Сортировка по баллу",
                    Arrays.asList(student(101, 4.0, 100001)));
            String content = new String(Files.readAllBytes(p));
            boolean ok = content.contains("Сортировка по баллу") && content.contains("100001");
            SimpleAssert.assertTrue(ok, "ResultWriter: заголовок и данные присутствуют в файле");
        } catch (IOException e) {
            SimpleAssert.assertTrue(false, "ResultWriter: заголовок (IOException)");
        }
    }
}