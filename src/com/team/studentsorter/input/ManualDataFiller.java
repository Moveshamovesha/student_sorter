package com.team.studentsorter.input;

import com.team.studentsorter.model.Student;
import java.util.List;
import java.util.Scanner;

public class ManualDataFiller implements DataFiller {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public List<Student> fill(int size) {
        // TODO (Максим): цикл от 0 до size-1:
        //   запросить 3 поля, собрать через Builder.
        //   Builder.build() бросит IllegalArgumentException при невалидных данных —
        //   поймать, вывести сообщение, попросить ввести заново (i не увеличивать).
        return null;
    }
}