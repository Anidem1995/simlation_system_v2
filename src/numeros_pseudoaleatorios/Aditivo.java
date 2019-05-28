package numeros_pseudoaleatorios;

import archivos.ManejoArchivos;
import numeros_pseudoaleatorios.modelo.Semilla;

public class Aditivo {
    Semilla semilla = new Semilla();

    ManejoArchivos manejoArchivos = new ManejoArchivos();

    public void iterar(int n)  {
        semilla.setXn(93);
        semilla.setM(64);
        semilla.setNumero(5);

        for (int i=0;i<n;i++){

            semilla.adi();
            manejoArchivos.escribir(semilla.getXn());


        }
        manejoArchivos.leerArchivo();
    }
}

