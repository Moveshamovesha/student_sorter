package com.team.studentsorter.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class ResultWriter {
    private final Path path;

    public ResultWriter(Path path) {
        this.path = path;
    }

    /** Дописывает строки в КОНЕЦ файла (режим добавления). */
    public void append(String header, List<?> items) {
        // TODO (Аркадий):
        // Files.write(path, lines, StandardCharsets.UTF_8,
        //             StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        // Перед списком писать header + текущую дату — так в файле видно
        // историю всех запусков.
    }
}