import java.time.LocalDate;

// ===== 1. Базовый абстрактный класс (верхний уровень) =====
abstract class Report {
    protected String id;
    protected String name;
    protected LocalDate date;

    public Report(String id, String name) {
        this.id = id;
        this.name = name;
        this.date = LocalDate.now();
        Report.count++;
    }

    // Обычный метод
    public void showInfo() {
        System.out.println("Отчёт: " + name);
        System.out.println("ID: " + id);
        System.out.println("Дата: " + date);
    }

    // Абстрактные методы
    public abstract void generate();

    public abstract void print();

    // Статический метод
    private static int count = 0;

    public static int getTotalReports() {
        return count;
    }
}
