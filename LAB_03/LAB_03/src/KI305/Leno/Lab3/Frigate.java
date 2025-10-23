package KI305.Leno.Lab3;

import java.io.PrintWriter;

/**
 * Клас <code>Frigate</code> реалізує фрегат, тип військового корабля.
 * Він розширює абстрактний клас <code>Ship</code> та реалізує
 * інтерфейс <code>MilitaryVessel</code>.
 *
 * @author Leno
 * @version 1.0
 */
public class Frigate extends Ship implements MilitaryVessel {

    private int cannonCount; // Нове поле, специфічне для фрегата

    /**
     * Конструктор для Фрегата.
     *
     * @param name Назва фрегата.
     * @param engine Об'єкт двигуна корабля.
     * @param cargoHold Об'єкт вантажного трюму корабля.
     * @param captain Об'єкт капітана корабля.
     * @param logger Об'єкт PrintWriter для логування.
     * @param cannonCount Кількість гармат на фрегаті.
     */
    public Frigate(String name, Engine engine, CargoHold cargoHold, Captain captain, PrintWriter logger, int cannonCount) {
        // Виклик конструктора батьківського класу (Ship)
        super(name, engine, cargoHold, captain, logger);
        this.cannonCount = cannonCount;
        log("Фрегат '" + name + "' введено в експлуатацію з " + cannonCount + " гарматами.");
    }

    // --- Реалізація абстрактного методу з Ship ---

    /**
     * Друкує та логує поточний статус Фрегата.
     * Ця реалізація додає інформацію про гармати.
     */
    @Override
    public void printStatus() {
        // Отримуємо дані з батьківського класу через protected гетери
        String status = "--- Статус фрегата: " + getName() + " ---\n" +
                "  Капітан: " + getCaptain().getName() + " (" + getCaptain().getExperienceYears() + " років досвіду)\n" +
                "  Двигун: " + (getEngine().isStarted() ? "ЗАПУЩЕНИЙ" : "ЗУПИНЕНИЙ") + " (" + getEngine().getPowerHp() + " к.с.)\n" +
                "  Швидкість: " + getCurrentSpeedKnots() + " вузлів\n" +
                "  Якір: " + (isAnchorDown() ? "ОПУЩЕНИЙ" : "ПІДНЯТИЙ") + "\n" +
                "  Вантаж: " + getCargoHold().getCurrentLoadTons() + "/" + getCargoHold().getMaxCapacityTons() + " тонн\n" +
                "  ОЗБРОЄННЯ: " + this.cannonCount + " гармат готові\n" + // <-- Нова інформація
                "---------------------------------";
        System.out.println(status);
        log("Звіт про статус фрегата згенеровано.");
    }

    // --- Реалізація методів інтерфейсу MilitaryVessel ---

    /**
     * Здійснює постріл з усіх гармат.
     */
    @Override
    public void fireCannons() {
        if (getCurrentSpeedKnots() > 0) {
            log("Неможливо стріляти! Корабель рухається занадто швидко!");
        } else {
            log("ВЕДЕМО ВОГОНЬ З УСІХ " + this.cannonCount + " ГАРМАТ! БУМ!");
            System.out.println(getName() + " стріляє з усіх " + this.cannonCount + " гармат!");
        }
    }

    /**
     * Встановлює рівень бойової тривоги.
     * @param threatLevel Опис рівня загрози (напр., "Червоний", "Жовтий").
     */
    @Override
    public void raiseAlert(String threatLevel) {
        log("Рівень тривоги встановлено: " + threatLevel.toUpperCase() + ". Всім по бойових місцях!");
        System.out.println("Тривога: " + threatLevel);
    }

    // --- Перевизначення методу (Вимога "коректного функціонування") ---

    /**
     * Перевизначає метод loadCargo, щоб обмежити вантаж на фрегаті.
     * Фрегати надають пріоритет швидкості та боєприпасам, а не вантажу.
     *
     * @param amountTons Кількість вантажу для завантаження.
     */
    @Override
    public void loadCargo(int amountTons) {
        int maxCargoForFrigate = getCargoHold().getMaxCapacityTons() / 4; // Фрегат несе лише 1/4 від макс. трюму

        if (amountTons > maxCargoForFrigate || (getCargoHold().getCurrentLoadTons() + amountTons) > maxCargoForFrigate) {
            log("У завантаженні ВІДМОВЛЕНО. Фрегати не перевозять важкі вантажі. Пріоритет - бойова готовність.");
        } else {
            // Виклик оригінального методу з батьківського класу
            super.loadCargo(amountTons);
        }
    }
}