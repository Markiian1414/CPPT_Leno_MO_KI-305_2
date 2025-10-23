package KI305.Leno.Lab2;

import java.io.PrintWriter;
import java.time.LocalDateTime; // <-- Імпорт для часу
import java.time.format.DateTimeFormatter; // <-- Імпорт для форматування

/**
 * Клас <code>Ship</code> реалізує сутність "корабель".
 * Він включає компоненти, такі як Двигун, ВантажнийТрюм та Капітан,
 * і логує свою діяльність у файл.
 *
 * @author Leno
 * @version 1.1
 */
public class Ship {

    // --- Поля ---
    private String name;
    private int currentSpeedKnots;
    private boolean isAnchorDown;

    // --- Поля-компоненти (Вимога #2) ---
    private Engine engine;
    private CargoHold cargoHold;
    private Captain captain;

    // --- Поля для логування ---
    private PrintWriter logger;
    // Додано форматер для дати і часу
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    // --- Конструктори (Вимога #3) ---

    /**
     * Конструктор за замовчуванням.
     * Створює корабель з компонентами за замовчуванням та назвою "Unnamed".
     * @param logger Об'єкт PrintWriter для логування.
     */
    public Ship(PrintWriter logger) {
        this.name = "Unnamed";
        this.engine = new Engine(1000); // За замовчуванням 1000 к.с.
        this.cargoHold = new CargoHold(500); // За замовчуванням 500 тонн
        this.captain = new Captain("No Captain", 0);
        this.currentSpeedKnots = 0;
        this.isAnchorDown = true;
        this.logger = logger;
        log("Корабель 'Unnamed' створено з компонентами за замовчуванням.");
    }

    /**
     * Перевантажений конструктор.
     * Створює корабель з конкретною назвою та компонентами.
     * @param name Назва корабля.
     * @param engine Об'єкт двигуна корабля.
     * @param cargoHold Об'єкт вантажного трюму корабля.
     * @param captain Об'єкт капітана корабля.
     * @param logger Об'єкт PrintWriter для логування.
     */
    public Ship(String name, Engine engine, CargoHold cargoHold, Captain captain, PrintWriter logger) {
        this.name = name;
        this.engine = engine;
        this.cargoHold = cargoHold;
        this.captain = captain;
        this.currentSpeedKnots = 0;
        this.isAnchorDown = true;
        this.logger = logger;
        log("Корабель '" + name + "' створено.");
    }

    // --- Методи (Вимога #4 - 10+ методів) ---

    /**
     * Приватний допоміжний метод для логування (Вимога #5).
     * @param message Повідомлення для логу.
     */
    private void log(String message) {
        // Додаємо поточний час до логу
        LocalDateTime now = LocalDateTime.now();
        logger.println("[" + dtf.format(now) + "] [Корабель " + name + "]: " + message);

        logger.flush(); // Переконуємося, що дані записані негайно
    }

    /**
     * Запускає двигун корабля.
     */
    public void startEngine() {
        engine.start();
        log("Двигун запущено.");
    }

    /**
     * Зупиняє двигун корабля.
     */
    public void stopEngine() {
        engine.stop();
        setSpeed(0); // Корабель зупиняється, коли двигун вимкнено
        log("Двигун зупинено.");
    }

    /**
     * Встановлює швидкість корабля.
     * @param speedKnots Нова швидкість у вузлах.
     */
    public void setSpeed(int speedKnots) {
        if (!engine.isStarted() && speedKnots > 0) {
            log("Неможливо встановити швидкість. Двигун не запущено.");
        } else if (isAnchorDown) {
            log("Неможливо встановити швидкість. Якір опущено.");
        } else {
            this.currentSpeedKnots = speedKnots;
            log("Швидкість встановлено на " + speedKnots + " вузлів.");
        }
    }

    /**
     * Завантажує вантаж у трюм.
     * @param amountTons Кількість вантажу для завантаження.
     */
    public void loadCargo(int amountTons) {
        if (cargoHold.loadCargo(amountTons)) {
            log(amountTons + " тонн вантажу завантажено. Всього: " + cargoHold.getCurrentLoadTons());
        } else {
            log("Не вдалося завантажити вантаж. Недостатньо місця.");
        }
    }

    /**
     * Розвантажує вантаж з трюму.
     * @param amountTons Кількість вантажу для розвантаження.
     */
    public void unloadCargo(int amountTons) {
        if (cargoHold.unloadCargo(amountTons)) {
            log(amountTons + " тонн вантажу розвантажено. Всього: " + cargoHold.getCurrentLoadTons());
        } else {
            log("Не вдалося розвантажити вантаж. Недостатньо вантажу в трюмі.");
        }
    }

    /**
     * Призначає нового капітана на корабель.
     * @param newCaptain Новий об'єкт капітана.
     */
    public void assignCaptain(Captain newCaptain) {
        this.captain = newCaptain;
        log("Призначено нового капітана: " + newCaptain.getName());
    }

    /**
     * Встановлює новий курс для корабля.
     * @param course Пункт призначення або напрямок.
     */
    public void setCourse(String course) {
        if (currentSpeedKnots > 0) {
            log("Встановлюємо курс на: " + course);
        } else {
            log("Неможливо встановити курс. Корабель не рухається.");
        }
    }

    /**
     * Опускає якір.
     */
    public void dropAnchor() {
        if (currentSpeedKnots == 0) {
            this.isAnchorDown = true;
            log("Якір опущено.");
        } else {
            log("Неможливо опустити якір! Корабель рухається.");
        }
    }

    /**
     * Піднімає якір.
     */
    public void raiseAnchor() {
        this.isAnchorDown = false;
        log("Якір піднято.");
    }

    /**
     * Отримує поточну швидкість.
     * @return Поточна швидкість у вузлах.
     */
    public int getCurrentSpeedKnots() {
        return currentSpeedKnots;
    }

    /**
     * Отримує назву корабля.
     * @return Назва корабля.
     */
    public String getName() {
        return name;
    }

    /**
     * Друкує та логує поточний статус корабля.
     */
    public void printStatus() {
        String status = "--- Статус корабля: " + name + " ---\n" +
                "  Капітан: " + captain.getName() + " (" + captain.getExperienceYears() + " років досвіду)\n" +
                "  Двигун: " + (engine.isStarted() ? "ЗАПУЩЕНИЙ" : "ЗУПИНЕНИЙ") + " (" + engine.getPowerHp() + " к.с.)\n" +
                "  Швидкість: " + currentSpeedKnots + " вузлів\n" +
                "  Якір: " + (isAnchorDown ? "ОПУЩЕНИЙ" : "ПІДНЯТИЙ") + "\n" +
                "  Вантаж: " + cargoHold.getCurrentLoadTons() + "/" + cargoHold.getMaxCapacityTons() + " тонн\n" +
                "---------------------------------";
        System.out.println(status);
        log("Звіт про статус згенеровано.");
    }
}