package com.team.studentsorter;

public class SimpleAssert {
    private static int passed = 0;
    private static int failed = 0;

    public static void assertTrue(boolean condition, String testName) {
        if (condition) {
            passed++;
            System.out.println("PASS: " + testName);
        } else {
            failed++;
            System.out.println("FAIL: " + testName);
        }
    }

    public static void assertEquals(Object expected, Object actual, String testName) {
        assertTrue(java.util.Objects.equals(expected, actual),
                testName + " (ожидалось " + expected + ", получено " + actual + ")");
    }

    /** Проверяет, что код бросил исключение нужного типа. */
    public static void assertThrows(Class<? extends Exception> type, Runnable code, String testName) {
        try {
            code.run();
            assertTrue(false, testName + " (исключение " + type.getSimpleName() + " не брошено)");
        } catch (Exception e) {
            assertTrue(type.isInstance(e),
                    testName + " (брошено " + e.getClass().getSimpleName() + ": " + e.getMessage() + ")");
        }
    }

    public static void assertNoThrows(Runnable code, String testName) {
        try {
            code.run();
            assertTrue(true, testName + " (исключение не брошено)");
        } catch (Exception e) {
            assertTrue(false,
                    testName + " (брошено " + e.getClass().getSimpleName() + ": " + e.getMessage() + ")");
        }
    }

    public static void printSummary() {
        System.out.println("========================================");
        System.out.println("ИТОГО: " + passed + " passed, " + failed + " failed");
        System.out.println("========================================");
    }
}