package numeros_pseudoaleatorios;

import archivos.ManejoArchivos;
import numeros_pseudoaleatorios.modelo.Semilla;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mixto {
    private Semilla semilla = new Semilla();
    private ManejoArchivos manejoArchivos = new ManejoArchivos();
    private Double a ,c, m, x;
    private int iteraciones;
    private Scanner scanner = new Scanner(System.in);

    /*public void iterar()  {
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
        System.out.println("agrega el valor de c ");
        Scanner a3=new Scanner(System.in);
        int C = a3.nextInt();


        semilla.setXn(s);
        semilla.setA(A);
        semilla.setM(M);
        semilla.setC(C);
        semilla.setNumero(n);

        for (int i=0;i<n;i++){
            semilla.mixto();
            manejoArchivos.escribir(semilla.getXn());
        }
        manejoArchivos.leerArchivo();
    }*/
    
    public void setData() {
        System.out.println("Introduzca Datos");

        System.out.println("Ingrese la cantidad de numeros pseudoaleatorios deseados");
        iteraciones = scanner.nextInt();

        System.out.println("introduzca un número de 3 digitos que sea mayor a 100 para la semilla (Xn)");
        x = scanner.nextDouble();

        System.out.println("introduzca un número que sea mayor a 0 para el multiplicador (a)");
        a = scanner.nextDouble();

        System.out.println("introduzca un número que sea mayor a 0 para la constante aditiva (c)");
        c = scanner.nextDouble();

        System.out.println("introduzca un número de 4 digitos mayor a 1,000 para el modulo (el modulo debe ser m> Xn, m>a, m>c)");
        m = scanner.nextDouble();

        generar();
    }

    private void generar()
    {
        Double xn = 0.0, n = 0.0;
        boolean indicador = true;
        List<Double> numeros = new ArrayList<>();

        for(int i=0; i<iteraciones; i++)
        {
            xn = (a*x + c) % m;
            x=xn;
            n++;

            numeros.add(xn);
            manejoArchivos.escribir(xn);

            if(numeros.size() != 1)
            {
                for(int k=0; k<(numeros.size()-1); k++)
                {
                    if(numeros.get(k)==xn)
                    {
                        System.out.println("Con los datos datos brindados solo es posible obtener" +(n-1)+"  iteraciones");

                        indicador=false;
                        break;
                    }
                }
            }
            if (indicador == false)
            {
                break;
            }
        }
    }
}