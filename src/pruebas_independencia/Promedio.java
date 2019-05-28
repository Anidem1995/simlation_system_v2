package pruebas_independencia;

import java.util.ArrayList;

public class Promedio {
        
    double total, media, med, varianza, z, alfa,  Za;
    double num=0, count =0, cantidad = 50;
    double nps[];
       
    public boolean calcular(ArrayList<Double>nums){

        med = 0.5;
        varianza=Math.sqrt((double)1/12);
        alfa = 0.05;
        Za=alfa;
        
        for(int x=0; x < nums.size()-1; x++) {
            
            num = nums.get(x);
            count = count + num;
        }
         System.out.println("Total " + count);
        total = count/10000;
        System.out.println("La suma es de " + total);
        media = total/cantidad;
        System.out.println("La media es " + media);
        z=(media-med)*Math.sqrt(cantidad)/varianza;
        
        System.out.println("El valor de Z0 es " + z);
        
        return Math.abs(z)<Za;
    }
}
