package com.team.studentsorter.input;

import com.team.studentsorter.model.Student;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileDataFiller implements DataFiller {
    private final Path filePath;

    public FileDataFiller(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Student> fill(int size) {
        // TODO (Максим):
        // try (Stream<String> lines = Files.lines(filePath)) {
        //     return lines
        //         .map(this::parseLine)        String -> Student или null
        //         .filter(Objects::nonNull)    битые строки отброшены
        //         .limit(size)
        //         .collect(Collectors.toList());
        // } catch (IOException e) { ... }
        return null;
    }

    private Student parseLine(String line) {
        // split(";") -> 3 части -> Integer.parseInt / Double.parseDouble
        // проверить через StudentValidator.isValid(...)
        // при ЛЮБОЙ ошибке вернуть null (строка будет отфильтрована)
        return null;
    }
}