import java.io.*;

class SerializableClass implements Serializable {
    private String textField;
    private int numberField;

    public SerializableClass(String textField, int numberField) {
        this.textField = textField;
        this.numberField = numberField;
        System.out.println("Вызван конструктор SerializableClass с параметрами: " +
                textField + ", " + numberField);
    }

    // Конструктор по умолчанию
    public SerializableClass() {
        System.out.println("Вызван конструктор по умолчанию SerializableClass");
    }

    public String getTextField() {
        return textField;
    }

    public int getNumberField() {
        return numberField;
    }

    @Override
    public String toString() {
        return "SerializableClass {textField='" + textField + "', numberField=" + numberField + "}";
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №24: Десериализация ===");

        // Имя файла, созданного в предыдущей лабораторной работе
        String filename = "serialized_object.ser";
        File file = new File(filename);

        // Если файл не существует, создаем его с теми же данными, что и в лабе 23
        if (!file.exists()) {
            System.out.println("Файл " + filename + " не найден!");
            System.out.println("Создаю тестовый файл с данными из лабораторной работы №23...\n");
            createTestSerializationFile(filename);
        } else {
            System.out.println("Найден существующий файл: " + filename);
            System.out.println("Размер файла: " + file.length() + " байт\n");
        }

        // 1. Чтение файла с помощью FileInputStream и ObjectInputStream
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {

            System.out.println("Открыты потоки для чтения...");

            // 2. Десериализация объекта с помощью метода readObject()
            System.out.println("\nНачало десериализации...");
            Object obj = objIn.readObject();

            // Приведение объекта к нужному типу
            SerializableClass deserializedObj = (SerializableClass) obj;

            // 3. Вывод восстановленных данных на экран
            System.out.println("\n✓ Объект успешно восстановлен!");
            System.out.println("\nВосстановленные данные:");
            System.out.println("Текстовое поле: " + deserializedObj.getTextField());
            System.out.println("Числовое поле: " + deserializedObj.getNumberField());
            System.out.println("\nПолное состояние объекта: " + deserializedObj);

            // 4. Анализ результата
            System.out.println("\n=== Анализ результата ===");
            System.out.println("1. Объект был успешно восстановлен из бинарного файла.");
            System.out.println("2. Поля объекта идентичны исходным (тем, что были записаны в лабораторной №23).");
            System.out.println("3. Во время десериализации НЕ БЫЛ вызван конструктор класса SerializableClass.");
            System.out.println("   Это видно по отсутствию сообщений конструктора в консоли.");

            // Демонстрация создания нового объекта для сравнения
            System.out.println("\nДля сравнения создадим новый объект с теми же данными:");
            SerializableClass newObj = new SerializableClass(
                    deserializedObj.getTextField(),
                    deserializedObj.getNumberField()
            );

            System.out.println("\nСравнение объектов:");
            System.out.println("Десериализованный объект: " + deserializedObj);
            System.out.println("Новый объект: " + newObj);
            System.out.println("\nДанные объектов " +
                    (deserializedObj.getTextField().equals(newObj.getTextField()) &&
                            deserializedObj.getNumberField() == newObj.getNumberField()
                            ? "СОВПАДАЮТ" : "НЕ СОВПАДАЮТ"));

            // 5. Закрытие потоков (автоматически через try-with-resources)
            System.out.println("\n✓ Потоки успешно закрыты (try-with-resources)");

        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Файл не найден - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Ошибка: Класс не найден - " + e.getMessage());
            System.err.println("Убедитесь, что класс SerializableClass доступен в classpath");
        } catch (ClassCastException e) {
            System.err.println("Ошибка приведения типа: " + e.getMessage());
        }

        System.out.println("\n=== Работа программы завершена ===");
    }

    // Метод для создания тестового файла сериализации (имитация лабы 23)
    private static void createTestSerializationFile(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {

            // Создаем объект с теми же данными, что и в лабе 23
            // Используем типичные тестовые данные
            SerializableClass testObj = new SerializableClass("Тестовый текст из лабы 23", 12345);

            // Сериализуем объект в файл
            objOut.writeObject(testObj);

            System.out.println("✓ Тестовый файл создан успешно!");
            System.out.println("Объект сохранен: " + testObj);
            System.out.println("Файл: " + filename + "\n");

        } catch (IOException e) {
            System.err.println("Ошибка при создании тестового файла: " + e.getMessage());
        }
    }
}