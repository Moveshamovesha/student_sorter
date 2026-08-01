package com.team.studentsorter.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ResultWriter {
    private final Path path;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss MM-dd-yyyy");

    public ResultWriter(Path path) {
        this.path = path;
    }

    /** Дописывает строки в КОНЕЦ файла (режим добавления). */
    public void append(String header, List<?> items) {
        List<String> lines = new ArrayList<>();
        String timestamp = LocalDateTime.now().format(FORMATTER);

        lines.add(header + " - " + timestamp);

        for (Object item : items)
            lines.add(String.valueOf(item));

        lines.add("");

        try {
            if (path.getParent() != null)
                Files.createDirectories(path.getParent());

            Files.write(
                path,
                lines,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл: " + path, e);
        }
    }
}