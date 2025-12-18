public class Zaschita extends Report{
    public Zaschita(String name, String id){
       super(id, name);
    }
    @Override
    public void generate() {
        System.out.println("Абстрактный класс");
    }
    @Override
    public void print() {
        System.out.println("Абстрактный класс");
    }
}
