package com.team.studentsorter;

public class TestRunner {
    public static void main(String[] args) {
        StudentTest.run();
        ValidatorTest.run();        // Максим
        DataFillerTest.run();       // Максим
        SortTest.run();             // Шамиль
        EvenFieldSortTest.run();    // Шамиль
        BinarySearchTest.run();     // Шамиль
        StudentListTest.run();      // Аркадий
        ResultWriterTest.run();     // Аркадий
        OccurrenceCounterTest.run();// Аркадий
        SimpleAssert.printSummary();
    }
}