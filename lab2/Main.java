package lab2;

public class Main {
    public static void main(String[] args) {
        int sum = 0;

        for (int i = 2; i <= 100; i += 2) {
            sum += i;
        }

        System.out.print("Сумма всех четных чисел от 2 до 100: " + sum);
        }
    }
