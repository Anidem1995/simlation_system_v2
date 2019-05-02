import variables_aleatorias.aceptacion_rechazo.Gamma;
import archivos.ManejoArchivos;
import variables_aleatorias.transformada_inversa.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Integrador {
    Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("¿Desea ingresar los números manualmente o tomarlos de un archivo?\n1 - Manualmente\n2 - Archivo");
        int tipo = scanner.nextInt();
        List<Double> numeros = (tipo == 1 ? capturarNumeros() : extraerNumeros());
        System.out.println("Seleccione la técnica de generación de variables aleatorias\n" +
                "1 - Transformada inversa\n" +
                "2 - Composición\n" +
                "3 - Aceptación o rechazo");
        int tecnica = scanner.nextInt();
        switch (tecnica) {
            case 1:
                System.out.println("Seleccione la distribución de probabilidad\n" +
                        "1 - Exponencial\n" +
                        "2 - Triangular\n" +
                        "3 - Weibull\n" +
                        "4 - Empírica\n" +
                        "5 - Normal");
                int distribucion = scanner.nextInt();
                switch (distribucion) {
                    case 1:
                        new Exponencial().iterar(numeros);
                        break;
                    case 2:
                        new Triangular().iterar(numeros);
                        break;
                    case 3:
                        new Weibull().iterar(numeros);
                        break;
                    case 4:
                        new Empirica().iterar(numeros);
                        break;
                    case 5:
                        new Normal().iterar(numeros);
                        break;
                    default:
                        System.out.println("El khé, carnal?");
                        break;
                }
                break;
            case 2:
                new variables_aleatorias.composicion.Triangular().iterar(numeros);
                break;
            case 3:
                new Gamma().iterar(numeros);
                break;
            default:
                System.out.println("El khé, carnal?");
                break;
        }
    }

    private List<Double> extraerNumeros() {
        ManejoArchivos manejoArchivos = new ManejoArchivos();
        System.out.println("¿A partir de qué posición desea comenzar?");
        int inicio = scanner.nextInt();
        System.out.println("¿Cuántos números desea?");
        int cantidad = scanner.nextInt();
        return manejoArchivos.extraeLista(inicio, cantidad);
    }

    private List<Double> capturarNumeros() {
        List<Double> numeros = new ArrayList<>();
        System.out.println("¿Cuántos números desea?");
        int cantidad = scanner.nextInt();
        for(int i = 1; i <= cantidad; i++) {
            System.out.println("Capture el número " + i);
            numeros.add(scanner.nextDouble());
        }
        return numeros;
    }
}
