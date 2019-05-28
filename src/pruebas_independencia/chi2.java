package pruebas_independencia;

import java.util.ArrayList;

public class chi2 
{
    public void calcular(ArrayList<Double>nums)
    {
        double FE = nums.size() / 5;
        int n=0;
        double val = 0;
        ArrayList<Double>nums2 = new ArrayList<>();
        String a;
        
        //añade los valores decimales a otro arraylist
        for (int x=0; x<nums.size(); x++) 
        {
            val = nums.get(x) / 10000;
            nums2.add(val);
            n++;
            System.out.println("Añade valores" + val);
        }
        
        
        double valTemp;
        int FoRango1=0,FoRango2=0,FoRango3=0,FoRango4=0,FoRango5=0;
        
        //cuenta la freuencia obtenida
        for(int x=0; x<nums2.size();x++)
        {
            valTemp =nums2.get(x);
            
            if(valTemp > 0 && valTemp <= 0.2000)
            {
                FoRango1++;
            }
            else if(valTemp > 0.2000 && valTemp <= 0.4000)
            {
                FoRango2++;
            }
            else if(valTemp > 0.4000 && valTemp <= 0.6000)
            {
                FoRango3++;                
            }
            else if(valTemp > 0.6000 && valTemp <= 0.8000)
            {
                FoRango4++;
            }
            else if(valTemp > 0.8000 && valTemp <= 1)
            {
                FoRango5++;
            }
            else
            {
                System.out.println("Error\n Existen numeros fuera de rango");
            }
            System.out.println("frecuencia obtenida" + valTemp);
        }
        
        double x2, valorTablaX2 =0.711;
        int gradosLib = 5-1;
         
        x2 = ((double)Math.pow((FoRango1 - FE),2)/FE)+
                ((double)Math.pow((FoRango2 - FE),2)/FE)+
                ((double)Math.pow((FoRango3 - FE),2)/FE)+
                ((double)Math.pow((FoRango4 - FE),2)/FE)+
                ((double)Math.pow((FoRango5 - FE),2)/FE);
         
        System.out.println("Tamaño de la muestra: "+n+"\n");
        System.out.println("Numero de subintervalos: 5");
        System.out.println("Frecuencia esperada por subintervalo: "+FE+"\n");
        System.out.println("Frecuencia observada por subintervalo: \n"
                            +"Rango (0.0,0.2]: "+FoRango1+"\n"
                            +"Rango (0.2,0.4]: "+FoRango2+"\n"
                            +"Rango (0.4,0.6]: "+FoRango3+"\n"
                            +"Rango (0.6,0.8]: "+FoRango4+"\n"
                            +"Rango (0.8,1.0]: "+FoRango5+"\n");
        System.out.println("Grados de libertad: "+gradosLib+"\n");
        System.out.println("Nivel de significancia (alfa) considerado 5% \n");
        System.out.println("Valor de X2 de acuerdo el valor de alfa y el grado de libartad: "+valorTablaX2+"\n");
        System.out.println("Valor de X2 de acuerdo al calculo de la prueba: "+x2+"\n");
            
        if(x2 < valorTablaX2)
        {
            System.out.println("Segun la prueba de chi cuadrada la muestra proviene de una distribucion uniforme");
        }
        else
        {
            System.out.println("Segun la prueba de chi cuadrada la muestra NO proviene de una distribucion uniforme");
        }
    } 
}
