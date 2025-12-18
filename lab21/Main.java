// Практическая работа №21: Банковская система с обработкой исключений предметной области

// 1. СОБСТВЕННЫЕ КЛАССЫ ИСКЛЮЧЕНИЙ

// Исключение для отрицательного баланса
class NegativeBalanceException extends Exception {
    public NegativeBalanceException(String message) {
        super(message);
    }
}

// Исключение для недостаточных средств
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Исключение для перевода на тот же счет
class SameAccountTransferException extends Exception {
    public SameAccountTransferException(String message) {
        super(message);
    }
}

// Исключение для невалидной суммы
class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

// 2. КЛАСС БАНКОВСКОГО СЧЕТА

class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;

    public BankAccount(String accountNumber, String ownerName, double initialBalance)
            throws NegativeBalanceException, InvalidAmountException {

        if (initialBalance < 0) {
            throw new NegativeBalanceException("Начальный баланс не может быть отрицательным: " + initialBalance);
        }

        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    // Метод для пополнения счета
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Сумма пополнения должна быть положительной: " + amount);
        }
        balance += amount;
        System.out.println("Счет " + accountNumber + " пополнен на " + amount + ". Новый баланс: " + balance);
    }

    // Метод для снятия средств
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Сумма снятия должна быть положительной: " + amount);
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Недостаточно средств. Баланс: " + balance + ", запрошено: " + amount);
        }

        balance -= amount;
        System.out.println("Со счета " + accountNumber + " снято " + amount + ". Остаток: " + balance);
    }

    // Метод для перевода на другой счет
    public void transferTo(BankAccount recipient, double amount)
            throws InsufficientFundsException, InvalidAmountException, SameAccountTransferException {

        if (this == recipient || this.accountNumber.equals(recipient.accountNumber)) {
            throw new SameAccountTransferException("Нельзя переводить средства на тот же самый счет: " + accountNumber);
        }

        if (amount <= 0) {
            throw new InvalidAmountException("Сумма перевода должна быть положительной: " + amount);
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Недостаточно средств для перевода. Баланс: " + balance + ", сумма: " + amount);
        }

        this.balance -= amount;
        recipient.balance += amount;

        System.out.println("Перевод " + amount + " со счета " + this.accountNumber +
                " на счет " + recipient.accountNumber);
        System.out.println("Отправитель (" + this.ownerName + "): остаток " + this.balance);
        System.out.println("Получатель (" + recipient.ownerName + "): баланс " + recipient.balance);
    }

    // Геттеры
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Счет №" + accountNumber + " (" + ownerName + "): баланс = " + balance;
    }
}

// 3. ОСНОВНОЙ КЛАСС ПРОГРАММЫ

public class Main {

    public static void main(String[] args) {
        System.out.println("БАНКОВСКАЯ СИСТЕМА");
        System.out.println("Демонстрация обработки исключений предметной области\n");

        try {
            // 1. Создание счетов
            System.out.println("1. Создание банковских счетов:");
            BankAccount account1 = new BankAccount("ACC001", "Иван Петров", 1000.0);
            BankAccount account2 = new BankAccount("ACC002", "Мария Сидорова", 500.0);
            System.out.println(account1);
            System.out.println(account2);

            // 2. Попытка создания счета с отрицательным балансом
            System.out.println("\n2. Попытка создания счета с отрицательным балансом:");
            try {
                BankAccount invalidAccount = new BankAccount("ACC003", "Ошибка", -100.0);
            } catch (NegativeBalanceException e) {
                System.out.println("Поймано исключение: " + e.getMessage());
            }

            // 3. Корректные операции
            System.out.println("\n3. Корректные операции:");
            account1.deposit(200.0);
            account1.withdraw(300.0);

            // 4. Попытка снять больше, чем есть на счете
            System.out.println("\n4. Попытка снять больше, чем есть на счете:");
            try {
                account2.withdraw(600.0);
            } catch (InsufficientFundsException e) {
                System.out.println("Поймано исключение: " + e.getMessage());
            }

            // 5. Попытка перевода на тот же счет
            System.out.println("\n5. Попытка перевода на тот же счет:");
            try {
                account1.transferTo(account1, 100.0);
            } catch (SameAccountTransferException e) {
                System.out.println("Поймано исключение: " + e.getMessage());
            }

            // 6. Попытка перевода отрицательной суммы
            System.out.println("\n6. Попытка перевода отрицательной суммы:");
            try {
                account1.transferTo(account2, -50.0);
            } catch (InvalidAmountException e) {
                System.out.println("Поймано исключение: " + e.getMessage());
            }

            // 7. Корректный перевод между счетами
            System.out.println("\n7. Корректный перевод между счетами:");
            account1.transferTo(account2, 200.0);

            // 8. Попытка пополнить счет нулевой суммой
            System.out.println("\n8. Попытка пополнить счет нулевой суммой:");
            try {
                account1.deposit(0.0);
            } catch (InvalidAmountException e) {
                System.out.println("Поймано исключение: " + e.getMessage());
            }

            // 9. Итоговые балансы
            System.out.println("\n9. Итоговые балансы:");
            System.out.println(account1);
            System.out.println(account2);

        } catch (Exception e) {
            System.out.println("Неожиданная ошибка: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\nРабота банковской системы завершена.");
    }
}