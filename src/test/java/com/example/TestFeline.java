package com.example;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;


public class TestFeline {

    Feline feline = new Feline();

    @Test
    public void testFelineEatMeat() throws Exception {
        // Вызов метода eatMeat
        List<String> food = feline.eatMeat();
        // Проверка результата
        assertEquals(List.of("Животные", "Птицы", "Рыба"), food);
    }

    @Test
    public void testFelineGetFamily() {
        // Проверка что метод возвращает строку "Кошачьи"
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void testFelineGetKittensDefault() {
        // Проверка что метод getKittens вернет 1 в случает вызова его без параметров
        assertEquals(1, feline.getKittens());
    }

    @Test
    public void testFelineGetKittensWithParameter() {
        // Проверка что метод getKittens вернет 5 в случает вызова его c параметром 5
        assertEquals(5, feline.getKittens(5));
    }

}
