package pruebas_independencia;

import java.util.Scanner;

public class AutoCorrelacion {
    
    int M, N=50, i, m1;
    int M2;
    Scanner sc= new Scanner(System.in);
    
    public void datos(){
        
        
         System.out.println("Introduzca Datos");
         System.out.println("Cantidad de saltos que se darán");
         m1 = sc.nextInt();
         
         System.out.println("Posición en la que se encontrará su numero inicial");
         i = sc.nextInt();  
         
         System.out.println("digito del 1-9");
         M = sc.nextInt();
    }
    
    public void datosM(){
        
        //System.out.println("digito del 1-9");
         M2 = sc.nextInt();
    }
    
    public void calcular(){
        int a = 0;
        System.out.println("\n Calcular autoCorr");
        
        
        
        if(a <= N){
            a = i + (M + 1) * m1;
            System.out.println("Verdadero.!" + a);
        }else{
            
            System.out.println("Falso.!, ingrese nuevamente un digito del 1-9");
            
        }
        
        
    }
    
}
