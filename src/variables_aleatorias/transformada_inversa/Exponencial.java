package variables_aleatorias.transformada_inversa;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Exponencial {
    public Exponencial() {
    }

    public void iterar(List<Double> numeros) {
        Double lambda = calcularLambda(numeros);
        for(Double numero : numeros) {
            System.out.println("Valor de la variable aleatoria usando " + numero);
            Double x = -1 / lambda * Math.log(numero);
            System.out.println(x);
        }
    }

    @NotNull
    private Double calcularLambda(List<Double> numeros) {
        return numeros.stream().mapToDouble(value -> value).sum() / numeros.size();
    }
}
