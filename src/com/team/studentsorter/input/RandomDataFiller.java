package com.team.studentsorter.input;

import com.team.studentsorter.model.Student;
import com.team.studentsorter.validation.StudentValidator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomDataFiller implements DataFiller {
    private final Random random = new Random();

    @Override
    public List<Student> fill(int size) {
        if (size < 0) throw new IllegalArgumentException("Отрицательный размер списка.");
        if (size == 0) return List.of();

        return Stream.generate(this::randomStudent)
                     .limit(size)
                     .collect(Collectors.toList());
    }

    private Student randomStudent() {
        int groupNumber = random.nextInt(StudentValidator.MIN_GROUP, StudentValidator.MAX_GROUP + 1);
        double averageGrade = (double)(random.nextInt((int)(StudentValidator.MIN_GRADE*10), (int)(StudentValidator.MAX_GRADE*10) + 1)) / 10;
        int recordBookNumber = random.nextInt(StudentValidator.MIN_RECORD_BOOK, StudentValidator.MAX_RECORD_BOOK + 1);

        return new Student.Builder()
            .groupNumber(groupNumber)
            .averageGrade(averageGrade)
            .recordBookNumber(recordBookNumber)
            .build();
    }
}
