package lab1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        zad6Ocenka(scanner);
        zad21TipTreugolnika(scanner);
        zad19Vremya(scanner);

    }

    public static void zad6Ocenka(Scanner scanner) {
        System.out.println("\n=== Задача 6: Оценка по баллам ===");
        System.out.print("Введите баллы (0-100): ");
        int ball = scanner.nextInt();

        String ocenka;
        if (ball >= 90 && ball <= 100) {
            ocenka = "5";
        } else if (ball >= 75 && ball <= 89) {
            ocenka = "4";
        } else if (ball >= 60 && ball <= 74) {
            ocenka = "3";
        } else if (ball >= 40 && ball <= 59) {
            ocenka = "2";
        } else if (ball >= 0 && ball < 40) {
            ocenka = "1";
        } else {
            ocenka = "ошибка";
        }
        System.out.println("Оценка: " + ocenka);
    }
    public static void zad21TipTreugolnika(Scanner scanner) {
        System.out.println("\n=== Задача 218=: Проверка времени ===");
        System.out.print("Введите три стороны (a b c): ");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        String tip;

        int kodTipa;
        if (a == b && b == c) {
            kodTipa = 1;
        } else if (a == b || a == c || b == c) {
            kodTipa = 2;
        } else {
            kodTipa = 3;
        }
        tip = switch (kodTipa) {
            case 1 -> "равносторонний";
            case 2 -> "равнобедренный";
            case 3 -> "разносторонний";
            default -> "ошибка";
        };

        switch(kodTipa){
            case 1:
                System.out.println("равносторонний");break;
            case 2:
                System.out.println("равнобедренный");break;
            case 3:
                System.out.println("разносторонний");break;
            default:
                System.out.println("ошибка");
        }
        System.out.println("Тип: " + tip);
    }

    public static void zad19Vremya(Scanner scanner) {
        System.out.println("\n=== Задача 19: Проверка времени ===");
        System.out.print("Введите часы и минуты: ");
        int hours = scanner.nextInt();
        int minute = scanner.nextInt();

        String proverka = (hours >= 0 && hours <= 23 && minute >= 0 && minute <= 59)
                ? "верно"
                : "ошибка";
        System.out.println("Результат: " + proverka);
    }
}