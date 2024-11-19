package com.example;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class TestLion {

    private String sex;
    private String expectedExceptionMessage;
    private boolean expectedHasMane;

    @Mock
    private Feline felineMock;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);   // Инициализация моков для каждого теста
    }

    @Test
    public void testLionGetKittens() throws Exception {
        // Настраиваем мок Feline для возврата 1
        when(felineMock.getKittens()).thenReturn(1);

        // Создаём объект Lion с замокированным Feline
        Lion lion = new Lion("Самец");
        lion.feline = felineMock;

        // Проверяем, что метод getKittens в Lion вызывает метод у Feline, метод вызван 1 раз.
        assertEquals(1, lion.getKittens());
        verify(felineMock, times(1)).getKittens();
    }

    @Test
    public void testLionGetFood() throws Exception {
        // Настраиваем мок Feline для возврата списка еды
        when(felineMock.getFood("Хищник")).thenReturn(Arrays.asList("Животные", "Птицы", "Рыба"));

        // Создаём объект Lion с замокированным Feline
        Lion lion = new Lion("Самец");
        lion.feline = felineMock;

        // Проверяем, что метод getFood в Lion вызывает метод у Feline, метод вызван 1 раз.
        assertEquals(Arrays.asList("Животные", "Птицы", "Рыба"), lion.getFood());
        verify(felineMock, times(1)).getFood("Хищник");
    }

    @Test
    public void testLionGetFoodThrowsException() throws Exception {
        // Настраиваем мок Feline для выброса исключения при вызове getFood.
        when(felineMock.getFood("Хищник")).thenThrow(new Exception("Ошибка получения пищи"));

        // Создаём объект Lion с замокированным Feline
        Lion lion = new Lion("Самец");
        lion.feline = felineMock;

        // Проверяем, что getFood выбрасывает исключение, сообщение корректное, метод вызван 1 раз.
        Exception exception = assertThrows(Exception.class, lion::getFood);
        assertEquals("Ошибка получения пищи", exception.getMessage());
        verify(felineMock, times(1)).getFood("Хищник");
    }
}


