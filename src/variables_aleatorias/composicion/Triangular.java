package variables_aleatorias.composicion;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Triangular {
    public Triangular() {}

    public void iterar(List<Double> numeros) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Valor de a");
        Double a = scanner.nextDouble();
        System.out.println("Valor de b");
        Double b = scanner.nextDouble();
        System.out.println("Valor de c");
        Double c = scanner.nextDouble();
        System.out.println("Valor de la moda");
        Double d = scanner.nextDouble();
        boolean continua = true;

        do {
            Random random = new Random();
            Double R1 = numeros.get(random.nextInt(numeros.size()));
            Double R2 = numeros.get(random.nextInt(numeros.size()));
            int area = (R1 < ((d - a) * c) / 2  ? 1 : 2);
            Double x = 0.0;
            System.out.println("Valor de R1: " + R1 + "\nValor de R2: " + R2);
            if (R1 < area)
                x = f1(d, R2, c, a);
            else x = f2(d, R2, c, b);
            System.out.println("Valor de X con R = " + R2);
            System.out.println("¿Continuar iterando?\n1-Sí\n2-No");
            continua = (scanner.nextInt() == 1);
        }while(continua);
    }

    public Double f1(Double moda, Double numero, Double c, Double a) {
        return Math.sqrt(((2 * moda * numero) / c) + Math.pow(a, 2));
    }

    public Double f2(Double moda, Double numero, Double c, Double b) {
        return Math.sqrt(((2 * moda * numero) / c) * numero) - b;
    }
}
