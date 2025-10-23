package KI305.Leno.Lab2;

/**
 * Клас, що представляє двигун корабля.
 * Зберігає потужність двигуна та його поточний стан (запущений/зупинений).
 */
public class Engine {
    private int powerHp;
    private boolean isStarted;

    /**
     * Конструктор для Двигуна.
     * @param powerHp Потужність двигуна в кінських силах.
     */
    public Engine(int powerHp) {
        this.powerHp = powerHp;
        this.isStarted = false;
    }

    /**
     * Запускає двигун.
     */
    public void start() {
        this.isStarted = true;
    }

    /**
     * Зупиняє двигун.
     */
    public void stop() {
        this.isStarted = false;
    }

    /**
     * Перевіряє, чи запущений двигун.
     * @return true, якщо двигун запущений, false в іншому випадку.
     */
    public boolean isStarted() {
        return isStarted;
    }

    /**
     * Отримує потужність двигуна.
     * @return Потужність в кінських силах.
     */
    public int getPowerHp() {
        return powerHp;
    }
}