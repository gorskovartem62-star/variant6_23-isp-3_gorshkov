// ===== 1. Интерфейс =====
interface Printable {
    void print();

    default void printInfo() {
        System.out.println("Информация для печати");
    }
}

// ===== 2. Абстрактный базовый класс =====
abstract class Document {
    protected String title;

    public Document(String title) {
        this.title = title;
    }

    public abstract void create();

    public void showTitle() {
        System.out.println("Документ: " + title);
    }
}

// ===== 3. Конкретные классы =====
class Report extends Document implements Printable {
    public Report(String title) {
        super(title);
    }

    @Override
    public void create() {
        System.out.println("Создаём отчёт: " + title);
    }

    @Override
    public void print() {
        System.out.println("Печатаем отчёт: " + title);
    }

    public void specificReportMethod() {
        System.out.println("Это метод только для Report");
    }
}

class Contract extends Document implements Printable {
    public Contract(String title) {
        super(title);
    }

    @Override
    public void create() {
        System.out.println("Составляем контракт: " + title);
    }

    @Override
    public void print() {
        System.out.println("Печатаем контракт: " + title);
    }
}

// ===== 4. Main класс =====
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа 14: Приведение типов ===\n");

        // Создаём объекты конкретных классов
        Report report = new Report("Годовой отчёт");
        Contract contract = new Contract("Трудовой договор");

        System.out.println("1. ВОСХОДЯЩЕЕ ПРИВЕДЕНИЕ (Upcasting):");
        System.out.println("Report → Document (автоматически):");
        Document doc1 = report; // Upcasting - автоматически
        doc1.showTitle();
        doc1.create();
        // doc1.print(); // ОШИБКА: не доступно через Document

        System.out.println("\nReport → Printable (автоматически):");
        Printable print1 = report; // Upcasting - автоматически
        print1.print();
        print1.printInfo();

        System.out.println("\n2. ПОЛИМОРФИЗМ через массив:");
        Document[] documents = {report, contract};
        for (Document doc : documents) {
            doc.showTitle();
            doc.create(); // Каждый вызовет свою реализацию

            // Проверка типа перед вызовом специфического метода
            if (doc instanceof Printable) {
                Printable p = (Printable) doc;
                p.print(); // Безопасное приведение
            }
        }

        System.out.println("\n3. НИСХОДЯЩЕЕ ПРИВЕДЕНИЕ (Downcasting):");
        Document doc2 = new Report("Финансовый отчёт");

        // БЕЗОПАСНОЕ приведение с instanceof
        System.out.println("Безопасное downcasting:");
        if (doc2 instanceof Report) {
            Report r = (Report) doc2; // Downcasting
            r.specificReportMethod(); // Доступ к специфическому методу
            r.print();
        }

        // ОПАСНОЕ приведение без проверки (работает если тип верный)
        System.out.println("\nОпасное downcasting (без проверки):");
        try {
            Report r2 = (Report) doc2; // Работает, потому что doc2 действительно Report
            r2.specificReportMethod();
        } catch (ClassCastException e) {
            System.out.println("Ошибка приведения: " + e.getMessage());
        }

        System.out.println("\n4. ОШИБОЧНОЕ приведение:");
        Document doc3 = new Contract("Договор аренды");

        System.out.println("Попытка некорректного приведения:");
        if (doc3 instanceof Report) {
            System.out.println("Это Report - можно приводить");
        } else {
            System.out.println("Это НЕ Report - приведение вызовет ошибку");
        }

        try {
            Report wrongReport = (Report) doc3; // ClassCastException!
            wrongReport.specificReportMethod();
        } catch (ClassCastException e) {
            System.out.println("ПОЙМАНА ОШИБКА: " + e.getClass().getSimpleName());
            System.out.println("Причина: doc3 это Contract, а не Report");
        }

        System.out.println("\n5. ПРИВЕДЕНИЕ к интерфейсу:");
        Document doc4 = new Report("Технический отчёт");

        // Проверяем, реализует ли интерфейс
        if (doc4 instanceof Printable) {
            System.out.println("Объект реализует Printable");
            Printable p = (Printable) doc4;
            p.print();
        }
    }
}