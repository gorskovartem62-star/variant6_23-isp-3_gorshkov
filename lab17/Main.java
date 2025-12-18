// ===== 1. Enum перечисление =====
enum ReportType {
    FINANCIAL(1, "Финансовый отчёт"),
    SALES(2, "Отчёт по продажам"),
    INVENTORY(3, "Отчёт по инвентаризации");

    private final int id;
    private final String description;

    // Конструктор
    ReportType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    // Метод с логикой
    public boolean canExportToFormat(String format) {
        return format.equals("PDF") || format.equals("EXCEL");
    }

    // Статический метод для поиска по id
    public static ReportType getById(int id) {
        for (ReportType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}

// ===== 2. Основной класс предметной области =====
class Report {
    private ReportType type;
    private String date;
    private String dataSource;
    private String fileName;

    public Report(ReportType type, String date, String dataSource) {
        this.type = type;
        this.date = date;
        this.dataSource = dataSource;
        this.fileName = generateFileName();
    }

    // Метод для изменения типа отчёта
    public boolean changeType(ReportType newType) {
        if (type == ReportType.FINANCIAL && newType == ReportType.INVENTORY) {
            System.out.println("Нельзя сменить финансовый отчёт на инвентаризационный напрямую");
            return false;
        }

        this.type = newType;
        this.fileName = generateFileName();
        System.out.println("Тип отчёта изменён на: " + newType.getDescription());
        return true;
    }

    // Генерация имени файла
    private String generateFileName() {
        return "report_" + type.name().toLowerCase() + "_" + date + ".txt";
    }

    // Метод для экспорта (заготовка для будущей работы с файлами)
    public void export(String format) {
        if (type.canExportToFormat(format)) {
            System.out.println("Экспорт " + type.getDescription() + " в формате " + format);
            System.out.println("Файл будет сохранён как: " + fileName);
        } else {
            System.out.println("Формат " + format + " не поддерживается для " + type.getDescription());
        }
    }

    // Вывод информации
    public void displayInfo() {
        System.out.println("=== Информация об отчёте ===");
        System.out.println("Тип: " + type.getDescription() + " (ID: " + type.getId() + ")");
        System.out.println("Дата: " + date);
        System.out.println("Источник данных: " + dataSource);
        System.out.println("Имя файла: " + fileName);
    }
}

// ===== 3. Main класс =====
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа 17: Enum (Вариант 6) ===\n");

        // Демонстрация enum
        System.out.println("1. Демонстрация enum ReportType:");
        System.out.println("Все значения enum:");
        for (ReportType type : ReportType.values()) {
            System.out.println("- " + type + " (ID: " + type.getId() + ", Описание: " + type.getDescription() + ")");
        }

        System.out.println("\n2. Проверка метода canExportToFormat():");
        System.out.println("FINANCIAL в PDF: " + ReportType.FINANCIAL.canExportToFormat("PDF"));
        System.out.println("SALES в CSV: " + ReportType.SALES.canExportToFormat("CSV"));

        System.out.println("\n3. Работа с классом Report:");

        // Создание объектов
        Report report1 = new Report(ReportType.FINANCIAL, "2024-01-15", "База данных");
        Report report2 = new Report(ReportType.SALES, "2024-01-16", "API CRM");

        // Вывод информации
        report1.displayInfo();
        report1.export("PDF");

        System.out.println("\n--- Второй отчёт ---");
        report2.displayInfo();
        report2.export("EXCEL");

        System.out.println("\n4. Изменение типа отчёта:");
        System.out.println("Попытка сменить FINANCIAL на INVENTORY:");
        report1.changeType(ReportType.INVENTORY);

        System.out.println("\nПопытка сменить SALES на INVENTORY:");
        if (report2.changeType(ReportType.INVENTORY)) {
            report2.displayInfo();
        }

        System.out.println("\n5. Поиск enum по ID:");
        System.out.println("ID 2: " + ReportType.getById(2));
        System.out.println("ID 5: " + ReportType.getById(5));

        System.out.println("\n6. Подготовка к будущей работе с файлами:");
        System.out.println("Объекты Report содержат поле fileName:");
        System.out.println("report1: " + report1.getClass().getSimpleName() + " готов к сериализации");
        System.out.println("report2: " + report2.getClass().getSimpleName() + " содержит данные для экспорта");
    }
}