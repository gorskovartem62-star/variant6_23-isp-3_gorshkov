package lab5;

public class Main {
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return Math.abs(a * b) / gcd(a, b);
    }

    public static boolean isCoprime(int a, int b) {
        return gcd(a, b) == 1;
    }

    public static void main(String[] args) {

        int a1 = 14, b1 = 21;
        System.out.println("НОД(" + a1 + ", " + b1 + ") = " + gcd(a1, b1));
        System.out.println("НОК(" + a1 + ", " + b1 + ") = " + lcm(a1, b1));
        System.out.println("Взаимно простые? " + isCoprime(a1, b1));
        System.out.println();

        int a2 = 15, b2 = 28;
        System.out.println("НОД(" + a2 + ", " + b2 + ") = " + gcd(a2, b2));
        System.out.println("НОК(" + a2 + ", " + b2 + ") = " + lcm(a2, b2));
        System.out.println("Взаимно простые? " + isCoprime(a2, b2));
        System.out.println();


        int a3 = 0, b3 = 7;
        System.out.println("НОД(" + a3 + ", " + b3 + ") = " + gcd(a3, b3));
        System.out.println("НОК(" + a3 + ", " + b3 + ") = " + lcm(a3, b3));
        System.out.println("Взаимно простые? " + isCoprime(a3, b3));
    }
}

