package com.team.studentsorter.sort;

import java.util.Comparator;
import java.util.List;

// TODO (Шамиль): сортировка выбором.
// Внешний цикл i от 0 до n-2:
//   найти индекс минимального элемента в [i..n-1] через comparator,
//   обменять элементы i и minIndex.
// НЕ использовать Collections.sort / Arrays.sort — запрещено заданием.

public class SelectionSortStrategy<T> implements SortStrategy<T> {
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (comparator.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, temp);
            }
        }
    }
}