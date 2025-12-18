import java.io.*;
import java.util.Scanner;

// 1. Создание класса, поддерживающего сериализацию
class SerializableClass implements Serializable {
    private String textField;
    private int numberField;

    public SerializableClass(String textField, int numberField) {
        this.textField = textField;
        this.numberField = numberField;
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
        Scanner scanner = new Scanner(System.in);

        // 2. Создание объекта и заполнение полей
        System.out.println("=== Создание объекта для сериализации ===");
        System.out.print("Введите текстовое поле: ");
        String text = scanner.nextLine();

        System.out.print("Введите числовое поле (целое число): ");
        int number = scanner.nextInt();

        SerializableClass obj = new SerializableClass(text, number);
        System.out.println("Создан объект: " + obj);

        // 3. Сериализация объекта в файл
        String filename = "serialized_object.ser";

        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {

            // Запись объекта в файл
            objOut.writeObject(obj);

            // 4. Закрытие потоков (автоматически через try-with-resources)
            System.out.println("\n✓ Объект успешно сохранён в файл: " + filename);

        } catch (IOException e) {
            System.err.println("Ошибка при сериализации: " + e.getMessage());
            return;
        }

        // 5. Проверка наличия файла в каталоге проекта
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("✓ Файл с сериализованным объектом существует в каталоге проекта");
            System.out.println("  Размер файла: " + file.length() + " байт");
            System.out.println("  Полный путь: " + file.getAbsolutePath());
        } else {
            System.out.println("✗ Файл не найден в каталоге проекта");
        }

        // Опционально: десериализация для демонстрации
        System.out.println("\n=== Проверка десериализации ===");
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {

            SerializableClass deserializedObj = (SerializableClass) objIn.readObject();
            System.out.println("Десериализованный объект: " + deserializedObj);
            System.out.println("Текстовое поле: " + deserializedObj.getTextField());
            System.out.println("Числовое поле: " + deserializedObj.getNumberField());

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при десериализации: " + e.getMessage());
        }

        scanner.close();
    }
}