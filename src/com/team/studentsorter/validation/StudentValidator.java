package com.team.studentsorter.validation;

public class StudentValidator {
    public static final int MIN_GROUP = 1;
    public static final int MAX_GROUP = 999;
    public static final double MIN_GRADE = 2.0;
    public static final double MAX_GRADE = 5.0;
    public static final int MIN_RECORD_BOOK = 100_000;  // 6-значный
    public static final int MAX_RECORD_BOOK = 999_999;

    // TODO: проверить
    public static void validate(int groupNumber, double averageGrade, int recordBookNumber) throws IllegalArgumentException {
        StringBuilder message = new StringBuilder();

        if (checkGroupNumber(groupNumber))
            message.append("Номер Группы должен быть трёхзначным и начинаться с единицы.\n");

        if (checkAverageGrade(averageGrade))
            message.append("Средняя оценка должна быть от 2 до 5 включительно. Допускается нецелое число.\n");

        if (checkRecordBookNumber(recordBookNumber))
            message.append("Номер Зачётной книжки должен быть положительным шестизначным числом.\n");

        if (message.isEmpty()) return;

        throw new IllegalArgumentException(message.toString());
    }

    // TODO: проверить
    public static boolean isValid(int groupNumber, double averageGrade, int recordBookNumber) {
        return checkGroupNumber(groupNumber) && checkAverageGrade(averageGrade) && checkRecordBookNumber(recordBookNumber);
    }

    // TODO: проверить
    private static boolean checkGroupNumber(int group) {
        return group >= MIN_GROUP && group <= MAX_GROUP;
    }

    // TODO: проверить
    private static boolean checkAverageGrade(double grade) {
        return grade >= MIN_GRADE && grade <= MAX_GRADE;
    }

    // TODO: проверить
    private static boolean checkRecordBookNumber(int recordBook) {
        return recordBook >= MIN_RECORD_BOOK && recordBook <= MAX_RECORD_BOOK;
    }
}