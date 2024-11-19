package com.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestCat {

    @Mock
    private Feline felineMock;

    private Cat catMock;

    private Predator predator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        catMock = new Cat(felineMock);
        this.predator = felineMock;
    }

    @Test
    public void testCatGetSound() {
        // Проверка что метод возвращает строку "Мяу"
        assertEquals("Мяу", catMock.getSound());
    }

    @Test
    public void testCatGetFood() throws Exception {
        // Настраиваем мок
        when(catMock.getFood()).thenReturn(List.of("Животные", "Птицы", "Рыба"));

        // Проверяем метод
        List<String> food = catMock.getFood();
        assertEquals(List.of("Животные", "Птицы", "Рыба"), food);

        // Проверяем, что метод predator.eatMeat вызван 1 раз
        verify(predator, times(1)).eatMeat();
    }

    @Test
    public void testCatGetFoodThrowsException() throws Exception {
        // Настраиваем мок для выброса исключения
        when(catMock.getFood()).thenThrow(new Exception("Ошибка получения пищи"));

        // Проверяем, что метод выбрасывает исключение
        Exception exception = assertThrows(Exception.class, () -> catMock.getFood());
        assertEquals("Ошибка получения пищи", exception.getMessage());

        // Проверяем, что метод feline.getFood("Хищник") вызван 1 раз
        verify(predator, times(1)).eatMeat();
    }
}


