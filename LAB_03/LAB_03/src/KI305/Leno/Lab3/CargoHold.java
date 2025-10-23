package KI305.Leno.Lab3;

/**
 * Клас, що представляє вантажний трюм корабля.
 * Відстежує місткість та поточне завантаження.
 */
public class CargoHold {
    private int maxCapacityTons;
    private int currentLoadTons;

    /**
     * Конструктор для ВантажногоТрюму.
     * @param maxCapacityTons Максимальна місткість трюму в тоннах.
     */
    public CargoHold(int maxCapacityTons) {
        this.maxCapacityTons = maxCapacityTons;
        this.currentLoadTons = 0;
    }

    /**
     * Завантажує вантаж у трюм.
     * @param amountTons Кількість вантажу для завантаження.
     * @return true, якщо завантаження успішне, false, якщо перевищено місткість.
     */
    public boolean loadCargo(int amountTons) {
        if (currentLoadTons + amountTons <= maxCapacityTons) {
            currentLoadTons += amountTons;
            return true;
        }
        return false;
    }

    /**
     * Розвантажує вантаж з трюму.
     * @param amountTons Кількість вантажу для розвантаження.
     * @return true, якщо розвантаження успішне, false, якщо намагаєтеся розвантажити більше, ніж є.
     */
    public boolean unloadCargo(int amountTons) {
        if (currentLoadTons - amountTons >= 0) {
            currentLoadTons -= amountTons;
            return true;
        }
        return false;
    }

    /**
     * Отримує поточне завантаження.
     * @return Поточне завантаження вантажу в тоннах.
     */
    public int getCurrentLoadTons() {
        return currentLoadTons;
    }

    /**
     * Отримує максимальну місткість.
     * @return Максимальна місткість вантажу в тоннах.
     */
    public int getMaxCapacityTons() {
        return maxCapacityTons;
    }
}