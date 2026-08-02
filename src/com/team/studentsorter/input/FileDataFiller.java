package com.team.studentsorter.input;

import com.team.studentsorter.model.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDataFiller implements DataFiller {
    private static final int GROUP_NUMBER = 0;
    private static final int AVERAGE_GRADE = 1;
    private static final int RECORD_BOOK_NUMBER = 2;
    private static final int TOTAL = 3;

    private final Path filePath;

    public FileDataFiller(Path filePath) throws IllegalArgumentException {
        if (Files.notExists(filePath)) {
            throw new IllegalArgumentException("Файл с данными студентов не найден.");
        }

        this.filePath = filePath;
    }

    @Override
    public List<Student> fill(int size) {
        AtomicInteger rejectedLines = new AtomicInteger(0);
        List<Student> result;

        try (Stream<String> lines = Files.lines(filePath)) {
            result = lines
                .map(this::parseLine)
                .peek(s -> { if(s == null) rejectedLines.incrementAndGet(); })
                .filter(Objects::nonNull)
                .limit(size)
                .collect(Collectors.toList());
            
            System.out.println("Файл прочитан. Отброшено записей: " + rejectedLines.get());
            if (result.size() < size) {
                System.out.println("Внимание: в файле нашлось только " + result.size()
                        + " валидных записей из " + size + " запрошенных.");
            }
            return result;
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }

        return List.of();
    }

    private Student parseLine(String line) {
        String[] raws = line.split(";");

        if (raws.length != TOTAL) return null;

        try {
            return new Student.Builder()
                .groupNumber(Integer.parseInt(raws[GROUP_NUMBER]))
                .averageGrade(Double.parseDouble(raws[AVERAGE_GRADE]))
                .recordBookNumber(Integer.parseInt(raws[RECORD_BOOK_NUMBER]))
                .build();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
