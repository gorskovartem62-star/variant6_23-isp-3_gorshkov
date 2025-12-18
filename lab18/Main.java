import java.util.ArrayList;

// ====================================
// 1. Предметная область: класс Product
// ====================================
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }
}

// ====================================
// 2. Обобщённый класс с ограничением
// ====================================
class ProductContainer<T extends Product> {
    private ArrayList<T> items;

    // Конструктор, принимающий объект типа T
    public ProductContainer(T item) {
        this.items = new ArrayList<>();
        this.items.add(item);
    }

    // Обычный метод (не использующий явно параметр T в сигнатуре)
    public int getItemCount() {
        return items.size();
    }

    // Метод, явно использующий параметр типа T
    public T getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    // Добавление нового элемента
    public void addItem(T item) {
        items.add(item);
    }

    // Вывод всех элементов
    public void displayAll() {
        for (T item : items) {
            System.out.println(item);
        }
    }
}

// ====================================
// 3. Главный класс с демонстрацией
// ====================================
public class Main {
    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНАЯ РАБОТА №18: GENERICS ===\n");

        // Создаём объекты Product
        Product p1 = new Product("Ноутбук", 999.99);
        Product p2 = new Product("Мышь", 25.50);
        Product p3 = new Product("Клавиатура", 75.00);

        // Создаём контейнер для Product
        System.out.println("1. Создаём контейнер с первым продуктом:");
        ProductContainer<Product> container = new ProductContainer<>(p1);
        System.out.println("   " + p1);

        // Выводим количество элементов
        System.out.println("\n2. Количество элементов в контейнере: " + container.getItemCount());

        // Добавляем ещё продукты
        System.out.println("\n3. Добавляем ещё два продукта:");
        container.addItem(p2);
        container.addItem(p3);
        System.out.println("   " + p2);
        System.out.println("   " + p3);

        // Получаем и выводим второй элемент
        System.out.println("\n4. Получаем второй элемент по индексу 1:");
        Product item = container.getItem(1);
        System.out.println("   Второй элемент: " + item);

        // Выводим все элементы
        System.out.println("\n5. Выводим все элементы контейнера:");
        container.displayAll();

        // Выводим итоговое количество
        System.out.println("\n6. Итоговое количество элементов: " + container.getItemCount());

        // Демонстрация типобезопасности (закомментировано, так как вызовет ошибку компиляции)
        System.out.println("\n7. Демонстрация типобезопасности:");
        System.out.println("   Попытка создать контейнер для String приведёт к ошибке компиляции:");
        System.out.println("   // ProductContainer<String> stringContainer = new ProductContainer<>(\"Test\");");
        System.out.println("   // Ошибка: Type parameter 'java.lang.String' is not within its bound");

        System.out.println("\n=== РАБОТА ЗАВЕРШЕНА ===");
    }
}