import java.io.*;

public class Main {

    // 1. Запись в файл разными способами
    public static void writeWithFileWriter() {
        System.out.println("=== 1. Запись с FileWriter ===");
        FileWriter fw = null;
        try {
            fw = new FileWriter("file1.txt");
            fw.write("Первая строка");
            fw.write("Вторая строка без переноса");
            System.out.println("Записали в file1.txt");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия: " + e.getMessage());
                }
            }
        }
    }

    public static void writeWithPrintWriter() {
        System.out.println("\n=== 2. Запись с PrintWriter ===");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("file2.txt");
            pw.println("Строка №1");
            pw.println("Строка №2");
            pw.printf("Число: %d", 42);
            System.out.println("Записали в file2.txt");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    // 2. Работа с классом File
    public static void workWithFileClass() {
        System.out.println("\n=== 3. Работа с классом File ===");

        File file = new File("data/users.txt");
        if (file.exists()) {
            System.out.println("Файл data/users.txt найден");
        } else {
            System.out.println("Файл data/users.txt отсутствует");
        }

        File dir = new File("отчеты/2024/march");
        if (dir.mkdirs()) {
            System.out.println("Создали папки: отчеты/2024/march");
        } else {
            System.out.println("Не смогли создать папки");
        }

        File newFile = new File("отчеты/2024/march/report.txt");
        try {
            if (newFile.createNewFile()) {
                System.out.println("Создали файл report.txt");
            } else {
                System.out.println("Файл report.txt уже есть");
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // 3. Чтение из файла
    public static void readWithBufferedReader() {
        System.out.println("\n=== 4. Чтение с BufferedReader ===");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("file2.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Прочитали: " + line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия: " + e.getMessage());
                }
            }
        }
    }

    public static void readWithFileReader() {
        System.out.println("\n=== 5. Чтение с FileReader (посимвольно) ===");
        FileReader fr = null;
        try {
            fr = new FileReader("file1.txt");
            int c;
            while ((c = fr.read()) != -1) {
                System.out.print((char)c);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия: " + e.getMessage());
                }
            }
        }
    }

    // 4. Копирование файлов (бинарные)
    public static void copyFileByteByByte() {
        System.out.println("\n=== 6. Копирование побайтово ===");

        // Сначала создадим небольшой текстовый файл для копирования
        try (PrintWriter pw = new PrintWriter("source.txt")) {
            pw.println("Это тестовый файл для копирования");
            pw.println("Вторая строка");
        } catch (IOException e) {
            System.out.println("Не смогли создать source.txt");
            return;
        }

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("source.txt");
            out = new FileOutputStream("copy1.txt");

            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }

            System.out.println("Скопировали source.txt в copy1.txt");

        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия: " + e.getMessage());
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия: " + e.getMessage());
                }
            }
        }
    }

    public static void copyWithBufferedStreams() {
        System.out.println("\n=== 7. Копирование с буферизацией ===");

        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            in = new BufferedInputStream(new FileInputStream("source.txt"));
            out = new BufferedOutputStream(new FileOutputStream("copy2.txt"));

            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }

            System.out.println("Скопировали source.txt в copy2.txt");

        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия: " + e.getMessage());
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия: " + e.getMessage());
                }
            }
        }
    }

    // 5. Демонстрационная программа
    public static void demoProgram() {
        System.out.println("\n=== 8. Демонстрация работы с файлами ===");

        // Создаем тестовый файл
        File testFile = new File("demo.txt");

        System.out.println("Файл существует? " + testFile.exists());

        // Записываем данные
        try (PrintWriter pw = new PrintWriter("demo.txt")) {
            pw.println("Строка 1");
            pw.println("Строка 2");
            pw.println("Строка 3");
            System.out.println("Записали 3 строки в demo.txt");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Читаем данные
        System.out.println("\nЧитаем содержимое demo.txt:");
        try (BufferedReader br = new BufferedReader(new FileReader("demo.txt"))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                System.out.println("Строка " + lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Информация о файле
        System.out.println("\nИнформация о файле demo.txt:");
        System.out.println("Имя: " + testFile.getName());
        System.out.println("Полный путь: " + testFile.getAbsolutePath());
        System.out.println("Размер: " + testFile.length() + " байт");
        System.out.println("Это файл? " + testFile.isFile());
        System.out.println("Это папка? " + testFile.isDirectory());
    }

    public static void main(String[] args) {
        System.out.println("ПРАКТИЧЕСКАЯ РАБОТА №19: ЧТЕНИЕ И ЗАПИСЬ ФАЙЛОВ");
        System.out.println("==================================================\n");

        // Выполняем все задачи по порядку
        writeWithFileWriter();
        writeWithPrintWriter();
        workWithFileClass();
        readWithBufferedReader();
        readWithFileReader();
        copyFileByteByByte();
        copyWithBufferedStreams();
        demoProgram();

        System.out.println("\n==================================================");
        System.out.println("ВСЕ ЗАДАНИЯ ВЫПОЛНЕНЫ!");
    }
}