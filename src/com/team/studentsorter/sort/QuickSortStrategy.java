package com.team.studentsorter.sort;

import java.util.Comparator;
import java.util.List;

public class QuickSortStrategy<T> implements SortStrategy<T> {
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() < 2) {
            return;
        }
        quickSort(list, 0, list.size() - 1, comparator);
    }

    private static <T> void quickSort(List<T> list, int left, int right, Comparator<T> comparator) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(list, left, right, comparator);

        quickSort(list, left, pivotIndex - 1, comparator);
        quickSort(list, pivotIndex + 1, right, comparator);
    }

    private static <T> int partition(List<T> list, int left, int right, Comparator<T> comparator) {
        T pivot = list.get(right);
        int i = left;
        for (int j = left; j < right; j++) {
            if (comparator.compare(list.get(j), pivot) < 0) {
                swap(list, i, j);
                i++;
            }
        }
        swap(list, i, right);
        return i;
    }

    private static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}