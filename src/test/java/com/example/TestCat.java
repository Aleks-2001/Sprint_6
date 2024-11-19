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
        MockitoAnnotations.openMocks(this); // Инициализация моков перед каждым тестом.
        catMock = new Cat(felineMock);  // Создаётся объект Cat, использующий felineMock...
        this.predator = felineMock;     // ...в качестве реализации интерфейса Predator
    }

    @Test
    public void testCatGetSound() {
        // Проверка что метод возвращает строку "Мяу"
        assertEquals("Мяу", catMock.getSound());
    }

    @Test
    public void testCatGetFood() throws Exception {
        when(catMock.getFood()).thenReturn(List.of("Животные", "Птицы", "Рыба"));  // Настраиваем мок

        List<String> food = catMock.getFood();  // Проверяем, что метод вернет ожидаемый список еды
        assertEquals(List.of("Животные", "Птицы", "Рыба"), food);

        verify(predator, times(1)).eatMeat();  // Проверяем, что метод predator.eatMeat вызван 1 раз
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


