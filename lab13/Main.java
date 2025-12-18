// ===== 1. Интерфейс =====
interface Printable {
    // Абстрактный метод
    void print();

    // Default метод
    default void printHeader() {
        System.out.println("=== Заголовок документа ===");
    }

    // Статический метод
    static void showInfo() {
        System.out.println("Интерфейс Printable для печати документов");
    }
}

// ===== 2. Абстрактный базовый класс =====
abstract class Document {
    protected String title;

    public Document(String title) {
        this.title = title;
    }

    // Абстрактный метод
    public abstract void create();

    // Обычный метод
    public void showTitle() {
        System.out.println("Документ: " + title);
    }

    // Статический метод
    public static int countPages(int pages) {
        System.out.println("Всего страниц: " + pages);
        return pages;
    }
}

// ===== 3. Конкретный класс-наследник =====
class Report extends Document implements Printable {
    private String author;

    public Report(String title, String author) {
        super(title);
        this.author = author;
    }

    // Реализация абстрактного метода из Document
    @Override
    public void create() {
        System.out.println("Создание отчёта: " + title);
    }

    // Реализация метода из интерфейса Printable
    @Override
    public void print() {
        System.out.println("Печать отчёта: " + title);
    }

    // Собственный метод
    public void showAuthor() {
        System.out.println("Автор: " + author);
    }

    // Переопределение default метода
    @Override
    public void printHeader() {
        System.out.println("=== ОТЧЁТ ===");
        // Вызов default метода интерфейса
        Printable.super.printHeader();
    }
}

// ===== 4. Main класс =====
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа 13: Интерфейсы ===\n");

        // Создаём объект конкретного класса
        Report report = new Report("Годовой отчёт", "Иванов И.И.");

        System.out.println("1. Работа через объект конкретного класса:");
        report.showTitle();
        report.create();
        report.print();
        report.showAuthor();
        report.printHeader(); // Default метод

        System.out.println("\n2. Полиморфизм через абстрактный класс:");
        // Переменная типа абстрактного класса
        Document doc = report;
        doc.showTitle();
        doc.create();
        // doc.print(); // Недоступно через Document
        // doc.printHeader(); // Недоступно через Document

        System.out.println("\n3. Полиморфизм через интерфейс:");
        // Переменная типа интерфейса
        Printable printer = report;
        printer.print();
        printer.printHeader(); // Default метод
        // printer.create(); // Недоступно через Printable
        // printer.showTitle(); // Недоступно через Printable

        System.out.println("\n4. Вызов статических методов:");
        Document.countPages(15); // Статический метод класса
        Printable.showInfo();    // Статический метод интерфейса

        System.out.println("\n5. Проверка типов и приведение:");
        // Проверяем, реализует ли Document интерфейс Printable
        if (doc instanceof Printable) {
            System.out.println("Документ поддерживает печать");
            // Приведение типа для доступа к методам интерфейса
            Printable printableDoc = (Printable) doc;
            printableDoc.print();
        }

        System.out.println("\n6. Доступ ко всем методам:");
        // Только через конкретный класс доступны ВСЕ методы
        System.out.println("Через Report:");
        report.showTitle();
        report.create();
        report.print();
        report.showAuthor();
    }
}