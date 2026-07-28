package com.team.studentsorter.validation;

public class StudentValidator {
    public static final int MIN_GROUP = 1;
    public static final int MAX_GROUP = 999;
    public static final double MIN_GRADE = 2.0;
    public static final double MAX_GRADE = 5.0;
    public static final int MIN_RECORD_BOOK = 100000;
    public static final int MAX_RECORD_BOOK = 999999;

    public static void validate(int groupNumber, double averageGrade, int recordBookNumber) {
        // Реализация — Максим, ветка feature/input
    }

    public static boolean isValid(int groupNumber, double averageGrade, int recordBookNumber) {
        // Реализация — Максим, ветка feature/input
        return true;
    }
}