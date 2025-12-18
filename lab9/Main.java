import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Device {
    private String name;
    private int power;

    public Device(String name, int power) {
        this.name = name;
        this.power = power;
    }
    public String getName() {
        return name;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Device{name='" + name + "', power=" + power + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Device device = (Device) obj;
        return power == device.power && name.equals(device.name);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Device> devices = new ArrayList<>();
        devices.add(new Device("Холодильник", 300));
        devices.add(new Device("Телевизор", 150));
        devices.add(new Device("Компьютер", 450));
        devices.add(new Device("Микроволновка", 800));
        devices.add(new Device("Чайник", 2000));
        System.out.println("=== Вывод через for ===");
        for (int i = 0; i < devices.size(); i++) {
            System.out.println(devices.get(i));
        }

        System.out.println("\n=== Вывод через for-each ===");
        for (Device d : devices) {
            System.out.println(d);
        }
        devices.add(0, new Device("Лампа", 60));
        devices.add(new Device("Вентилятор", 50));

        System.out.println("\n=== После добавления в начало и конец ===");
        devices.forEach(System.out::println);

        devices.remove(2); // удалить по индексу 2
        devices.remove(new Device("Чайник", 2000)); // удалить по значению

        System.out.println("\n=== После удалений ===");
        devices.forEach(System.out::println);

        System.out.println("\nРазмер списка: " + devices.size());

        Device selected = devices.get(3);
        System.out.println("Устройство по индексу 3: " + selected.getName() + " (мощность: " + selected.getPower() + ")");

        selected.setPower(600);
        System.out.println("После изменения мощности: " + selected);

        System.out.println("\nПоиск устройства с мощностью > 500:");
        for (Device d : devices) {
            if (d.getPower() > 500) {
                System.out.println("Найдено: " + d);
                break;
            }
        }

        Device checkDevice = new Device("Телевизор", 150);
        boolean contains = devices.contains(checkDevice);
        System.out.println("\nСписок содержит " + checkDevice + "? " + contains);
        devices.clear();
        System.out.println("\nСписок очищен. Размер: " + devices.size());
    }
}