package com.example;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(Parameterized.class)
public class ParameterizedTest {

    private String sex;
    private String expectedExceptionMessage;
    private boolean expectedHasMane;

    public ParameterizedTest(String sex, String expectedExceptionMessage, boolean expectedHasMane) {
        this.sex = sex;
        this.expectedExceptionMessage = expectedExceptionMessage;
        this.expectedHasMane = expectedHasMane;
    }

    // Для тестирования конструктора класса Lion применим параметризацию
    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{
                // Три набора параметров для тестов
                {"Самец", null, true},
                {"Самка", null, false},
                {"Неверное значение", "Используйте допустимые значения пола животного - самец или самка", false}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);   // Инициализация моков для каждого теста
    }

    @Test
    public void testLionConstructor() throws Exception {
        if (expectedExceptionMessage != null) {
            // Проверка, что для некорректного значения выбрасывается исключение с правильным сообщением.
            Exception exception = assertThrows(Exception.class, () -> new Lion(sex));
            assertEquals(expectedExceptionMessage, exception.getMessage());
        } else {
            // Проверяется, что для валидных значений ("Самец", "Самка") флаг hasMane устанавливается корректно.
            Lion lion = new Lion(sex);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }
    }
}