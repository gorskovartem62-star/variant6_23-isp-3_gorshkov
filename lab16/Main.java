// ===== 1. Интерфейс =====
interface Printable {
    void print();
}

// ===== 2. Абстрактный класс =====
abstract class Document {
    abstract void create();

    void showInfo() {
        System.out.println("Это документ");
    }
}

// ===== 3. Обычный класс =====
class Report {
    void generate() {
        System.out.println("Генерация отчёта");
    }
}

// ===== 4. Main класс =====
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа 16: Анонимные классы ===\n");

        // 1. Анонимный класс на основе ИНТЕРФЕЙСА
        System.out.println("1. Анонимный класс для интерфейса Printable:");
        Printable print1 = new Printable() {
            @Override
            public void print() {
                System.out.println("Печать через анонимный класс");
            }
        };
        print1.print();

        // 2. Анонимный класс на основе АБСТРАКТНОГО класса
        System.out.println("\n2. Анонимный класс для абстрактного класса Document:");
        Document doc1 = new Document() {
            @Override
            void create() {
                System.out.println("Создание документа через анонимный класс");
            }

            @Override
            void showInfo() {
                System.out.println("Переопределённый showInfo()");
            }
        };
        doc1.create();
        doc1.showInfo();

        // 3. Анонимный класс на основе ОБЫЧНОГО класса
        System.out.println("\n3. Анонимный класс для обычного класса Report:");
        Report report1 = new Report() {
            @Override
            void generate() {
                System.out.println("Переопределённая генерация");
            }

            void extraMethod() {
                System.out.println("Дополнительный метод");
            }
        };
        report1.generate();
        // report1.extraMethod(); // Недоступно через тип Report

        // 4. Функциональный интерфейс с лямбдой
        System.out.println("\n4. Функциональный интерфейс и лямбда:");
        Printable print2 = () -> System.out.println("Печать через лямбду");
        print2.print();

        // 5. Сравнение анонимного класса и обычной реализации
        System.out.println("\n5. Сравнение:");

        // Обычная реализация
        class RegularPrinter implements Printable {
            @Override
            public void print() {
                System.out.println("Обычная реализация");
            }
        }
        Printable regular = new RegularPrinter();
        regular.print();

        // Анонимная реализация
        Printable anonymous = new Printable() {
            @Override
            public void print() {
                System.out.println("Анонимная реализация");
            }
        };
        anonymous.print();

        // 6. Передача анонимного объекта в метод
        System.out.println("\n6. Передача анонимного объекта:");
        processPrintable(new Printable() {
            @Override
            public void print() {
                System.out.println("Печать внутри переданного объекта");
            }
        });

        processDocument(new Document() {
            @Override
            void create() {
                System.out.println("Создание внутри переданного объекта");
            }
        });
    }

    // Метод для приёма Printable
    static void processPrintable(Printable p) {
        System.out.print("Обработка: ");
        p.print();
    }

    // Метод для приёма Document
    static void processDocument(Document d) {
        System.out.print("Обработка: ");
        d.create();
    }
}