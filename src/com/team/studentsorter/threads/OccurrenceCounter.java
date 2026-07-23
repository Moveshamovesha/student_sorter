package com.team.studentsorter.threads;

import com.team.studentsorter.model.Student;
import java.util.List;
import java.util.concurrent.*;

public class OccurrenceCounter {

    /** Считает вхождения target в list, используя threadCount потоков. */
    public static int count(List<Student> list, Student target, int threadCount)
            throws InterruptedException, ExecutionException {
        // TODO (Аркадий):
        // 1. Разбить list на threadCount примерно равных кусков (subList).
        // 2. ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        // 3. Для каждого куска submit(Callable), который считает
        //    вхождения target через equals (циклом, БЕЗ готовых поисков).
        // 4. Сложить результаты всех Future.get(), вернуть сумму.
        // 5. pool.shutdown() в finally.
        // Результат вывести в консоль в месте вызова (из меню).
        return 0;
    }
}