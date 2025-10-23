package KI305.Leno.Lab3;

/**
 * Інтерфейс <code>MilitaryVessel</code> визначає методи для бойових кораблів.
 *
 * @author Leno
 * @version 1.0
 */
public interface MilitaryVessel {

    /**
     * Здійснює постріл з головних гармат корабля.
     */
    void fireCannons();

    /**
     * Встановлює рівень бойової тривоги.
     * @param threatLevel Опис рівня загрози (напр., "Червоний", "Жовтий").
     */
    void raiseAlert(String threatLevel);
}