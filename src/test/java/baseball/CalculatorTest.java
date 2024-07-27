package baseball;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void init() {
        calculator = new Calculator();
    }


    @AfterEach
    void end() {
        calculator = null;
        System.setIn(System.in);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 2"})
    void add(int i1, int i2) {
        assertThat(calculator.add(i1, i2)).isEqualTo(i1 + i2);
    }

    @ParameterizedTest
    @CsvSource(value = {"2, 1"})
    void subtract(int i1, int i2) {
        assertThat(calculator.subtract(i1, i2)).isEqualTo(i1 - i2);
    }

    @ParameterizedTest
    @CsvSource(value = {"2, 3"})
    void multiple(int i1, int i2) {
        assertThat(calculator.multiple(i1, i2)).isEqualTo(i1 * i2);
    }

    @ParameterizedTest
    @CsvSource(value = {"4, 2"})
    void divide(int i1, int i2) {
        assertThat(calculator.divide(i1, i2)).isEqualTo(i1 / i2);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 2, +, 3", "2, 3, *, 6", "5, 2, -, 3", "6, 2, /, 3"})
    void doCalculate(int i1, int i2, String symbol, int answer) {
        assertThat(calculator.doCalculate(i1, i2, symbol)).isEqualTo(answer);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 + 2 * 3 / 4 - 5"})
    void splitValue(String input) {
        String value = input;
        InputStream inputStream = new ByteArrayInputStream(value.getBytes());
        System.setIn(inputStream);
        String[] splitValues = calculator.getSplitValues();
        String[] expected = {"1", "+", "2", "*", "3", "/", "4", "-", "5"};
        assertThat(splitValues).containsExactly(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2 + 3 * 4 / 2"})
    void doCalculator(String input) {
        String value = input;
        InputStream inputStream = new ByteArrayInputStream(value.getBytes());
        System.setIn(inputStream);
        int answer = calculator.doCalculator();
        assertThat(answer).isEqualTo(10);
    }

}
