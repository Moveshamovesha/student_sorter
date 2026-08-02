package com.team.studentsorter.validation;

public class StudentValidator {
    public static final int MIN_GROUP = 1;
    public static final int MAX_GROUP = 999;
    public static final double MIN_GRADE = 2.0;
    public static final double MAX_GRADE = 5.0;
    public static final int MIN_RECORD_BOOK = 100_000;  // 6-значный
    public static final int MAX_RECORD_BOOK = 999_999;

    public static void validate(int groupNumber, double averageGrade, int recordBookNumber) throws IllegalArgumentException {
        StringBuilder message = new StringBuilder();

        if (!checkGroupNumber(groupNumber))
            message.append("Номер Группы: целое от 1 до 999;");

        if (!checkAverageGrade(averageGrade))
            message.append("Средняя оценка: число в диапазоне 2.0 - 5.0 включительно;");

        if (!checkRecordBookNumber(recordBookNumber))
            message.append("Номер Зачётной книжки: >0, шестизначное число;");

        if (message.isEmpty()) return;

        throw new IllegalArgumentException(message.toString());
    }

    public static boolean isValid(int groupNumber, double averageGrade, int recordBookNumber) {
        return checkGroupNumber(groupNumber) && checkAverageGrade(averageGrade) && checkRecordBookNumber(recordBookNumber);
    }

    private static boolean checkGroupNumber(int group) {
        return group >= MIN_GROUP && group <= MAX_GROUP;
    }

    private static boolean checkAverageGrade(double grade) {
        return grade >= MIN_GRADE && grade <= MAX_GRADE;
    }

    private static boolean checkRecordBookNumber(int recordBook) {
        return recordBook >= MIN_RECORD_BOOK && recordBook <= MAX_RECORD_BOOK;
    }
}
