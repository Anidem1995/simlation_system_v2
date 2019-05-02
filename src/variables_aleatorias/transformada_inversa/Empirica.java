package variables_aleatorias.transformada_inversa;

import model.Intervalo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Empirica {
    public void iterar(List<Double> numeros) {
        List<Intervalo> intervalos = new ArrayList<>();

        int n = numeros.size() / 10;
        int FE = numeros.size() / n;

        Collections.sort(numeros);
        Double diferencia = numeros.get(numeros.size() - 1) - numeros.get(0);
        Double rangoIntervalo = diferencia / n;
        Double inicio = numeros.get(0);
        for(int i = 0 ; i < n ; i++) {
            intervalos.add(new Intervalo(inicio, inicio + rangoIntervalo));
            inicio = inicio + rangoIntervalo;
        }

        cuentaApariciones(numeros, intervalos);
        estableceFrecuencias(intervalos, numeros);
        DecimalFormat df = new DecimalFormat("#.####");
        System.out.println("Tabla de frecuencias\n" +
                "   Intervalo\tFrecuencia\t  Frecuencia\tFrecuencia\n" +
                "\t\t\t\t\t\t\t   relativa\t    absoluta");
        for(Intervalo intervalo : intervalos)
            System.out.println("[" + df.format(intervalo.getInicio()) + "-" + df.format(intervalo.getFin()) + "]\t    "
            + intervalo.getApariciones() + "\t\t" + df.format(intervalo.getFrecuencia()) + "\t\t" + df.format(intervalo.getFrec_acumulada()));
    }

    private void cuentaApariciones(List<Double> numeros, List<Intervalo> intervalos) {
        for(Double n : numeros) {
            for(Intervalo intervalo : intervalos)
                intervalo.setApariciones(n >=  intervalo.getInicio() && n <= intervalo.getFin() ? intervalo.getApariciones() + 1 : intervalo.getApariciones());
        }
    }

    private void estableceFrecuencias(List<Intervalo> intervalos, List<Double> numeros) {
        AtomicReference<Double> frecuencia_acumulada = new AtomicReference<>(0.0);
        intervalos.stream().forEach(intervalo -> {
            intervalo.setFrecuencia(intervalo.getApariciones() / numeros.size());
            intervalo.setFrec_acumulada(frecuencia_acumulada.accumulateAndGet(intervalo.getFrecuencia(), (x, y) -> x + y));
        });
    }

    private void calculaEquis(Double valor) {

    }
    }
