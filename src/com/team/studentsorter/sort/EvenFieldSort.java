package com.team.studentsorter.sort;

import com.team.studentsorter.model.Student;
import java.util.List;

public class EvenFieldSort {
    /**
     * Сортирует ТОЛЬКО студентов с чётным номером зачётки.
     * Нечётные остаются на исходных позициях.
     * Идея: собрать чётные элементы в отдельный список, отсортировать его
     * (любой нашей SortStrategy), затем записать обратно на те же индексы.
     */
    public static void sortEvenByRecordBook(List<Student> list, SortStrategy<Student> strategy) {
        // TODO (Шамиль):
        // 1. Пройти по списку, запомнить индексы, где recordBookNumber чётный.
        // 2. Собрать эти элементы в temp-список.
        // 3. strategy.sort(temp, StudentComparators.BY_RECORD_BOOK).
        // 4. Записать temp обратно в list по сохранённым индексам.
    }
}