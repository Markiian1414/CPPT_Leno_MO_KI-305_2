import java.io.*;
import java.util.*;

/**
 * Клас Lab1Leno305 створює квадратну матрицю заданого розміру
 * і заповнює її символами користувача та фоном.
 * <p>
 * Рамка навколо внутрішнього квадрата збільшується кожні 6 одиниць розміру матриці.
 * Внутрішній квадрат займає центральну частину матриці.
 * Результат виводиться на екран та записується у файл MyFile.txt.
 * </p>
 */
public class Lab1Leno305 {

    /**
     * Точка входу в програму.
     * <p>
     * Програма запитує розмір квадратної матриці та символ-заповнювач.
     * Далі будує матрицю, виводить її на екран і зберігає у файл.
     * </p>
     *
     * @param args Аргументи командного рядка (не використовуються)
     * @throws FileNotFoundException якщо не вдасться створити файл MyFile.txt
     */
    public static void main(String[] args) throws FileNotFoundException {
        int n;          // Розмір квадратної матриці
        char filler;    // Символ для заповнення внутрішнього квадрата

        Scanner in = new Scanner(System.in);
        File dataFile = new File("MyFile.txt");
        PrintWriter fout = new PrintWriter(dataFile);

        // Зчитування розміру матриці
        System.out.print("Введіть розмір квадратної матриці: ");
        n = in.nextInt();
        in.nextLine();

        // Зчитування символу-заповнювача
        System.out.print("Введіть символ-заповнювач: ");
        String input = in.nextLine();

        // Перевірка коректності введення символу
        if (input.length() != 1) {
            System.out.println("Потрібно ввести рівно один символ!");
            return;
        }
        filler = input.charAt(0);

        char[][] arr = new char[n][n]; // Створення матриці

        // Обчислення товщини рамки навколо внутрішнього квадрата
        int border = (n - 1) / 6 + 1;

        // Заповнення матриці
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Якщо елемент у рамці — ставимо фон, інакше — символ користувача
                if (i < border || i >= n - border || j < border || j >= n - border) {
                    arr[i][j] = ' '; // фон
                } else {
                    arr[i][j] = filler; // внутрішній квадрат
                }
                System.out.print(arr[i][j]);
                fout.print(arr[i][j]);
            }
            System.out.println();
            fout.println();
        }

        fout.flush();
        fout.close();
    }
}
