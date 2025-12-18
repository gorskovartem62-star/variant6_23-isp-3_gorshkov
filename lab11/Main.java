import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // 1. Создаем список и добавляем 5-7 объектов
        LinkedList<Report> list = new LinkedList<>();


        list.add(new FinancialReport("2024-01-16", 1000000));
        list.add(new FinancialReport("2024-01-16", 1000));
        list.add(new FinancialReport("2024-01-16", 10000));
        list.add(new FinancialReport("2024-01-16", 100000));
        list.removeIf(i->i.getSum()<10000);
        System.out.println("=========================================================");
        for (Report r : list) r.show();



        list.add(new StatisticalReport("2024-01-17", 500));
        list.add(new Report("Аналитический", "2024-01-18"));
        list.add(new FinancialReport("2024-01-19", 2500000));

        // Используем addFirst() и addLast() для разнообразия
        list.addFirst(new Report("Первый", "2024-01-14"));
        list.addLast(new Report("Последний", "2024-01-20"));

        // 2. Выводим весь список
        System.out.println("=== Полный список ===");
        for (Report r : list) r.show();

        // 3. Добавляем элемент в начало и конец
        list.addFirst(new Report("Новый первый", "2024-01-13"));
        list.addLast(new Report("Новый последний", "2024-01-21"));
        System.out.println("\n=== После добавления в начало и конец ===");
        for (Report r : list) r.show();

        // 4. Удаляем первый и последний элементы
        list.removeFirst();
        list.removeLast();
        System.out.println("\n=== После удаления первого и последнего ===");
        for (Report r : list) r.show();

        // 5. Получаем первый и последний элементы без удаления
        System.out.println("\nПервый элемент:");
        list.getFirst().show();
        System.out.println("Последний элемент:");
        list.getLast().show();

        // 6. Находим первый элемент, удовлетворяющий условию
        Report found = null;
        for (Report r : list) {
            if (r.getType().equals("Финансовый")) {
                found = r;
                break;
            }
        }
        System.out.println("\nПервый финансовый отчёт: " + (found != null ? "найден" : "не найден"));

        // 7. Удаляем все элементы, удовлетворяющие условию
        LinkedList<Report> toRemove = new LinkedList<>();
        for (Report r : list) {
            if (r.getType().equals("Финансовый")) toRemove.add(r);
        }
        list.removeAll(toRemove);
        System.out.println("\n=== После удаления всех финансовых отчётов ===");
        for (Report r : list) r.show();

        // 8. Проверяем, пуст ли список, выводим размер
        System.out.println("\nСписок пуст? " + list.isEmpty());
        System.out.println("Размер списка: " + list.size());



    }
}