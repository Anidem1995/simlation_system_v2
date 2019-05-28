package numeros_pseudoaleatorios.modelo;

import archivos.ManejoArchivos;
import numeros_pseudoaleatorios.VonNeumann;

import java.util.Scanner;

public class corridas {
    double zo,o,w,r;
    Semilla semilla = new Semilla();
    ManejoArchivos manejoArchivos = new ManejoArchivos();
    VonNeumann vonNeumann=new VonNeumann();


    public corridas(double zo,double o,double w,double r){
        this.zo=zo;
        this.o=o;
        this.w=w;
        this.r=r;

    }
    public corridas(){}

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getZo() {
        return zo;
    }

    public void setZo(double zo) {
        this.zo = zo;
    }

    public double getO() {
        return o;
    }

    public void setO(double o) {
        this.o = o;
    }
    public void pruebacorridas(){
        System.out.println("quieres saber la independencia? ");
        Scanner b1=new Scanner(System.in);
        int n1 = b1.nextInt();
        if (n1==1){
            vonNeumann.getN();

            this.zo=(2*vonNeumann.getN())/3;
            this.o=((16*vonNeumann.getN())-29)/90;
            this.r=(zo-(vonNeumann.getN()/2))/Math.sqrt(this.getO());
            System.out.println(getR());}


    }
}
