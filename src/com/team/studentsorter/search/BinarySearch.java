package com.team.studentsorter.search;

import java.util.Comparator;
import java.util.List;

// TODO (Шамиль): классика — left/right/mid,
// comparator.compare(list.get(mid), key), вернуть индекс или -1.

public class BinarySearch {
    /** Свой бинарный поиск (готовый Collections.binarySearch запрещён). */
    public static <T> int indexOf(List<T> sortedList, T key, Comparator<T> comparator) {
        if (sortedList == null || sortedList.size() == 0) {
            return -1;
        }
        if (sortedList.size() == 1) {
            return 1;
        }
        int left = 0;
        int right = sortedList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            T midVal = sortedList.get(mid);
            int compResult = comparator.compare(midVal, key);
            if (compResult == 0) {
                return mid;
            } else if (compResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}