package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void testCalculator() {
        Calculator calculator = new Calculator();

        // Basic operations
        assertEquals(7.0, calculator.evaluate("2 + 5"), "2 + 5 should equal 7.0");
        assertEquals(33.0, calculator.evaluate("3 + 6 * 5"), "3 + 6 * 5 should equal 33.0");
        assertEquals(20.0, calculator.evaluate("4 * (2 + 3)"), "4 * (2 + 3) should equal 20.0");
        assertEquals(2.0, calculator.evaluate("(7 + 9) / 8"), "(7 + 9) / 8 should equal 2.0");
    }
}