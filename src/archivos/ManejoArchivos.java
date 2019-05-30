package archivos;

import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ManejoArchivos {
    RandomAccessFile archivo;

    private void abrirArchivo() {
        try {
            archivo = new RandomAccessFile("almacen", "rw");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escribir(double numero) {
        try {
            abrirArchivo();
            archivo.seek(archivo.length());
            archivo.writeDouble(numero);
            archivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Double> obtenerLista() {
        ArrayList<Double> lista = new ArrayList<>();
        try {
            abrirArchivo();
            while (archivo.getFilePointer() < archivo.length())
                lista.add(archivo.readDouble());
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    public void leerArchivo() {
        try {
            abrirArchivo();
            while (archivo.getFilePointer() < archivo.length())
                System.out.println(archivo.readDouble());
            archivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limpiarArchivo() {
        try {
            abrirArchivo();
            archivo.seek(0);
            while (archivo.getFilePointer() < archivo.length())
                archivo.writeChars("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cuentaRepeticiones() {
        int repeticiones = 0;
        Double n;
        RandomAccessFile archivo_auxiliar;
        try {
            abrirArchivo();
            archivo_auxiliar = new RandomAccessFile("almacen", "rw");

            while(archivo_auxiliar.getFilePointer() < archivo_auxiliar.length()) {
                n = archivo_auxiliar.readDouble();
                archivo.seek(archivo_auxiliar.getFilePointer());
                while (archivo.getFilePointer() < archivo.length())
                    repeticiones += (n == archivo.readDouble()) ? 1 : 0;
                System.out.println("El número " + n + " se repite " + repeticiones + " veces");
            }
            archivo.close();
            archivo_auxiliar.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarArchivo() {
        try {
            abrirArchivo();
            for (int i = 0; i < archivo.length(); i++) {
                archivo.writeUTF("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Double> extraeLista(int inicio, int cantidad) {
        List<Double> numeros = new ArrayList<>();
        try{
            abrirArchivo();
            archivo.seek(inicio * 8);
            for(int i = 0; i < cantidad; i++) {
                NumberFormat nf = new DecimalFormat("##.###");
                Double numero = archivo.readDouble();
                String n_texto = nf.format(numero).toString();
                numero = (n_texto.length() == 4 ? numero / 10000 : numero / 1000);
                numeros.add(numero);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return numeros;
    }
}
