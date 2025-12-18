import java.io.*;

public class Main {

    // 1. Метод для проверки существования файла
    public static boolean checkFileExists(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            System.out.println("Файл '" + filePath + "' найден.");
            return true;
        } else {
            System.out.println("ОШИБКА: Файл '" + filePath + "' отсутствует.");
            System.out.println("Проверьте путь к файлу: " + file.getAbsolutePath());
            return false;
        }
    }

    // 2. Метод для чтения файла с использованием FileReader и BufferedReader
    public static void readFileWithBufferedReader(String filePath) {
        System.out.println("\n=== ЧТЕНИЕ ФАЙЛА С ПОМОЩЬЮ BufferedReader ===");

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            // Создание FileReader
            fileReader = new FileReader(filePath);
            System.out.println("FileReader создан успешно.");

            // Обертка в BufferedReader
            bufferedReader = new BufferedReader(fileReader);
            System.out.println("BufferedReader создан успешно.");

            // Чтение файла построчно
            String line;
            int lineNumber = 1;

            System.out.println("\nНачало чтения содержимого файла:");
            System.out.println("======================================");

            // Цикл чтения до достижения конца файла (readLine() вернет null)
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("Строка №" + lineNumber + ": " + line);
                lineNumber++;
            }

            System.out.println("======================================");
            System.out.println("Чтение файла выполнено успешно!");
            System.out.println("Всего прочитано строк: " + (lineNumber - 1));

        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } finally {
            // Корректное закрытие потоков
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                    System.out.println("BufferedReader закрыт.");
                }
                if (fileReader != null) {
                    fileReader.close();
                    System.out.println("FileReader закрыт.");
                }
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии потоков: " + e.getMessage());
            }
        }
    }

    // 3. Метод для чтения файла только с FileReader (посимвольно)
    public static void readFileWithFileReader(String filePath) {
        System.out.println("\n=== ДОПОЛНИТЕЛЬНО: ЧТЕНИЕ ФАЙЛА С ПОМОЩЬЮ FileReader ===");

        FileReader fileReader = null;

        try {
            fileReader = new FileReader(filePath);
            System.out.println("Начало посимвольного чтения:");
            System.out.println("======================================");

            int character;
            StringBuilder currentLine = new StringBuilder();
            int lineNumber = 1;

            while ((character = fileReader.read()) != -1) {
                char ch = (char) character;

                if (ch == '\n') {
                    // Конец строки
                    System.out.println("Строка №" + lineNumber + ": " + currentLine.toString());
                    currentLine = new StringBuilder();
                    lineNumber++;
                } else if (ch != '\r') {
                    // Игнорируем символ возврата каретки, добавляем остальные
                    currentLine.append(ch);
                }
            }

            // Выводим последнюю строку, если она есть
            if (currentLine.length() > 0) {
                System.out.println("Строка №" + lineNumber + ": " + currentLine.toString());
            }

            System.out.println("======================================");
            System.out.println("Посимвольное чтение завершено.");

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                    System.out.println("FileReader закрыт.");
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии: " + e.getMessage());
                }
            }
        }
    }

    // 4. Основной метод программы
    public static void main(String[] args) {
        System.out.println("ПРАКТИЧЕСКАЯ РАБОТА №20: ЧТЕНИЕ ДАННЫХ ИЗ ФАЙЛА");
        System.out.println("=====================================================\n");

        // Определение пути к файлу (из предыдущей лабораторной работы)
        // Используем файлы, созданные в Lab19

        // Вариант 1: Чтение файла из практической 19
        String filePath1 = "file2.txt";  // Файл, созданный PrintWriter в Lab19

        System.out.println("ПОПЫТКА 1: Чтение файла 'file2.txt' (из Lab19)");
        System.out.println("-------------------------------------------------");

        if (checkFileExists(filePath1)) {
            readFileWithBufferedReader(filePath1);
        }

        // Вариант 2: Чтение другого файла из практической 19
        String filePath2 = "demo.txt";  // Демонстрационный файл из Lab19

        System.out.println("\n\nПОПЫТКА 2: Чтение файла 'demo.txt' (из Lab19)");
        System.out.println("-------------------------------------------------");

        if (checkFileExists(filePath2)) {
            readFileWithBufferedReader(filePath2);
            // Дополнительно: чтение тем же способом, что и в Lab19
            readFileWithFileReader(filePath2);
        }

        // Вариант 3: Проверка обработки ошибки при отсутствии файла
        String filePath3 = "несуществующий_файл.txt";

        System.out.println("\n\nПОПЫТКА 3: Проверка обработки отсутствующего файла");
        System.out.println("-------------------------------------------------------");

        checkFileExists(filePath3);  // Должен вывести сообщение об ошибке

        System.out.println("\n=====================================================");
        System.out.println("ВЫПОЛНЕНИЕ ПРАКТИЧЕСКОЙ РАБОТЫ №20 ЗАВЕРШЕНО");

        // Инструкция для пользователя
        System.out.println("\nИНСТРУКЦИЯ:");
        System.out.println("1. Убедитесь, что файлы 'file2.txt' и 'demo.txt' существуют");
        System.out.println("   (они создаются при выполнении Lab19Full.java)");
        System.out.println("2. Если файлы отсутствуют, сначала запустите Lab19Full.java");
        System.out.println("3. Проверьте расположение файлов в той же папке, что и эта программа");
    }
}