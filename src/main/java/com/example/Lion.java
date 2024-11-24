package com.example;

import java.util.List;

public class Lion {
    boolean hasMane;
    private Feline feline;

    // Основной конструктор с инъекцией зависимости Feline
    public Lion(String sex, Feline feline) throws Exception {
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }
        this.feline = feline;
    }

    //  Перегруженный конструктор, сохраняет возможность упрощенного создания объекта Lion
//    public Lion(String sex) throws Exception {
//        this(sex, new Feline()); // Вызывает основной конструктор
//    }

    public int getKittens() {
        return feline.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return feline.getFood("Хищник");
    }
}