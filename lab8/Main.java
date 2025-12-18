public class Main {
    public static void main(String[] args) {
        Report report1 = new Report("Общий", "2024-01-15");
        FinancialReport report2 = new FinancialReport("2024-01-16", 1000000);
        StatisticalReport report3 = new StatisticalReport("2024-01-17", 500);

        System.out.println("=== Инкапсуляция ===");
        System.out.println("Тип отчёта: " + report1.getType());
        try {
            report1.setType("");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Наследование ===");
        System.out.println("Финансовый отчёт:");
        System.out.println("Тип: " + report2.getType());
        System.out.println("Сумма: " + report2.getSum());

        System.out.println("\n=== Полиморфизм ===");
        Report[] reports = {report1, report2, report3};

        for (Report r : reports) {
            r.show();
        }
    }
}