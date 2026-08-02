package com.team.studentsorter.threads;

import com.team.studentsorter.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class OccurrenceCounter {

    /** Считает вхождения target в list, используя threadCount потоков. */
    public static int count(List<Student> list, Student target, int threadCount)
            throws InterruptedException, ExecutionException {

        if (threadCount <= 0)
            throw new IllegalArgumentException("Количество потоков должно быть больше 0");

        if (list.isEmpty())
            return 0;

        int workers = Math.min(threadCount, list.size());
        ExecutorService pool = Executors.newFixedThreadPool(workers);
        List<Future<Integer>> futures = new ArrayList<>();

        int totalSize = list.size();
        int chunkSize = (int) Math.ceil(totalSize / (double) workers);

        try {
            for (int i = 0; i < threadCount; i++) {
                int start = i * chunkSize;
                int end = Math.min(start + chunkSize, totalSize);
                if (start >= end) break;

                List<Student> sub = list.subList(start, end);

                Callable<Integer> task = () -> {
                    int count = 0;
                    for (Student s : sub) {
                        if (s.equals(target))
                            count++;
                    }
                    return count;
                };

                futures.add(pool.submit(task));
            }

            int sum = 0;
            for (Future<Integer> f : futures)
                sum += f.get();

            return sum;

        } finally {
            pool.shutdown();
        }
    }
}