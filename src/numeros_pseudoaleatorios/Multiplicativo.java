package numeros_pseudoaleatorios;

import archivos.ManejoArchivos;
import numeros_pseudoaleatorios.modelo.Semilla;

import java.util.Scanner;


public class Multiplicativo {
    Semilla semilla = new Semilla();

    ManejoArchivos manejoArchivos = new ManejoArchivos();

    public void iterar()  {
        System.out.println("cuantos numeros quieres? ");
        Scanner b=new Scanner(System.in);
        int n = b.nextInt();
        System.out.println("agrega el valor de Xn ");
        Scanner a=new Scanner(System.in);
        int s = a.nextInt();
        System.out.println("agrega el valor de a ");
        Scanner a1=new Scanner(System.in);
        int A = a1.nextInt();
        System.out.println("agrega el valor de M ");
        Scanner a2=new Scanner(System.in);
        int M = a2.nextInt();



        semilla.setXn(s);
        semilla.setA(A);
        semilla.setM(M);
        semilla.setNumero(n);

        for (int i=0;i<n;i++){
            semilla.mult();
            manejoArchivos.escribir(semilla.getXn());

        }
    }
}