import java.util.Scanner;

public class Prob317 {
    // Constants
    private static final double PI = 3.141592653589793;
    private static final double G = 9.81;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input
        System.out.print("Enter velocity (m/s): ");
        double velocity = scanner.nextDouble();
        System.out.print("Enter height (m): ");
        double height = scanner.nextDouble();

        // Compute parameters
        double add = (velocity * velocity) / (2 * G) + height;
        double factor = -G / (2 * velocity * velocity);
        double result = -0.5 * PI * add * add / factor;

        // Print analytical result
        System.out.printf("Analytical Volume: %.4f m^3\n", result);

        // Numerical integration
        double volume = 0;
        double step = 0.00001;
        double x = 0;
        double lastY = 0;
        double averageSliceVolume = 0.0001;

        while (true) {
            double y = x * x * factor + add;
            if (y <= 0) break;

            double sliceHeight = lastY - y;
            double sliceVolume = PI * x * x * sliceHeight;
            volume += sliceVolume;

            if (sliceVolume > averageSliceVolume) {
                step /= 2;
            } else {
                step *= 2;
            }

            x += step;
            lastY = y;
        }

        System.out.printf("Numerical Integration Volume: %.4f m^3\n", volume);
        scanner.close();
    }
}