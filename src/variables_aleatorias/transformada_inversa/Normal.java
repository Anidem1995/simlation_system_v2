package variables_aleatorias.transformada_inversa;

import java.util.List;

public class Normal {
    public void iterar(List<Double> numeros) {
        Double miu = calcularMiu(numeros);
        Double sigma = calcularDesviacion(numeros, miu);
        System.out.println("Valor de la media");
        System.out.println(miu);
        System.out.println("Valor de la desviación estándar");
        System.out.println(sigma);
        for(Double numero : numeros) {
            System.out.println("Calculando el valor de la variable aleatoria para " + numero);
            Double x = numero * sigma - miu + sigma;
            System.out.println(x);
        }
    }

    private Double calcularMiu(List<Double> numeros) {
        return numeros.stream().mapToDouble(value -> value).sum() / numeros.size();
    }

    private Double calcularDesviacion(List<Double> numeros, Double miu) {
        return numeros.stream().mapToDouble(value -> Math.pow(value - miu, 2)).sum() / numeros.size();
    }
}
