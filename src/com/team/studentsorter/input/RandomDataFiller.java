package com.team.studentsorter.input;

import com.team.studentsorter.model.Student;
import com.team.studentsorter.validation.StudentValidator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomDataFiller implements DataFiller {
    private final Random random = new Random();

    // TODO: проверить
    @Override
    public List<Student> fill(int size) {
        return Stream.generate(this::randomStudent)
                     .limit(size)
                     .collect(Collectors.toList());
    }

    // TODO: проверить
    private Student randomStudent() {
        return new Student.Builder()
            .groupNumber(
                random.nextInt(StudentValidator.MIN_GROUP, StudentValidator.MAX_GROUP + 1)
            ).averageGrade(
                random.nextInt((int)(StudentValidator.MIN_GRADE*10), (int)(StudentValidator.MIN_GRADE*10) + 1) * 0.1d
            ).recordBookNumber(
                random.nextInt(StudentValidator.MIN_RECORD_BOOK, StudentValidator.MAX_RECORD_BOOK + 1)
            ).build();
    }
}
