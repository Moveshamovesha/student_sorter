package com.team.studentsorter.sort;

import com.team.studentsorter.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO (Шамиль):
// 1. Пройти по списку, запомнить индексы, где recordBookNumber чётный.
// 2. Собрать эти элементы в temp-список.
// 3. strategy.sort(temp, StudentComparators.BY_RECORD_BOOK).
// 4. Записать temp обратно в list по сохранённым индексам.

public class EvenFieldSort {
    /**
     * Сортирует ТОЛЬКО студентов с чётным номером зачётки.
     * Нечётные остаются на исходных позициях.
     * Идея: собрать чётные элементы в отдельный список, отсортировать его
     * (любой нашей SortStrategy), затем записать обратно на те же индексы.
     */
    public static void sortEvenByRecordBook(List<Student> list, SortStrategy<Student> strategy) {
        int[] arrIndex = new int[list.size()];
        List<Student> listTemp = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getRecordBookNumber() % 2 == 0) {
                arrIndex[count] = i;
                listTemp.add(list.get(i));
                count++;
            }
        }
        strategy.sort(listTemp, StudentComparators.BY_RECORD_BOOK);
        for (int i = 0; i < listTemp.size(); i++) {
           list.set(arrIndex[i], listTemp.get(i));
        }
    }
}