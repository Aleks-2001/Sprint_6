package com.example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(Parameterized.class)
public class ParameterizedTest {

    private  String sex; // Входной параметр для конструктора
    private  Object expectedResult; // Ожидаемый результат (true/false или Exception)

    public ParameterizedTest(String sex, Object expectedResult) {
        this.sex = sex;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{
                {"Самец", true}, // "Самец" -> hasMane = true
                {"Самка", false}, // "Самка" -> hasMane = false
                {"Неверное значение", Exception.class} // Ошибка
        });
    }

    @Test
    public void testLionConstructor() throws Exception {
        if (expectedResult instanceof Class && ((Class<?>) expectedResult).equals(Exception.class)) {
            // Проверяем, что выбрасывается исключение для недопустимого значения
            Exception exception = assertThrows(Exception.class, () -> new Lion(sex));
            assertEquals("Используйте допустимые значения пола животного - самей или самка", exception.getMessage());
        } else {
            // Проверяем корректное создание объекта и значение поля hasMane
            Lion lion = new Lion(sex);
            assertEquals(expectedResult, lion.doesHaveMane());
        }
    }
}