package lab6;

public class Main {
    public static void main(String[] args) {

        Fraction a = new Fraction();
        System.out.println(a);


        Fraction b = new Fraction(3, 4);
        System.out.println(b);

        Fraction c = new Fraction(b);
        System.out.println(c);

        System.out.println("Значение дроби b: " + b.value());
        Fraction opposite = b.negate();
        System.out.println("Противоположная дробь для b: " + opposite);

    }
}

class Fraction {
    int num;
    int den;

    public Fraction() {
        this(0, 1);
    }

    public Fraction(int num5, int den) {
        num = num5;
        this.den = den;
    }

    public Fraction(Fraction other) {
        num = other.num;
        den = other.den;
    }

    public double value() {
        return (double) num / den;
    }

    public Fraction negate() {
        int newNum = -num;
        return new Fraction(newNum, den);
    }

    @Override
    public String toString() {
        return "Дробь: " + num + "/" + den;


    }
}