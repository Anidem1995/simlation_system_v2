package pruebas_independencia;

import java.util.ArrayList;

public class Corridas {
    
    int totalN=1,R=0;
    double numsNeg=0,numsPos=0,mediaR,desvEstR,Z,nivelSignificancia=0.005f,valTablaZ=2.58f;
    
    public void calcularCorridas(ArrayList<Double>nums) {
        double suma = 0,media,num;
        boolean contadorR,banderaR=true;
        
        for(int x=0; x < nums.size()-1; x++) {
            suma+=nums.get(x);            
            totalN++;
        }
        media = suma / totalN;
        
        for(int x=0; x < nums.size()-1; x++) {
            num = nums.get(x);
           
            if(num > media) {
                numsPos++;
                contadorR=true;
            }
            else {
                numsNeg++;
                contadorR=false;
            }
            
            if(banderaR != contadorR) {
                R++;
                banderaR=contadorR;
            }
        }
        
        if((nums.get(0) - media) < 0)
            R--;
        
        mediaR = ((2*numsPos*numsNeg)/(numsPos + numsNeg)) + 1;
        
        double s = numsPos + numsNeg,potencia = Math.pow(s, 2);
        
        desvEstR = (float)Math.sqrt(((2*numsPos*numsNeg)*(2*numsPos*numsNeg-numsPos-numsNeg)) / (potencia*(numsPos+numsNeg-1)));
        
        Z = (R - mediaR) / desvEstR;
        
        
        if(Z <= valTablaZ) {
            System.out.println("Nivel de significancia (alfa) considerado 5% \n");
            System.out.println("Total de corridas: "+R+"\n");
            System.out.println("Corridas positivas(arriba de la media): "+numsPos+"\n");
            System.out.println("Corridas negativas(debajo de la media): "+numsNeg+"\n");
            System.out.println("Valor esperado de R: "+mediaR+"\n");
            System.out.println("Desviación estandar de la media: "+ desvEstR+"\n");
            System.out.println("Valor de Z de acuerdo al nivel de significancia: "+valTablaZ+"\n");
            System.out.println("Valor de Z obtenido de los calculos con los numeros generados: "+Z+"\n");
            System.out.println("De acuerdo a la prueba de corridas los numeros generados son estadisticamente independientes \n");
        } else {
            System.out.println("Nivel de significancia (alfa) considerado 5% \n");
            System.out.println("Total de corridas: "+R+"\n");
            System.out.println("Corridas positivas(arriba de la media): "+numsPos+"\n");
            System.out.println("Corridas negativas(debajo de la media): "+numsNeg+"\n");
            System.out.println("Valor esperado de R: "+mediaR+"\n");
            System.out.println("Desviación estandar de la media: "+ desvEstR+"\n");
            System.out.println("Valor de Z de acuerdo al nivel de significancia: "+valTablaZ+"\n");
            System.out.println("Valor de Z obtenido de los calculos con los numeros generados: "+Z+"\n");
            System.out.println("De acuerdo a la prueba de corridas los numeros generados NO son estadisticamente independientes \n");
        } 
    }
}
