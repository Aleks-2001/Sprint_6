package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class TestLion {

    private String sex;
    private String expectedExceptionMessage;
    private boolean expectedHasMane;

    @Mock
    private Feline felineMock;

    public TestLion(String sex, String expectedExceptionMessage, boolean expectedHasMane) {
        this.sex = sex;
        this.expectedExceptionMessage = expectedExceptionMessage;
        this.expectedHasMane = expectedHasMane;
    }

    // В тесте конструктора объектов класса Lion используем параметризацию
    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{
                {"Самец", null, true},
                {"Самка", null, false},
                {"Другое", "Используйте допустимые значения пола животного - самец или самка", false}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLionConstructor() throws Exception {
        if (expectedExceptionMessage != null) {
            Exception exception = assertThrows(Exception.class, () -> new Lion(sex));
            assertEquals(expectedExceptionMessage, exception.getMessage());
        } else {
            Lion lion = new Lion(sex);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }
    }

    @Test
    public void testGetKittens() throws Exception {
        // Настраиваем мок Feline
        when(felineMock.getKittens()).thenReturn(1);

        // Создаём объект Lion с замокированным Feline
        Lion lion = new Lion("Самец");
        lion.feline = felineMock;

        // Проверяем метод getKittens
        assertEquals(1, lion.getKittens());
        verify(felineMock, times(1)).getKittens();
    }

    @Test
    public void testGetFood() throws Exception {
        // Настраиваем мок Feline
        when(felineMock.getFood("Хищник")).thenReturn(Arrays.asList("Животные", "Птицы", "Рыба"));

        // Создаём объект Lion с замокированным Feline
        Lion lion = new Lion("Самец");
        lion.feline = felineMock;

        // Проверяем метод getFood
        assertEquals(Arrays.asList("Животные", "Птицы", "Рыба"), lion.getFood());
        verify(felineMock, times(1)).getFood("Хищник");
    }

    @Test
    public void testGetFoodThrowsException() throws Exception {
        // Настраиваем мок Feline
        when(felineMock.getFood("Хищник")).thenThrow(new Exception("Ошибка получения пищи"));

        // Создаём объект Lion с замокированным Feline
        Lion lion = new Lion("Самец");
        lion.feline = felineMock;

        // Проверяем, что getFood выбрасывает исключение
        Exception exception = assertThrows(Exception.class, lion::getFood);
        assertEquals("Ошибка получения пищи", exception.getMessage());
        verify(felineMock, times(1)).getFood("Хищник");
    }
}
