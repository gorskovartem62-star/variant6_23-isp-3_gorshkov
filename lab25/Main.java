
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Лабораторная работа №25: Клиент-серверное взаимодействие через сокеты
 * Реализация системы проверки одобрения кредита на основе доходов и расходов
 */

public class Main {

    /**
     * КЛАСС СЕРВЕРА
     */
    static class CreditServer {
        public static void main(String[] args) {
            System.out.println("=== ЗАПУСК СЕРВЕРА ===");
            System.out.println("Сервер запущен. Ожидание подключения клиента на порту 5500...");

            try (ServerSocket serverSocket = new ServerSocket(5500)) {
                while (true) {
                    try (Socket clientSocket = serverSocket.accept()) {
                        System.out.println("\nКлиент подключен: " + clientSocket.getInetAddress());

                        InputStream in = clientSocket.getInputStream();
                        OutputStream out = clientSocket.getOutputStream();

                        BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                        String message = br.readLine();
                        System.out.println("Получено от клиента: " + message);

                        String response = processCredit(message) + "\n";

                        out.write(response.getBytes(StandardCharsets.UTF_8));
                        out.flush();
                        System.out.println("Отправлен ответ: " + response.trim());
                    } catch (IOException e) {
                        System.out.println("Ошибка соединения с клиентом: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка запуска сервера: " + e.getMessage());
            }
        }

        public static String processCredit(String arg) {
            arg = arg.trim();
            String[] parts = arg.split("\\s+");

            if (parts.length < 2) {
                return "Ошибка: неверный формат данных. Ожидается: доход расход";
            }

            try {
                int income = Integer.parseInt(parts[0]);
                int expenses = Integer.parseInt(parts[1]);

                System.out.println("Анализ данных:");
                System.out.println("  Доходы: " + income);
                System.out.println("  Расходы: " + expenses);

                int dif = income - expenses;
                System.out.println("  Разница: " + dif);

                if (dif < 15000) {
                    return "Кредит не одобрен. Разница между доходами и расходами: " + dif + " (требуется минимум 15000)";
                } else {
                    return "Кредит одобрен! Разница между доходами и расходами: " + dif;
                }
            } catch (NumberFormatException e) {
                return "Ошибка: неверный формат чисел. Введите целые числа";
            }
        }
    }


    static class CreditClient {
        public static void main(String[] args) {
            System.out.println("=== ЗАПУСК КЛИЕНТА ===");
            Scanner scan = new Scanner(System.in);

            System.out.println("\nВведите доходы и расходы через пробел (например: 50000 30000):");
            System.out.print("> ");
            String data = scan.nextLine();

            try (Socket socket = new Socket("localhost", 5500)) {
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();

                PrintWriter pw = new PrintWriter(out, true);
                BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

                System.out.println("Подключились к серверу");

                pw.println(data);
                System.out.println("Отправлены данные: " + data);

                String message = br.readLine();
                System.out.println("\n=== РЕЗУЛЬТАТ ===");
                System.out.println("Ответ сервера: " + message);

            } catch (ConnectException e) {
                System.out.println("Ошибка: Не удалось подключиться к серверу. Убедитесь, что сервер запущен.");
            } catch (IOException e) {
                System.out.println("Ошибка подключения к серверу: " + e.getMessage());
            } finally {
                scan.close();
                System.out.println("\nКлиент завершил работу");
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("Лабораторная работа №25: Клиент-серверное взаимодействие");
        System.out.println("Выберите режим работы:");
        System.out.println("1. Запустить сервер");
        System.out.println("2. Запустить клиент");
        System.out.print("> ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            CreditServer.main(args);
        } else if (choice.equals("2")) {
            CreditClient.main(args);
        } else {
            System.out.println("Неверный выбор. Запустите программу с аргументом:");
            System.out.println("  Для сервера: java SocketLab$CreditServer");
            System.out.println("  Для клиента: java SocketLab$CreditClient");
        }

        scanner.close();
    }
}

