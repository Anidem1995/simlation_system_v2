package numeros_pseudoaleatorios;

import archivos.ManejoArchivos;
import numeros_pseudoaleatorios.modelo.Semilla;

import java.util.Scanner;

public class productoCentral {

    Semilla semilla = new Semilla();
    ManejoArchivos manejoArchivos = new ManejoArchivos();

    public void iterar() {
        System.out.println("cuantos numeros quieres? ");
        Scanner v=new Scanner(System.in);
        int n = v.nextInt();
        System.out.println("agrega el valor de la semilla 1");
        Scanner a=new Scanner(System.in);
        int s = a.nextInt();
        System.out.println("agrega el valor de la semilla 2");
        Scanner b=new Scanner(System.in);
        int s1 = a.nextInt();
        semilla.setValor(s);
        semilla.setValor2(s1);
        semilla.setNumero(n);
        for(int i = 0; i < n; i++) {
            semilla.mult2Semillas();
            semilla.agregarCeros();
            semilla.extraerCentrales();
            manejoArchivos.escribir(semilla.getValor());
        }
        manejoArchivos.leerArchivo();
    }
}
