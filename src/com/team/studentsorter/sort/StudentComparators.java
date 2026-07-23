package com.team.studentsorter.sort;

import com.team.studentsorter.model.Student;
import java.util.Comparator;

public class StudentComparators {
    public static final Comparator<Student> BY_GROUP =
            Comparator.comparingInt(Student::getGroupNumber);

    public static final Comparator<Student> BY_GRADE =
            Comparator.comparingDouble(Student::getAverageGrade);

    public static final Comparator<Student> BY_RECORD_BOOK =
            Comparator.comparingInt(Student::getRecordBookNumber);

    /** Полная сортировка по всем 3 полям (цепочка). */
    public static final Comparator<Student> BY_ALL_FIELDS =
            BY_GROUP.thenComparing(BY_GRADE).thenComparing(BY_RECORD_BOOK);
}