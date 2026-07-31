package com.team.studentsorter.search;

import java.util.Comparator;
import java.util.List;

public class BinarySearch {

    public static <T> int indexOf(List<T> sortedList, T key, Comparator<T> comparator) {
        if (sortedList == null || sortedList.isEmpty()) {
            return -1;
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