package com.example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedTest {

    private  String sex; // Входной параметр для конструктора
    private boolean expectedHasMane;  // Входной параметр для конструктора

    @Mock
    private Feline felineMock;

    public ParameterizedTest(String sex, boolean expectedHasMane) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{
                {"Самец", true}, // "Самец" -> hasMane = true
                {"Самка", false}, // "Самка" -> hasMane = false
        });
    }

    @Test
    public void testLionConstructor() throws Exception {
            // Проверяем корректное создание объекта и значение поля hasMane
            Lion lion = new Lion(sex, felineMock);
            assertEquals(expectedHasMane, lion.doesHaveMane());

    }
}