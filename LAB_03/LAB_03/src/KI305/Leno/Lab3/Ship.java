package KI305.Leno.Lab3;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Абстрактний клас <code>Ship</code> реалізує загальну сутність "корабель".
 * Він слугує базою для конкретних типів кораблів.
 *
 * @author Leno
 * @version 2.0 // Оновлено версію
 */
public abstract class Ship { // <-- Клас тепер АБСТРАКТНИЙ

    // --- Поля ---
    private String name;
    private int currentSpeedKnots;
    private boolean isAnchorDown;

    // --- Поля-компоненти ---
    private Engine engine;
    private CargoHold cargoHold;
    private Captain captain;

    // --- Поля для логування ---
    private PrintWriter logger;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    // --- Конструктори ---
    // Конструктори залишаються (вони будуть викликані через super() у нащадках)

    /**
     * Конструктор за замовчуванням.
     * @param logger Об'єкт PrintWriter для логування.
     */
    public Ship(PrintWriter logger) {
        this.name = "Unnamed";
        this.engine = new Engine(1000);
        this.cargoHold = new CargoHold(500);
        this.captain = new Captain("No Captain", 0);
        this.currentSpeedKnots = 0;
        this.isAnchorDown = true;
        this.logger = logger;
        log("Корабель 'Unnamed' створено з компонентами за замовчуванням.");
    }

    /**
     * Перевантажений конструктор.
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

    // --- Абстрактні методи ---

    /**
     * Друкує та логує поточний статус корабля.
     * Має бути реалізований усіма підкласами.
     */
    public abstract void printStatus(); // <-- Метод тепер АБСТРАКТНИЙ


    // --- Конкретні методи ---
    // (Всі інші методи залишаються такими ж, як у Lab2)

    /**
     * Допоміжний метод для логування.
     * @param message Повідомлення для логу.
     */
    protected void log(String message) { // (Змінено на protected, щоб Frigate міг логувати)
        LocalDateTime now = LocalDateTime.now();
        logger.println("[" + dtf.format(now) + "] [Корабель " + name + "]: " + message);
        logger.flush();
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
        setSpeed(0);
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

    // --- Гетери для підкласів ---
    // (Потрібні, щоб Frigate міг отримати доступ до полів для printStatus)

    /** @return Об'єкт двигуна корабля. */
    protected Engine getEngine() { return engine; }
    /** @return Об'єкт вантажного трюму корабля. */
    protected CargoHold getCargoHold() { return cargoHold; }
    /** @return Об'єкт капітана корабля. */
    protected Captain getCaptain() { return captain; }
    /** @return Статус якоря. */
    protected boolean isAnchorDown() { return isAnchorDown; }
}