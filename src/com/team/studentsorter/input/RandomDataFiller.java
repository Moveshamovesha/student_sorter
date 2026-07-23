package com.team.studentsorter.input;

import com.team.studentsorter.model.Student;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomDataFiller implements DataFiller {
    private final Random random = new Random();

    @Override
    public List<Student> fill(int size) {
        // TODO (Максим): заполнение ОБЯЗАТЕЛЬНО через стрим:
        // return Stream.generate(this::randomStudent)
        //              .limit(size)
        //              .collect(Collectors.toList());
        return null;
    }

    private Student randomStudent() {
        // генерация в ДОПУСТИМЫХ диапазонах из StudentValidator,
        // балл округлить до 1 знака: Math.round(x * 10) / 10.0
        return null;
    }
}