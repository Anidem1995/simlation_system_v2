package variables_aleatorias.aceptacion_rechazo;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Gamma {
    public Gamma(){}

    public void iterar(List<Double> numeros) {
        boolean aceptado = false;
        Double a, b, c, theta, beta, R1, R2, x;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Valor de theta");
        theta = scanner.nextDouble();
        System.out.println("Valor de beta");
        beta = scanner.nextDouble();
        a = Math.sqrt(2 * beta - 1);
        b = 2 * beta - Math.log(4) + 1 / a;
        do {
            Random random = new Random();
            R1 = numeros.get(random.nextInt(numeros.size()));
            R2 = numeros.get(random.nextInt(numeros.size()));

            x = beta * (R1 * Math.pow(1 - R1, a));
            c = b - Math.log(Math.pow(R1, 2) * R2);

            aceptado = (x > c ? false : true);
        }while(!aceptado);
        if (aceptado) {
            x = x / (beta * theta);
            System.out.println("Valor de la variable aleatoria");
            System.out.println(x);
        }
    }
}
