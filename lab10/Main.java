// ===== 3. Конкретные классы =====

// ===== Main класс =====
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа: Абстрактные классы ===\n");

        // Создание объектов
        SalesReport sales = new SalesReport("S001", "Отчёт по продажам", "База данных");
        FinanceReport finance = new FinanceReport("F001", "Финансовый отчёт", "Excel файл", "USD");

        System.out.println("1. Статический метод:");
        System.out.println("Всего отчётов: " + Report.getTotalReports());

        System.out.println("\n2. Работа с SalesReport:");
        sales.showInfo();
        sales.getData();
        sales.generate();
        sales.showTotal();
        sales.print();

        System.out.println("\n3. Работа с FinanceReport:");
        finance.showInfo();
        finance.getData();
        finance.generate();
        finance.showCurrency();
        finance.print();

        System.out.println("\n4. Демонстрация полиморфизма:");
        Report report1 = sales;
        Report report2 = finance;

        System.out.println("--- Отчёт 1 ---");
        report1.showInfo();
        report1.generate();
        report1.print();

        System.out.println("\n--- Отчёт 2 ---");
        report2.showInfo();
        report2.generate();
        report2.print();

        System.out.println("============ЗАЩИТА=============");
    }
}