import algoritmos_geneticos.CustomGeneticAlgorithm;
import algoritmos_geneticos.TravellingSalesman;
import numeros_pseudoaleatorios.Mixto;
import numeros_pseudoaleatorios.VonNeumann;
import pruebas_independencia.Corridas;
import pruebas_independencia.KolmogorovSmirnov;
import variables_aleatorias.aceptacion_rechazo.Gamma;
import archivos.ManejoArchivos;
import variables_aleatorias.transformada_inversa.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Integrador {
    private Scanner scanner = new Scanner(System.in);
    List<Double> random_numbers = new ArrayList<>();

    public void start() {
        System.out.println("Los números pseudoaleatorios serán generados usando el método congruencial mixto\n" +
                "Las pruebas de independencia estadística y distribución uniforme serán realizadas con los métodos de corridas y Kolmogorov-Smirnov");
        System.out.println("¿Desea generar nuevos números aleatorios o cargarlos desde un archivo?\n1 - Nuevos números\n2 - Desde archivo");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                //VonNeumann vonNeumann = new VonNeumann();
                //vonNeumann.iterar();
                Mixto mixto = new Mixto();
                mixto.setData();
                random_numbers = extraerNumeros();
                break;
            case 2:
                 random_numbers = extraerNumeros();
                break;
                default:
                    break;
        }
        System.out.println("Aplicando prueba de Kolmogorov-Smirnov para la comprobación de distribución uniforme");
        KolmogorovSmirnov kolmogorovSmirnov = new KolmogorovSmirnov();
        kolmogorovSmirnov.calcular(random_numbers);
        System.out.println("Aplicando prueba de corridas para la comprobación de independencia estadística");
        Corridas corridas = new Corridas();
        corridas.calcularCorridas(random_numbers);
        System.out.println("Iniciando");
        CustomGeneticAlgorithm customGeneticAlgorithm = new CustomGeneticAlgorithm();
        customGeneticAlgorithm.start(random_numbers);
    }

    private List<Double> extraerNumeros() {
        ManejoArchivos manejoArchivos = new ManejoArchivos();
        System.out.println("¿Cuántos números desea?");
        int cantidad = scanner.nextInt();
        return manejoArchivos.extraeLista(0, cantidad);
    }
}
