package app;

import converter.Converter;
import converter.SafeConverter;

public class Main {
    public static void main(String[] args) {
        Converter converter = new Converter("Basic Length Converter");
        SafeConverter safeConverter = new SafeConverter("Secure Length Converter");

        System.out.println("=== ДЕМОНСТРАЦИЯ РАБОТЫ КЛАССОВ ===\n");

        System.out.println("1. Перегруженные методы базового класса:");
        System.out.println("100 см в метрах: " + converter.toMeters(100));
        System.out.println("2500 мм в метрах: " + converter.toMeters(2500.0));
        System.out.println("2 км 300 м в метрах: " + converter.toMeters(2, 300));

        System.out.println("\n2. Перегруженные методы дочернего класса:");
        System.out.println("100 см в метрах: " + safeConverter.toMeters(100));
        System.out.println("2500000 мм в метрах (должно быть ограничено 1000): " +
                safeConverter.toMeters(2500000.0));
        System.out.println("-500 мм в метрах (должно быть ограничено 0): " +
                safeConverter.toMeters(-500.0));
        System.out.println("2 км 300 м в метрах: " + safeConverter.toMeters(2, 300));

        System.out.println("\n3. Тестирование ограничения результата:");

        double bigValue = 5000000.0;
        System.out.println("Исходное значение: " + bigValue + " мм");
        System.out.println("Результат базового конвертера: " + converter.toMeters(bigValue) + " м");
        System.out.println("Результат безопасного конвертера: " + safeConverter.toMeters(bigValue) + " м");


        double negativeValue = -1000.0;
        System.out.println("\nИсходное значение: " + negativeValue + " мм");
        System.out.println("Результат базового конвертера: " + converter.toMeters(negativeValue) + " м");
        System.out.println("Результат безопасного конвертера: " + safeConverter.toMeters(negativeValue) + " м");

        System.out.println("\n4. Демонстрация переопределения методов:");
        System.out.println("converter.description(): " + converter.description());
        System.out.println("safeConverter.description(): " + safeConverter.description());

        System.out.println("\nconverter.toString(): " + converter);
        System.out.println("safeConverter.toString(): " + safeConverter);

        System.out.println("\n5. Вспомогательные методы:");
        System.out.println("Имя базового конвертера: " + converter.getUnitName());
        System.out.println("Имя безопасного конвертера: " + safeConverter.getUnitName());

        System.out.println("\n6. Тестирование всех вариантов конвертации:");

        int[] cmValues = {50, 150, 250};
        double[] mmValues = {500.0, 1500.0, 2500000.0};
        int[][] kmMValues = {{1, 200}, {2, 500}, {0, 50}};

        System.out.println("\nБазовый конвертер:");
        for (int cm : cmValues) {
            System.out.println(cm + " см = " + converter.toMeters(cm) + " м");
        }

        System.out.println("\nБезопасный конвертер:");
        for (double mm : mmValues) {
            System.out.println(mm + " мм = " + safeConverter.toMeters(mm) + " м (ограничено 0-1000)");
        }

        System.out.println("\nКонвертация км и м:");
        for (int[] kmM : kmMValues) {
            System.out.println(kmM[0] + " км " + kmM[1] + " м = " +
                    safeConverter.toMeters(kmM[0], kmM[1]) + " м");
        }
    }
}