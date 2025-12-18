// Конкретный класс 2
class FinanceReport extends DataReport {
    private String currency;

    public FinanceReport(String id, String name, String source, String currency) {
        super(id, name, source);
        this.currency = currency;
    }

    @Override
    public void generate() {
        System.out.println("Генерация финансового отчёта");
        System.out.println("Валюта: " + currency);
    }

    @Override
    public void getData() {
        System.out.println("Загрузка данных из файла: " + source);
    }

    public void showCurrency() {
        System.out.println("Валюта отчёта: " + currency);
    }
}
