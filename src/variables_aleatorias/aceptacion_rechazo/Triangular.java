package variables_aleatorias.aceptacion_rechazo;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Triangular {
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
        boolean aceptado = false;

        do{
            Random random = new Random();
            Double R1 = numeros.get(random.nextInt(numeros.size()));
            Double x = a + ((b - d) * R1);
            Double R2 = numeros.get(random.nextInt(numeros.size()));

            Double fx = 0.0;
            if(a <= x && x <= d)
                fx = (c / d) * x;
            else fx = (c / d) * (b - x);

            System.out.println("Valor de R2: " + R2);
            System.out.printf("Valor de x: " + x);
            if(R2 > fx)
                System.out.println("x es rechazado");
            else {
                System.out.println("x es aceptado");
                aceptado = true;
            }
        }while(!aceptado);
    }
}
