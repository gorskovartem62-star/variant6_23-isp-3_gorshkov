// ===== 1. Внешний класс =====
class DocumentProcessor {
    String name;

    DocumentProcessor(String name) {
        this.name = name;
    }

    void process() {
        System.out.println("Обработка в " + name);
    }

    // ===== 2. ВНУТРЕННИЙ класс =====
    class Validator {
        void validate() {
            System.out.println("Валидация в " + name); // Доступ к полю внешнего класса
        }
    }

    // ===== 3. СТАТИЧЕСКИЙ класс =====
    static class Formatter {
        static void format() {
            System.out.println("Форматирование");
        }
    }
}

// ===== 4. Main класс =====
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Вложенные классы ===\n");

        // 1. Внешний класс
        DocumentProcessor dp = new DocumentProcessor("Процессор1");
        dp.process();

        // 2. Внутренний класс
        DocumentProcessor.Validator v = dp.new Validator();
        v.validate();

        // 3. Статический класс
        DocumentProcessor.Formatter.format();

        // 4. Разница в создании
        System.out.println("\nРазница в создании:");
        System.out.println("Внутренний: dp.new Validator()");
        System.out.println("Статический: DocumentProcessor.Formatter.format()");
    }
}