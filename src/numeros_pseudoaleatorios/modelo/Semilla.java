package numeros_pseudoaleatorios.modelo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Semilla {
    public double valor,a,xn,m,c,valor2,numero,z,k;

    private String valor_texto;

    public Semilla() { }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getZ() {
        return z;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getValor2() {
        return valor2;
    }

    public void setValor2(double valor2) {
        this.valor2 = valor2;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getXn() {
        return xn;
    }

    public void setXn(double xn) { this.xn = xn; }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void elevarCuadrado() { this.valor = Math.pow(this.getValor(), 2); }

    public  void mult(){
        this.xn =(this.getXn()*this.getA())%this.getM();
    }

    public void adi(){
        for (int i=0;i<numero;i++){
            this.xn= this.z=(this.getXn()+i);
            for (int j=0;j<numero;j++){
                this.xn=((this.getXn()+this.getZ())%this.getM());
            }
        }
    }

    public void veri(){
        int cuenta=0;
       for (int i=0;i<this.getValor();i++){
               if (i==this.getValor()){
                   cuenta++;
                   System.out.println("el numero  " + i + "se repite  " + cuenta);
           }
       }
    }

    public void mixto(){
        this.xn=((this.getXn() * this.getA())+this.getC())%this.getM();
    }

    public void multReferencia(){
        this.valor = this.getValor() * this.getK();
    }

    public void mult2Semillas(){
        numero = this.getValor2();
        this.valor = this.getValor() * numero;
    }


    public void agregarCeros() {
        NumberFormat nf = new DecimalFormat("##.###");
        this.valor_texto = nf.format(valor) + "";
        StringBuffer sb = new StringBuffer(8);

        switch (valor_texto.length()) {
            case 1:
                sb.append("0000000");
                break;
            case 2:
                sb.append("000000");
                break;
            case 3:
                sb.append("00000");
                break;
            case 4:
                sb.append("0000");
                break;
            case 5:
                sb.append("000");
                break;
            case 6:
                sb.append("00");
                break;
            case 7:
                sb.append("0");
                break;
            default:
                break;
        }
        this.valor_texto = sb.toString() + valor_texto;
    }

    public void extraerCentrales() {
        String centrales = valor_texto.substring(2, 6);
        this.valor = Double.parseDouble(centrales);
    }
}
