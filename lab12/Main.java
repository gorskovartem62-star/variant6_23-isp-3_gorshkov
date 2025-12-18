// ===== 1. Базовый абстрактный класс =====
abstract class Report {
    protected String name;

    public Report(String name) {
        this.name = name;
    }
    // Метод, который будет переопределён в дочерних классах
    public abstract void generate();

    // Общий метод для всех отчётов
    public void showName() {
        System.out.println("Отчёт: " + name);
    }
}
// ===== 2. Конкретные классы =====
class SalesReport extends Report {
    public SalesReport(String name) {
        super(name);
    }
    @Override
    public void generate() {
        System.out.println("Генерация отчёта по продажам: " + name);
    }
}
class FinanceReport extends Report {
    public FinanceReport(String name) {
        super(name);
    }

    @Override
    public void generate() {
        System.out.println("Генерация финансового отчёта: " + name);
    }
}

// ===== 3. Main класс =====
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация полиморфизма ===\n");

        // Создаём объекты разных типов
        SalesReport sales = new SalesReport("Отчёт по продажам");
        FinanceReport finance = new FinanceReport("Финансовый отчёт");

        System.out.println("1. Прямой вызов методов:");
        sales.generate();    // Вызовет метод SalesReport
        finance.generate();  // Вызовет метод FinanceReport

        System.out.println("\n2. Полиморфизм через общий метод:");
        // Вызов через метод, принимающий базовый тип
        processReport(sales);
        processReport(finance);

        System.out.println("\n3. Полиморфизм через массив:");
        Report[] reports = {sales, finance};

        for (Report report : reports) {
            // Полиморфный вызов - один метод, разные реализации
            report.generate();
        }

        System.out.println("\n4. Общий метод базового класса:");
        // Все объекты могут использовать метод showName()
        for (Report report : reports) {
            report.showName();
        }
    }

    // Общий метод, принимающий объекты базового типа
    public static void processReport(Report report) {
        System.out.print("Обработка: ");
        report.generate(); // Вызовется нужная реализация
    }
}