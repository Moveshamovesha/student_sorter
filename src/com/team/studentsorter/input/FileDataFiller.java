package com.team.studentsorter.input;

import com.team.studentsorter.model.Student;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDataFiller implements DataFiller {
    final private int GROUP_NUMBER = 0;
    final private int AVERAGE_GRADE = 1;
    final private int RECORD_BOOK_NUMBER = 2;
    final private int TOTAL = 3;

    private final Path filePath;

    public FileDataFiller(Path filePath) {
        this.filePath = filePath;
    }

    // TODO: проверить
    @Override
    public List<Student> fill(int size) throws MissingResourceException {
        if (filePath == null) {
            throw new MissingResourceException("Путь до файла с данными студентов не указан.", "com.team.studentsorter.input.FileDataFiller", "filePath");
        }

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
            return result;
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }

        return List.of();
    }

    // TODO: проверить
    private Student parseLine(String line) {
        String[] raws = line.split(";");

        if (raws.length < TOTAL) return null;

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
