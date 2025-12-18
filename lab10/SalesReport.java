// Конкретный класс 1
class SalesReport extends DataReport {
    private double total;

    public SalesReport(String id, String name, String source) {
        super(id, name, source);
        this.total = 0.0;
    }

    @Override
    public void generate() {
        System.out.println("Генерация отчёта по продажам из: " + source);
        total = 12500.50;
    }

    @Override
    public void getData() {
        System.out.println("Получение данных о продажах");
    }

    public void showTotal() {
        System.out.println("Общая сумма: " + total);
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Формат: PDF");
    }
}
