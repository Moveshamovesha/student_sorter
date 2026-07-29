package test;

import com.team.studentsorter.validation.StudentValidator;

public class ValidatorTest {
    static public void run() {
        noThrows();
        hasThrows();
    }

    static private void noThrows() {
        SimpleAssert.assertNoThrows(
            () -> StudentValidator.validate(123, 3.5, 123_456),
            "StudentValidator: прохождение валидации (комбинация 1/9)"
        );
        
        SimpleAssert.assertNoThrows(
            () -> StudentValidator.validate(1, 2.0, 100_000),
            "StudentValidator: прохождение валидации (комбинация 2/9)"
        );
        
        SimpleAssert.assertNoThrows(
            () -> StudentValidator.validate(999, 2.0, 100_000),
            "StudentValidator: прохождение валидации (комбинация 3/9)"
        );

        SimpleAssert.assertNoThrows(
            () -> StudentValidator.validate(1, 5.0, 100_000),
            "StudentValidator: прохождение валидации (комбинация 4/9)"
        );

        SimpleAssert.assertNoThrows(
            () -> StudentValidator.validate(999, 5.0, 100_000),
            "StudentValidator: прохождение валидации (комбинация 5/9)"
        );

        SimpleAssert.assertNoThrows(
            () -> StudentValidator.validate(1, 2.0, 999_999),
            "StudentValidator: прохождение валидации (комбинация 6/9)"
        );

        SimpleAssert.assertNoThrows(
            () -> StudentValidator.validate(999, 2.0, 999_999),
            "StudentValidator: прохождение валидации (комбинация 7/9)"
        );

        SimpleAssert.assertNoThrows(
            () -> StudentValidator.validate(1, 5.0, 999_999),
            "StudentValidator: прохождение валидации (комбинация 8/9)"
        );

        SimpleAssert.assertNoThrows(
            () -> StudentValidator.validate(999, 5.0, 999_999),
            "StudentValidator: прохождение валидации (комбинация 9/9)"
        );
    }

    static private void hasThrows() {
        SimpleAssert.assertThrows(
            IllegalArgumentException.class,
            () -> StudentValidator.validate(0, 0, 0),
            "StudentValidator: получение исключения при валидации (комбинация 1/4)"
        );

        SimpleAssert.assertThrows(
            IllegalArgumentException.class,
            () -> StudentValidator.validate(-100, -10.5, -100),
            "StudentValidator: получение исключения при валидации (комбинация 2/4)"
        );

        SimpleAssert.assertThrows(
            IllegalArgumentException.class,
            () -> StudentValidator.validate(100_001, 2.5, 123_456),
            "StudentValidator: получение исключения при валидации (комбинация 3/4)"
        );

        SimpleAssert.assertThrows(
            IllegalArgumentException.class,
            () -> StudentValidator.validate(123, 10.10, 123_456),
            "StudentValidator: получение исключения при валидации (комбинация 4/4)"
        );
    }
}
