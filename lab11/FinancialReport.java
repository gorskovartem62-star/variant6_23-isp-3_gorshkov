class FinancialReport extends Report {
    private double sum;

    public FinancialReport(String date, double sum) {
        super("Финансовый", date);
        this.sum = sum;
    }
@Override
    public double getSum() {
        return sum;
    }

    @Override
    public void show() {
        super.show();
        System.out.println("Сумма: " + sum);
    }
    public void kaka(){
        System.out.println(getDate());
    }

}



