import java.sql.SQLSyntaxErrorException;

class StatisticalReport extends Report {
    private int count;

    public int getCount(){
        return  count;
    }
    public void setCount(int a){
        this.count=a;
        if(a==0||a<0){
            System.out.println(0);
        }
        else this.count=a;
    }

    public StatisticalReport(String date, int count) {
        super("Статистический", date);
        this.count = count;
    }
    @Override
    public void show() {
        super.show();
        System.out.println("Количество данных: " + count);
    }
}
