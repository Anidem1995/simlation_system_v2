package variables_aleatorias.transformada_inversa;

import java.util.List;
import java.util.Scanner;

public class Weibull {

    public Weibull() {}

    public void iterar(List<Double> numeros) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Defina el parámetro de forma\nSe recomienda un valor entre 0 y 1");
        Double beta = scanner.nextDouble();
        System.out.println("Defina el parámetro de escala\nSe recomienda un valor entre 0 y 1");
        Double alfa = scanner.nextDouble();
        for(Double numero : numeros) {
            System.out.println("Valor de la variable aleatoria usando " + numero);
            Double x = alfa * Math.pow((-1 * Math.log(numero)), 1 / beta);
            System.out.println(x);
        }
    }
}
