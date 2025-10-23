package KI305.Leno.Lab2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Клас-драйвер для тестування класу Ship (Лаб2).
 * Цей клас створює та керує файлом логу.
 *
 * @author Leno
 * @version 1.0
 */
public class Lab2 {
    /**
     * Головний метод.
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {

        // Вимога #6 та #7: Коректна обробка файлів з використанням try-with-resources
        // Цей блок автоматично закриває PrintWriter (logger)
        // коли блок try завершується, навіть якщо виникає помилка.
        try (PrintWriter logger = new PrintWriter(new FileWriter("ship_log.txt"))) {

            // Створюємо об'єкти-компоненти
            Engine bigEngine = new Engine(50000);
            CargoHold largeHold = new CargoHold(10000);
            Captain experiencedCaptain = new Captain("Jack Sparrow", 20);

            // Створюємо корабель, використовуючи повний конструктор
            Ship myShip = new Ship("Black Pearl", bigEngine, largeHold, experiencedCaptain, logger);

            // --- Тестуємо методи ---
            myShip.printStatus();

            myShip.raiseAnchor();
            myShip.startEngine();
            myShip.setSpeed(20);
            myShip.setCourse("Tortuga");

            myShip.loadCargo(5000);
            myShip.loadCargo(6000); // Це повинно не спрацювати (перевищення місткості)

            myShip.printStatus();

            myShip.unloadCargo(3000);
            myShip.setSpeed(0);
            myShip.stopEngine();
            myShip.dropAnchor();

            // Створюємо інший корабель, використовуючи конструктор за замовчуванням
            Ship defaultShip = new Ship(logger);
            defaultShip.startEngine(); // Не повинно спрацювати (якір за замовчуванням опущений)
            defaultShip.raiseAnchor();
            defaultShip.startEngine();
            defaultShip.setSpeed(5);

            System.out.println("\n--- Тестування завершено. Перевірте 'ship_log.txt' для деталей. ---");

        } catch (IOException e) {
            System.err.println("Помилка запису у файл логу: " + e.getMessage());
        }
    }
}