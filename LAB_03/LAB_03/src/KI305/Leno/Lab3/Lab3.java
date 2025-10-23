package KI305.Leno.Lab3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Клас-драйвер для тестування класу Frigate (Лаб3).
 *
 * @author Leno
 * @version 1.0
 */
public class Lab3 {
    /**
     * Головний метод.
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {

        // Використовуємо try-with-resources для автоматичного закриття файлу
        try (PrintWriter logger = new PrintWriter(new FileWriter("lab3_log.txt"))) {

            // Неможливо створити об'єкт абстрактного класу Ship
            // Ship genericShip = new Ship(logger); // <--- ЦЕЙ РЯДОК ДАСТЬ ПОМИЛКУ КОМПІЛЯЦІЇ

            // Створюємо компоненти для нашого фрегата
            Engine frigateEngine = new Engine(80000);
            CargoHold frigateHold = new CargoHold(1000); // 1000т - макс. трюм
            Captain frigateCaptain = new Captain("Horatio Hornblower", 15);

            // Створюємо об'єкт Frigate
            Frigate myFrigate = new Frigate(
                    "HMS Indefatigable",
                    frigateEngine,
                    frigateHold,
                    frigateCaptain,
                    logger,
                    44 // Кількість гармат
            );

            // Тестуємо методи
            myFrigate.printStatus();

            myFrigate.raiseAnchor();
            myFrigate.startEngine();
            myFrigate.setSpeed(25);
            myFrigate.setCourse("Blockade of Brest");

            // Тестуємо перевизначений метод loadCargo
            myFrigate.loadCargo(500); // Повинно бути відмовлено (1000 / 4 = 250 max)
            myFrigate.loadCargo(150); // Це повинно спрацювати

            // Тестуємо методи інтерфейсу
            myFrigate.raiseAlert("Red");

            myFrigate.setSpeed(0); // Потрібно зупинитись для стрільби
            myFrigate.fireCannons();

            // Фінальний статус
            myFrigate.printStatus();

            System.out.println("\n--- Тестування Лаб3 завершено. Перевірте 'lab3_log.txt' для деталей. ---");

        } catch (IOException e) {
            System.err.println("Помилка запису у файл логу: " + e.getMessage());
        }
    }
}