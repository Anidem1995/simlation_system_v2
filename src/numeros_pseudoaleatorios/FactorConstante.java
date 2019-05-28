package numeros_pseudoaleatorios;
import archivos.ManejoArchivos;
import numeros_pseudoaleatorios.modelo.Semilla;

import java.util.Scanner;

public class FactorConstante {
    Semilla semilla = new Semilla();
    ManejoArchivos manejoArchivos = new ManejoArchivos();

    public void iterar () {
        System.out.println("cuantos numeros quieres? ");
        Scanner v=new Scanner(System.in);
        int n = v.nextInt();
        System.out.println("agrega el valor de la semilla ");
        Scanner a=new Scanner(System.in);
        int s = a.nextInt();
        System.out.println("agrega el valor de la semilla ");
        Scanner b=new Scanner(System.in);
        int k = b.nextInt();

        semilla.setValor(s);
        semilla.setK(k);
        semilla.setNumero(n);
        for(int i = 0; i < n; i++) {
            semilla.multReferencia();
            semilla.agregarCeros();
            semilla.extraerCentrales();
            manejoArchivos.escribir(semilla.getValor());
        }
        manejoArchivos.leerArchivo();
    }
}
