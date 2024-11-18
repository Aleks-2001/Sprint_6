import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Spy;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestFeline {

    @Spy
    private Feline feline;


    @Test
    public void testFelineEatMeat() throws Exception {
        // Мокируем метод getFood, чтобы изолировать тест eatMeat
        doReturn(List.of("Животные", "Птицы", "Рыба")).when(feline).getFood("Хищник");

        // Вызов метода eatMeat
        List<String> food = feline.eatMeat();

        // Проверка результата
        assertEquals(List.of("Животные", "Птицы", "Рыба"), food);

        // Проверка, что getFood вызван 1 раз с правильным параметром
        verify(feline, times(1)).getFood("Хищник");
    }

    @Test
    public void testFelineEatMeatThrowsException() throws Exception {
        // Мокируем выброс исключения
        doThrow(new Exception("Ошибка getFood")).when(feline).getFood("Хищник");

        // Проверяем, что исключение действительно выбрасывается
        Exception exception = assertThrows(Exception.class, () -> feline.eatMeat());
        assertEquals("Ошибка getFood", exception.getMessage());

        // Проверяем, что метод getFood был вызван 1 раз
        verify(feline, times(1)).getFood("Хищник");
    }


    @Test
    public void testGetFamily() {
        // Проверка что метод возвращает строку "Кошачьи"
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void testGetKittensDefault() {
        // Проверка что метод getKittens вернет 1 в случает вызова его без параметров
        assertEquals(1, feline.getKittens());
    }

    @Test
    public void testGetKittensWithParameter() {
        // Проверка что метод getKittens вернет 5 в случает вызова его c параметром 5
        assertEquals(5, feline.getKittens(5));
    }




}
