import java.io.*;

public class Main {

    public static void main(String[] args) {
        // 1. Автоматический ввод данных для обработки NumberFormatException
        System.out.println("=== Тест 1: Обработка NumberFormatException ===");
        String[] testInputs = {"10", "abc", "5", "0", "25"};

        for (String input : testInputs) {
            try {
                int number = Integer.parseInt(input);
                System.out.println("Успешно преобразовано: " + number);

                // 2. Обработка деления на ноль
                try {
                    int result = 100 / number;
                    System.out.println("  100 / " + number + " = " + result);
                } catch (ArithmeticException e) {
                    System.out.println("  Ошибка: деление на ноль при числе " + number);
                }

            } catch (NumberFormatException e) {
                System.out.println("Ошибка преобразования: '" + input + "' не является числом");
            }
        }

        // 3. Работа с файлами (автоматическое создание и чтение)
        System.out.println("\n=== Тест 2: Работа с файлами ===");

        // Создаем тестовый файл
        String testFileName = "test_input.txt";
        String outputFileName = "test_output.txt";

        // Запись в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName))) {
            writer.write("Первая строка файла\n");
            writer.write("Вторая строка файла\n");
            writer.write("Третья строка с числом 123\n");
            System.out.println("Создан тестовый файл: " + testFileName);
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }

        // Чтение из файла
        System.out.println("\nЧтение файла " + testFileName + ":");
        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("  Строка " + lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл " + testFileName + " не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        // 4. Тест с несуществующим файлом
        System.out.println("\n=== Тест 3: Попытка чтения несуществующего файла ===");
        String nonExistentFile = "non_existent_file.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(nonExistentFile))) {
            String line = reader.readLine();
            System.out.println("Прочитано: " + line);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл '" + nonExistentFile + "' не найден (ожидаемое поведение)");
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }

        // 5. Дополнительный тест: обработка разных типов исключений
        System.out.println("\n=== Тест 4: Обработка разных типов исключений ===");

        // Массив с различными сценариями
        Object[] testCases = {"100", 0, "текст", null, "50"};

        for (Object testCase : testCases) {
            System.out.println("\nОбработка значения: " + testCase);

            try {
                // Пытаемся преобразовать в строку и затем в число
                String strValue = String.valueOf(testCase);
                int value = Integer.parseInt(strValue);

                // Пытаемся выполнить деление
                int divisionResult = 1000 / value;
                System.out.println("  Результат: 1000 / " + value + " = " + divisionResult);

            } catch (NumberFormatException e) {
                System.out.println("  Исключение: неверный формат числа");
            } catch (ArithmeticException e) {
                System.out.println("  Исключение: деление на ноль");
            } catch (NullPointerException e) {
                System.out.println("  Исключение: нулевая ссылка");
            } catch (Exception e) {
                System.out.println("  Общее исключение: " + e.getClass().getSimpleName());
            }
        }

        // 6. Запись результата в выходной файл
        System.out.println("\n=== Тест 5: Запись результатов в файл ===");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write("Результаты выполнения программы:\n");
            writer.write("1. Обработаны тестовые данные: 10, abc, 5, 0, 25\n");
            writer.write("2. Создан и прочитан тестовый файл\n");
            writer.write("3. Обработана попытка чтения несуществующего файла\n");
            writer.write("4. Протестированы различные типы исключений\n");
            writer.write("5. Все исключения были корректно обработаны\n");
            System.out.println("Результаты записаны в файл: " + outputFileName);

        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }

        System.out.println("\n=== Программа завершена ===");
    }
}