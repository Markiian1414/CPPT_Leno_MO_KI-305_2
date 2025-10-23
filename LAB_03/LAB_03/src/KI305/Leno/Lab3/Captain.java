package KI305.Leno.Lab3;

/**
 * Клас, що представляє капітана корабля.
 * Зберігає ім'я капітана та його досвід.
 */
public class Captain {
    private String name;
    private int experienceYears;

    /**
     * Конструктор для Капітана.
     * @param name Ім'я капітана.
     * @param experienceYears Кількість років досвіду капітана.
     */
    public Captain(String name, int experienceYears) {
        this.name = name;
        this.experienceYears = experienceYears;
    }

    /**
     * Отримує ім'я капітана.
     * @return Ім'я капітана.
     */
    public String getName() {
        return name;
    }

    /**
     * Отримує досвід капітана.
     * @return Кількість років досвіду.
     */
    public int getExperienceYears() {
        return experienceYears;
    }

    /**
     * Встановлює нове ім'я для капітана.
     * @param name Нове ім'я.
     */
    public void setName(String name) {
        this.name = name;
    }
}