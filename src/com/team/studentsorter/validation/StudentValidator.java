package com.team.studentsorter.validation;

public class StudentValidator {
    public static final int MIN_GROUP = 1;
    public static final int MAX_GROUP = 999;
    public static final double MIN_GRADE = 2.0;
    public static final double MAX_GRADE = 5.0;
    public static final int MIN_RECORD_BOOK = 100000;  // 6-значный
    public static final int MAX_RECORD_BOOK = 999999;

    /** Бросает IllegalArgumentException с понятным сообщением. */
    public static void validate(int groupNumber, double averageGrade, int recordBookNumber) {
        // TODO (Максим): проверить каждое поле на диапазон,
        // собрать все ошибки в одно сообщение
    }

    /** То же, но без исключения — для фильтрации строк из файла. */
    public static boolean isValid(int groupNumber, double averageGrade, int recordBookNumber) {
        // TODO (Максим): вернуть true/false, не бросая исключение
        return false;
    }
}