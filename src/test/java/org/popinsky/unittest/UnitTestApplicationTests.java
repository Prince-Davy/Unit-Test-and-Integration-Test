package org.popinsky.unittest;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class UnitTestApplicationTests {

    Calculator calculatorTest = new Calculator();

    @Test
    void itShouldAddTwoNumbers() {
        //given
        int numberOne = 20;
        int numberTwo = 30;

        //when
        int result = calculatorTest.add(numberOne, numberTwo);

        //then
        int expected = 50;
        assertThat(result).isEqualTo(expected);
    }

    static class  Calculator{
        int add(int a, int b){return a + b;}
    }

}
