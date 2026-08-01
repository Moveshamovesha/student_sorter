package com.team.studentsorter.sort;

import com.team.studentsorter.model.Student;
import java.util.ArrayList;
import java.util.List;

public class EvenFieldSort {

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