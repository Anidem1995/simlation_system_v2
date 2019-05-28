package pruebas_independencia;

import java.util.ArrayList;

public class Poker {
 ArrayList<Integer>numeroLeidos = new ArrayList<>();
    
    Poker(ArrayList<Double>nums){
       
          numeroLeidos.add(2244);
        for(int i=0;i<nums.size();i++){
          numeroLeidos.add(nums.get(i).intValue());
          
         GetPoker(numeroLeidos.get(i));
      }
        
        
        
    }
    int par1,doblepar1,tercia1,poker1;
    
    public void GetPoker(int n){
        String numero=Integer.toString(n);
        int posauxiliar, doblepar=0, tercia=0,poker=0;
        int[] contador=new int[numero.length()];
      
        for(int i=0;i<numero.length();i++){
            contador[i]=1;
            for(int k=0;k<numero.length();k++)
            if(numero.charAt(i)==numero.charAt(k))
                if(i!=k)
            {
              contador[i]++;  
            }
            
            
        }
        
        for(int i=0;i<contador.length;i++){
            if(contador[i]==2)
            {
              doblepar++;  
            }   
            else{
                if(contador[i]==3)
                {
                  tercia++;  
                }
                else 
                    if(contador[i]==4){
                        poker++;
                    }
               
            }
        }
        
        if(poker>0)
        {
            System.out.println("El numero: "+ numero+ " es un Poker");
            poker1++;
        }
        else
            if(tercia>0)
            {
                System.out.println("El numero: "+ numero+ " es una Tercia");
                tercia1++;
            }
               
                else
                    if(doblepar>2){
                      System.out.println("El numero: "+ numero+ " es un Doble Par");
                      doblepar1++;
                    }
                        
                        else
                            if(doblepar>0){
                               System.out.println("El numero: "+ numero+ " es un Par");  
                               par1++;
                            }
                           
        
        
        
        
        
        
    }
    
    void imprimir(){
        System.out.println("Hay "+ poker1+ " pokers en estos numeros");
        System.out.println("Hay "+ tercia1+ " tercias en estos numeros");
        System.out.println("Hay "+ doblepar1+ " doblespares en estos numeros");
        System.out.println("Hay "+ par1+ " pares en estos numeros");
    }
    
}
