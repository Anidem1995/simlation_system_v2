
package pruebas_independencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KolmogorovSmirnov {
    double mas,menos,sumatoriaMas=0,sumatoriaMenos=0,D;
            
    public boolean calcular(List<Double> nums)
    {
        boolean accepted = false;
        int n=0, i=1;
        double val;
        List<Double>nums2 = new ArrayList<>();
        List<Double>Dmas = new ArrayList<>();
        List<Double>Dmenos = new ArrayList<>();

        //añade los valores decimales a otro arraylist
        for (int x=0; x<nums.size(); x++) 
        {
            val = nums.get(x) / 10000;
            nums2.add(val);
            n++;
        } 
        
        //ordena los valores
        Collections.sort(nums2);
        
        //saca los valores de D+ y D-
        for (int x=0; x<nums2.size(); x++)
        {
            mas =(i/n) - nums2.get(x);
            menos = nums2.get(x) - ((i-1)/n);
            
            Dmas.add(mas);
            Dmenos.add(menos);    
            
            i++;
        }
        
        //saca la sumatoria de D+ y D-
        for (int x=0; x<nums2.size(); x++)
        {
            sumatoriaMas += Dmas.get(x);
            sumatoriaMenos += Dmenos.get(x);
        }
        
        D =1.36/ Math.sqrt(n);
        
        if(sumatoriaMas > sumatoriaMenos)
        {
            if(sumatoriaMas<=D)
            {
                System.out.println("Nivel de significancia (alfa) considerado 5% \n");
                System.out.println("Valor de D+: "+sumatoriaMas+"\n");
                System.out.println("Valor de D-: "+sumatoriaMenos+"\n");
                System.out.println("Valor de D: "+D+"\n");
                System.out.println("Total de numeros de la muestra: "+ n+"\n");
                System.out.println("De acuerdo a la prueba de Kolmogorov Smirnov los numeros generados provienen de una distribución uniforme \n");
                accepted = true;
            }
            else
            {
                System.out.println("Nivel de significancia (alfa) considerado 5% \n");
                System.out.println("Valor de D+: "+sumatoriaMas+"\n");
                System.out.println("Valor de D-: "+sumatoriaMenos+"\n");
                System.out.println("Valor de D: "+D+"\n");
                System.out.println("Total de numeros de la muestra: "+ n+"\n");
                System.out.println("De acuerdo a la prueba de Kolmogorov Smirnov los numeros generados NO provienen de una distribución uniforme \n");
                accepted = false;
            }
            
        }
        else
        {
            if(sumatoriaMenos<=D)
            {
                System.out.println("Nivel de significancia (alfa) considerado 5% \n");
                System.out.println("Valor de D+: "+sumatoriaMas+"\n");
                System.out.println("Valor de D-: "+sumatoriaMenos+"\n");
                System.out.println("Valor de D: "+D+"\n");
                System.out.println("Total de numeros de la muestra: "+ n+"\n");
                System.out.println("De acuerdo a la prueba de Kolmogorov Smirnov los numeros generados provienen de una distribución uniforme \n");
                accepted = true;
            }
            else
            {
                System.out.println("Nivel de significancia (alfa) considerado 5% \n");
                System.out.println("Valor de D+: "+sumatoriaMas+"\n");
                System.out.println("Valor de D-: "+sumatoriaMenos+"\n");
                System.out.println("Valor de D: "+D+"\n");
                System.out.println("Total de numeros de la muestra: "+ n+"\n");
                System.out.println("De acuerdo a la prueba de Kolmogorov Smirnov los numeros generados NO provienen de una distribución uniforme \n");
                accepted = false;
            }
            
        }
        return accepted;
    }
}
