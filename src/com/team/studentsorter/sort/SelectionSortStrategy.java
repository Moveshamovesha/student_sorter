package com.team.studentsorter.sort;

import java.util.Comparator;
import java.util.List;

public class SelectionSortStrategy<T> implements SortStrategy<T> {
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        // TODO (Шамиль): сортировка выбором.
        // Внешний цикл i от 0 до n-2:
        //   найти индекс минимального элемента в [i..n-1] через comparator,
        //   обменять элементы i и minIndex.
        // НЕ использовать Collections.sort / Arrays.sort — запрещено заданием.
    }
}