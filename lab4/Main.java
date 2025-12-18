package lab4;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int a = scanner.nextInt();
        int[] massive = new int[a];


        System.out.println("Введите " + a + " чисел:");
        for (int i = 0; i < a; i++) {
            massive[i] = scanner.nextInt();
        }

        int sumChetni = 0;
        for (int i = 0; i < a; i++) {
            if (massive[i] % 2 == 0) {
                sumChetni += massive[i];
            }
        }

        int[] newMassive = new int[a];
        for (int i = 0; i < a; i++) {
            if (massive[i] % 2 != 0) {
                newMassive[i] = 0;
            } else {
                newMassive[i] = massive[i];
            }
        }

        System.out.println("\nИсходный массив:");
        printMassive(massive);

        System.out.println("Сумма чётных элементов: " + sumChetni);

        System.out.println("Массив с заменой нечётных на 0:");
        printMassive(newMassive);
    }

    public static void printMassive(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}