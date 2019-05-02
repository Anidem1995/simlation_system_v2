package variables_aleatorias.transformada_inversa;

import java.util.List;
import java.util.Scanner;

public class Triangular {
    public void Triangular() {}

    public void iterar(List<Double> numeros) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Valor de la constante a");
        Double a = scanner.nextDouble();
        System.out.println("Valor de la constante b");
        Double b = scanner.nextDouble();
        System.out.println("Valor de la constante c");
        Double c = scanner.nextDouble();
        System.out.println("Valor de la moda");
        Double moda = scanner.nextDouble();

        for(Double numero : numeros) {
            Double x = 0.0;
            if(numero <= a && numero <= c / moda * moda)
                x = f1(moda, numero, c, a);
            else if (numero < c / moda * moda && numero <= 1)
                x = f2(moda, numero, c, b);
            System.out.println("Valor de la variable aleatoria con " + numero);
            System.out.println(x);
        }
    }

    public Double f1(Double moda, Double numero, Double c, Double a) {
        return Math.sqrt(((2 * moda * numero) / c) + Math.pow(a, 2));
    }

    public Double f2(Double moda, Double numero, Double c, Double b) {
        return Math.sqrt(((2 * moda * numero) / c) * numero) - b;
    }
}
