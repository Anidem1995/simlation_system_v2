package numeros_pseudoaleatorios;

import archivos.ManejoArchivos;
import numeros_pseudoaleatorios.modelo.Semilla;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VonNeumann {
    int n;


    Semilla semilla = new Semilla();
    ManejoArchivos manejoArchivos = new ManejoArchivos();

    public VonNeumann(){}

    public int getN() {
        return n;
    }

    public void iterar() {
        System.out.println("cuantos numeros quieres? ");
        Scanner b=new Scanner(System.in);
        this.n = b.nextInt();
        System.out.println("agrega el valor de la semilla ");
        Scanner a=new Scanner(System.in);
        int s = a.nextInt();
        semilla.setValor(s);
        semilla.setNumero(n);
        for(int i = 0; i < this.n; i++) {
            semilla.elevarCuadrado();
            semilla.agregarCeros();
            semilla.extraerCentrales();
            manejoArchivos.escribir(semilla.getValor());
        }
        //manejoArchivos.leerArchivo();
    }

    public List<Double> numbersToList() {
        List<Double> numbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Valor de la semilla");
        int seed_value = scanner.nextInt();
        Semilla semilla = new Semilla();
        semilla.setValor(seed_value);
        semilla.setNumero(100);
        for(int i = 0; i < 100; i++) {
            semilla.elevarCuadrado();
            semilla.agregarCeros();
            semilla.extraerCentrales();
            numbers.add(semilla.getValor());
        }
        return numbers;
    }
}
