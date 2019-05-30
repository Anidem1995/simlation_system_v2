package pruebas_independencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Huecos {
    Scanner a_teclado = new Scanner(System.in);
    int menu;
    String variable;
    ArrayList<Integer>numeroLeidos = new ArrayList<>();
    ArrayList<String>numeroLeidos2 = new ArrayList<>();
    String archivo1;

  Huecos(ArrayList<Double>nums) {
      for(int i=0;i<nums.size();i++)
          numeroLeidos.add(nums.get(i).intValue());

      System.out.println("Ingrese un numero para comparar");
      variable=a_teclado.next();
      comparar(variable);
  }
  
  

public void comparar(String a){
    int v=0;
    int v2=0;
    int v3=0;
    int numcomparar;
    int numerofinal;
    String numero;
    char z;
    for(int i=0;i<numeroLeidos.size();i++){
        
        numero=Integer.toString(numeroLeidos.get(i)); 
        if(a.equals(String.valueOf(numero.charAt(numero.length()-1))))
        {

            v++;
            if(v2==0)
            {v2=i;
            v3=i;
            }
            else {
                
                v2=i-v2-1;
                System.out.println("Hueco: "+ (v-1) +" Posicion "+(v3+1) +" Longitud: " +v2);  
                v2=i;
                v3=i;
            }
        }
        else {
        }
    }
}    
}
