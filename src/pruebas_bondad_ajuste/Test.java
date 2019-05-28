package pruebas_bondad_ajuste;

import archivos.ManejoArchivos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Test{
static Scanner a_teclado;
        ArrayList<Double> datos;
        public ArrayList<Double> intervalos;
        double [] frecuenciaObservada;
        tablaDisNormal distNormal=new tablaDisNormal();
        //ArrayList<Integer> Frecobservada;
        Double[] a;
        private static BigDecimal truncateDecimal(double x,int numberofDecimals)
        {
                if ( x > 0) {
                        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
                } else {
                        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
                }
        }
public  Test() {


        a_teclado= new Scanner(System.in);
        intervalos= new ArrayList();

        //CALCULAR RANGO
        //DATOMAX-DATOMIN
        datos=new ManejoArchivos().obtenerLista();

        double max = 0;
        for (int i = 0; i < datos.size(); i++) {
        if (datos.get(i) > max) {
        max = datos.get(i);
        }
        }
        double min = max;
        for (int i = 0; i < datos.size(); i++) {
        if (datos.get(i) < min) {
        min = datos.get(i);
        }
        }

        //CALCULAR INTERVALOS K
        //DEBEN SER PREFERENTEMENE IMPARES
        double rango=max-min;
        double k=1+3.322*Math.log10(datos.size());
        int contador = 0;

        for(int I = 1; I <= Math.ceil(k); I++)
        {
                if((Math.ceil(k) % I) == 0)
                {
                        contador++;
                }
        }

        if(contador <= 2)
        {
        k=Math.ceil(k);
        }else{
        k=Math.floor(k);
        }

        //CALCULAR LA AMPLITUD
        double a=rango/k;

        //ARREGLO DEL TAMAÑO DE LOS INTERVALOS

        frecuenciaObservada= new double[(int)k];
        intervalos = new ArrayList();
        double var=min;

        for(int i=0;i<k;i++)
        {
        var += a;
        intervalos.add(var);
        }

        //CALCULAR LA MEDIA
        double media=0;
        for(int i=0;i<datos.size();i++){
                media+=datos.get(i);
        }
        media=media/datos.size();

        //CALCULAR DESVIACIÓN ESTANDAR
        double desvestandar = 0;
        double varianza = 0;

        for(int i=0; i<datos.size();i++)
        {
                varianza+=Math.pow(datos.get(i)-media,2)/datos.size();
        }
        desvestandar = Math.sqrt(varianza);

        //FRECUENCIA OBSERVADA VARIABLES QUE SE ENCUENTRAN EN EL RANGO Y Estadistico Z.
        for(int i=0;i<datos.size();i++)
        {
        Boolean b=true;
                for(int c=0;c<intervalos.size();c++){
                if(datos.get(i)<intervalos.get(c) && b)
                {
                        b=false;
                        frecuenciaObservada[c]=frecuenciaObservada[c]+1;

                }
                }

        }

        //SE CALCULAN LOS ESTADISTICOS A BUSCAR EN LA TABLA NORMAL
        double [][] estadisticosZ= new double[intervalos.size()][2];

        for (int i=0;i<intervalos.size();i++){
                for(int j=0; j<2;j++){
                        double z=0;
                        double vari=0;
                        if(j==0)
                                vari=a;
                        z=(intervalos.get(i)-media-vari)/desvestandar;
                        estadisticosZ[i][j]=Double.valueOf(truncateDecimal(z,2).toString());
                }
        }


        //for(int i=0;i<estadisticosZ.length;i++){
         //     System.out.println(estadisticosZ[i][0]+"   "+ estadisticosZ[i][1] +"    "+ intervalos.get(i)+"      "+ frecuenciaObservada[i]);
        //}

        //for(int i=0;i<frecuenciaObservada.length;i++){
         //       System.out.println(frecuenciaObservada[i] +"     "+ intervalos.get(i)+ "feswfs");
        //}

        ArrayList<Double> probabilidades= new ArrayList<>();
        for(int i=0;i<estadisticosZ.length;i++){
                String estA, estA1, estB1;
                String estB;


                int iPart = (int) estadisticosZ[i][1];
                double fPart = estadisticosZ[i][1] - iPart;

                estA=Integer.toString(iPart);
                estB=fPart+"";

                for(int j=0;j<estB.length();j++){

                        if(estB.substring(j,j+1).equals(".")){
                                estA=estA+"."+estB.substring(j+1,j+2);
                                estB="0.0"+estB.substring(j+2,j+3);
                        }
                }
                int iPart1 = (int) estadisticosZ[i][0];
                double fPart1 = estadisticosZ[i][0] - iPart1;
                estA1=Integer.toString(iPart1);
                estB1=fPart1+"";

                for(int j=0;j<estB1.length();j++){

                        if(estB1.substring(j,j+1).equals(".")){
                                estA1=estA1+"."+estB1.substring(j+1,j+2);
                                estB1="0.0"+estB1.substring(j+2,j+3);

                        }
                }

                Double distMayor=distNormal.buscar(estA,Double.valueOf(estB));
                Double distMenor=distNormal.buscar(estA1,Double.valueOf(estB1));
                probabilidades.add(distMayor-distMenor);
        }
        //for(int i=0;i<probabilidades.size();i++){
        //        System.out.println(probabilidades.get(i));
        //}


        double esperado=0;
        for(int i=0;i<datos.size();i++){
            esperado+=datos.get(i);
        }
        esperado=esperado/datos.size();
        ArrayList<Double> teorico= new ArrayList<>();

        for(int i=0;i<intervalos.size();i++){
            teorico.add(frecuenciaObservada[i]*probabilidades.get(i));
        }
        for(int i=0;i<intervalos.size();i++){
                System.out.println("INTERVALOS       "+(intervalos.get(i)-a)+"----"+ intervalos.get(i)+"   FRECUENCIA      "+frecuenciaObservada[i]+ "  PROBABILIDAD     "+ probabilidades.get(i)+"  FRECUENCIA OBSERVADA    "+teorico.get(i) );
        }
        System.out.println("Seleccione el tipo de problema a resolver: ");
        System. out.println("1.- Test de Bondad de Ajuste");



// La variable chi2 contiene los puntos cr{iticos de la distribuci{on chi-cuadrado
        double [][] chi2 = new double[30][20];

        chi2[0][0]=0.0000392713; chi2[0][1]=0.00015709 ; chi2[0][2]=0.00062844 ;
        chi2[0][3]=0.00098207 ;  chi2[0][4]=0.00393219 ; chi2[0][5]=0.0157907  ;
        chi2[0][6]=0.06418462 ;  chi2[0][7]=0.10153112 ; chi2[0][8]=0.14847169 ;
        chi2[0][9]=0.45493617 ;  chi2[0][10]=1.07419508 ; chi2[1][11]=1.3233042 ;
        chi2[1][12]=1.64237643 ; chi2[0][13]=2.70554059 ; chi2[1][14]=3.84145534;
        chi2[1][15]=5.02390259 ; chi2[0][16]=5.4119039  ; chi2[1][17]=6.6348913 ;
        chi2[1][18]=7.87939984 ; chi2[1][19]=10.8273599  ;

        chi2[1][0]=0.010024667 ; chi2[1][1]=0.02010041 ; chi2[1][2]=0.04040506  ;
        chi2[1][3]=0.05063571  ; chi2[1][4]=0.10258624 ; chi2[1][5]=0.21072078  ;
        chi2[1][6]=0.44628681  ; chi2[1][7]=0.57536392 ; chi2[1][8]=0.71334954  ;
        chi2[1][9]=1.38629356  ; chi2[1][10]=2.40794418; chi2[1][11]=2.77259042 ;
        chi2[1][12]=3.21887861 ; chi2[1][13]=4.60517613; chi2[1][14]=5.99147636 ;
        chi2[1][15]=7.37777915 ; chi2[1][16]=7.82407148; chi2[1][17]=9.21035104 ;
        chi2[1][18]=10.5965296 ; chi2[1][19]=13.8150038;

        chi2[2][0]=0.071723452 ; chi2[2][1]=0.11483162 ; chi2[2][2]=0.18483028  ;
        chi2[2][3]=0.2157949   ; chi2[2][4]=0.35184596 ; chi2[2][5]=0.58437546  ;
        chi2[2][6]=1.00517327  ; chi2[2][7]=1.21253206 ; chi2[2][8]=1.42365235  ;
        chi2[2][9]=2.36597275  ; chi2[2][10]=3.66487081; chi2[2][11]=4.10834211 ;
        chi2[2][12]=4.64163016 ; chi2[2][13]=6.25139445; chi2[2][14]=7.8147247  ;
        chi2[2][15]=9.34840397 ; chi2[2][16]=9.8374115 ; chi2[2][17]=11.3448821 ;
        chi2[2][18]=12.8380732 ; chi2[2][19]=16.2659592;

        chi2[3][0]=0.206983634 ; chi2[3][1]=0.29710681 ; chi2[3][2]= 0.42939979 ;
        chi2[3][3]= 0.48441898 ; chi2[3][4]= 0.7107241 ; chi2[3][5]= 1.06362428 ;
        chi2[3][6]= 1.64877579 ; chi2[3][7]= 1.922558  ; chi2[3][8]=  2.19469816;
        chi2[3][9]= 3.35669472 ; chi2[3][10]=4.87843209; chi2[3][11]= 5.38526609;
        chi2[3][12]= 5.98861547; chi2[3][13]=7.77943396; chi2[3][14]=9.48772846 ;
        chi2[3][15]= 11.143262 ; chi2[3][16]=11.6678439; chi2[3][17]=13.2766986 ;
        chi2[3][18]=14.8601658 ; chi2[3][19]=18.4662262;

        chi2[4][0]=0.411750815 ; chi2[4][1]=0.55429691 ; chi2[4][2]=0.75188962  ;
        chi2[4][3]=0.83120886  ; chi2[4][4]=1.1454773  ; chi2[4][5]=1.61030906  ;
        chi2[4][6]=2.34253238  ; chi2[4][7]=2.67460419 ; chi2[4][8]=2.99990998  ;
        chi2[4][9]=4.35145866  ; chi2[4][10]=6.06443063; chi2[4][11]=6.62567844 ;
        chi2[4][12]=7.289273   ; chi2[4][13]=9.2363491 ; chi2[4][14]=11.0704826 ;
        chi2[4][15]=12.832492  ; chi2[4][16]=13.3882241; chi2[4][17]=15.0863174 ;
        chi2[4][18]=16.7496485 ; chi2[4][19]=20.5146507;

        chi2[5][0]=0.675733351 ; chi2[5][1]=0.87208326 ; chi2[5][2]=1.13441559  ;
        chi2[5][3]=1.23734192  ; chi2[5][4]=1.63538047 ; chi2[5][5]=2.20413033  ;
        chi2[5][6]=3.07008773  ; chi2[5][7]=3.45459752 ; chi2[5][8]=3.82755105  ;
        chi2[5][9]=5.34811904  ; chi2[5][10]=7.23113222; chi2[5][11]=7.84080573 ;
        chi2[5][12]= 8.55805779; chi2[5][13]=10.6446375; chi2[5][14]=12.5915774 ;
        chi2[5][15]=14.449355  ; chi2[5][16]=15.0331968; chi2[5][17]=16.8118718 ;
        chi2[5][18]=18.5475126 ; chi2[5][19]=22.4574794;

        chi2[6][0]=0.989250877 ; chi2[6][1]=1.23903171 ; chi2[6][2]=1.56429438  ;
        chi2[6][3]=1.68986403  ; chi2[6][4]=2.16734918 ; chi2[6][5]= 2.8331052  ;
        chi2[6][6]= 3.82231961 ; chi2[6][7]=4.25485216 ; chi2[6][8]=4.67132973  ;
        chi2[6][9]=6.34580926  ; chi2[6][10]=8.38342933; chi2[6][11]=9.03714593 ;
        chi2[6][12]= 9.80324786; chi2[6][13]=12.0170314; chi2[6][14]=14.0671273 ;
        chi2[6][15]= 16.0127737; chi2[6][16]=16.6224305; chi2[6][17]=18.4753241 ;
        chi2[6][18]=20.2777379 ; chi2[6][19]=24.3212955;

        chi2[7][0]=1.344402736 ; chi2[7][1]=1.64650617 ; chi2[7][2]=2.0324683   ;
        chi2[7][3]=2.17972466  ; chi2[7][4]=2.73263265 ; chi2[7][5]=3.48953743  ;
        chi2[7][6]=4.59357183  ; chi2[7][7]=5.07064161 ; chi2[7][8]= 5.52742332 ;
        chi2[7][9]= 7.34412012 ; chi2[7][10]=9.5244569 ; chi2[7][11]=10.2188538 ;
        chi2[7][12]=11.030089  ; chi2[7][13]=13.3615619; chi2[7][14]=15.5073125 ;
        chi2[7][15]=17.5345446 ; chi2[7][16]=18.1682014; chi2[7][17]=20.0901592 ;
        chi2[7][18]= 21.954861 ; chi2[7][19]=26.1239313;

        chi2[8][0]=1.734911384 ; chi2[8][1]= 2.08788942; chi2[8][2]=2.53236808  ;
        chi2[8][3]=2.70038873  ; chi2[8][4]=3.32511514 ; chi2[8][5]=4.16815571  ;
        chi2[8][6]=5.38005545  ; chi2[8][7]= 5.89882293; chi2[8][8]=6.39330396  ;
        chi2[8][9]=8.34283203  ; chi2[8][10]=10.6563694; chi2[8][11]=11.3887495 ;
        chi2[8][12]= 12.2421409; chi2[8][13]=14.6836632; chi2[8][14]=16.9189602 ;
        chi2[8][15]= 19.0227776; chi2[8][16]=19.6789778; chi2[8][17]=21.6660476 ;
        chi2[8][18]= 23.5892748; chi2[8][19]=27.8767314;

        chi2[9][0]=2.155845379 ; chi2[9][1]= 2.55819883; chi2[9][2]= 3.05905416 ;
        chi2[9][3]= 3.2469635  ; chi2[9][4]= 3.94029535; chi2[9][5]= 4.8651783  ;
        chi2[9][6]= 6.17907588 ; chi2[9][7]= 6.73719855; chi2[9][8]= 7.26721803 ;
        chi2[9][9]= 9.34181609 ; chi2[9][10]=11.7807199; chi2[9][11]=12.5488588 ;
        chi2[9][12]= 13.4419627; chi2[9][13]=15.9871747; chi2[9][14]=18.307029  ;
        chi2[9][15]= 20.4832007; chi2[9][16]=21.160752 ; chi2[9][17]=23.2092872 ;
        chi2[9][18]= 25.1880549; chi2[9][19]=29.5878854;

        chi2[10][0]=2.603201921; chi2[10][1]=3.05349572; chi2[10][2]=3.60868262 ;
        chi2[10][3]=3.81574236 ; chi2[10][4]=4.57480903; chi2[10][5]=5.57778834 ;
        chi2[10][6]= 6.98867219; chi2[10][7]=7.58414485; chi2[10][8]=8.14786512 ;
        chi2[10][9]= 10.3409955; chi2[10][10]=12.8986677; chi2[10][11]=13.7006897;
        chi2[10][12]=14.6314204; chi2[10][13]=17.2750067; chi2[10][14]=19.6751531;
        chi2[10][15]=21.9200227; chi2[10][16]=22.6178952; chi2[10][17]=24.7250219;
        chi2[10][18]=26.7568638; chi2[10][19]=31.2635069;

        chi2[11][0]=3.073785001; chi2[11][1]=3.57055135 ; chi2[11][2]=4.17827858 ;
        chi2[11][3]= 4.40377753; chi2[11][4]=5.22602767 ; chi2[11][5]=6.30379585 ;
        chi2[11][6]= 7.80732864; chi2[11][7]=8.43841944 ; chi2[11][8]= 9.03427808;
        chi2[11][9]=11.3403219 ; chi2[11][10]=14.0111009; chi2[11][11]=14.8453991;
        chi2[11][12]=15.81199  ; chi2[11][13]=18.5493402; chi2[11][14]=21.0260554;
        chi2[11][15]=23.3366602; chi2[11][16]=24.0539347; chi2[11][17]=26.2169637;
        chi2[11][18]=28.2996599; chi2[11][19]=32.9092299;

        chi2[12][0]=3.565041969; chi2[12][1]=4.10689964 ; chi2[12][2]=4.76544397 ;
        chi2[12][3]= 5.00873758; chi2[12][4]=5.89186059 ; chi2[12][5]= 7.04149966;
        chi2[12][6]= 8.63386347; chi2[12][7]= 9.29906327; chi2[12][8]= 9.92567929;
        chi2[12][9]=12.3397531 ; chi2[12][10]=15.1187184; chi2[12][11]=15.9839051;
        chi2[12][12]=16.9847934; chi2[12][13]=19.8119327; chi2[12][14]=22.3620266;
        chi2[12][15]=24.7355809; chi2[12][16]=25.4714923; chi2[12][17]=27.6881845;
        chi2[12][18]=29.8193179; chi2[12][19]=34.5273671;

        chi2[13][0]=4.07465883 ; chi2[13][1]=4.66041549 ; chi2[13][2]= 5.36818314;
        chi2[13][3]=5.62872382 ; chi2[13][4]=6.57063165 ; chi2[13][5]= 7.78953765;
        chi2[13][6]=9.46732899 ; chi2[13][7]=10.1653113 ; chi2[13][8]=10.8214758 ;
        chi2[13][9]=13.3392718 ; chi2[13][10]=16.2220942; chi2[13][11]=17.1169328;
        chi2[13][12]=18.150767 ; chi2[13][13]=21.0641406; chi2[13][14]=23.6847823;
        chi2[13][15]=26.1189349; chi2[13][16]=26.8727296; chi2[13][17]=29.1411633;
        chi2[13][18]=31.3194251; chi2[13][19]=36.1238673;

        chi2[14][0]=4.600874064; chi2[14][1]=5.22935591 ; chi2[14][2]=5.984904   ;
        chi2[14][3]= 6.26212294; chi2[14][4]=7.26093477 ; chi2[14][5]=8.54675311 ;
        chi2[14][6]= 10.3069578; chi2[14][7]=11.0365377 ; chi2[14][8]=11.7211681 ;
        chi2[14][9]= 14.3388572; chi2[14][10]=17.3216928; chi2[14][11]=18.2450842;
        chi2[14][12]=19.3106533; chi2[14][13]=22.3071206; chi2[14][14]=24.9957967;
        chi2[14][15]=27.4883647; chi2[14][16]=28.2594884; chi2[14][17]=30.5779507;
        chi2[14][18]=32.8014907; chi2[14][19]=37.6977736;

        chi2[15][0]=5.142164252; chi2[15][1]=5.81219685 ; chi2[15][2]=6.61422137 ;
        chi2[15][3]= 6.90766413; chi2[15][4]=7.96163861 ; chi2[15][5]= 9.31223527;
        chi2[15][6]= 11.1521187; chi2[15][7]=11.9122166 ; chi2[15][8]= 12.6243455;
        chi2[15][9]=15.3384973 ; chi2[15][10]=18.4178907; chi2[15][11]=19.3688566;
        chi2[15][12]=20.4650744; chi2[15][13]=23.5418215; chi2[15][14]=26.2962209;
        chi2[15][15]=28.8453246; chi2[15][16]=29.6331594; chi2[15][17]=31.9998609;
        chi2[15][18]=34.2670534; chi2[15][19]=  39.2517757;

        chi2[16][0]=5.697273651; chi2[16][1]=6.40774196 ; chi2[16][2]=7.25498623 ;
        chi2[16][3]= 7.56417857; chi2[16][4]= 8.6717536 ; chi2[16][5]= 10.085183 ;
        chi2[16][6]=12.0022634 ; chi2[16][7]=12.7919245 ; chi2[16][8]= 13.5306752;
        chi2[16][9]=16.338179  ; chi2[16][10]=19.5110202; chi2[16][11]=20.4886786;
        chi2[16][12]=21.6145624; chi2[16][13]=24.7690282; chi2[16][14]=27.5871003;
        chi2[16][15]=30.1909826; chi2[16][16]=30.9950423; chi2[16][17]=33.408717 ;
        chi2[16][18]=35.7183777; chi2[16][19]=40.7911093;

        chi2[17][0]=6.264765867; chi2[17][1]=7.01490342 ; chi2[17][2]= 7.90623123;
        chi2[17][3]= 8.23073717; chi2[17][4]= 9.39044787; chi2[17][5]= 10.8649369;
        chi2[17][6]= 12.8569505; chi2[17][7]=13.6752906 ; chi2[17][8]=14.4398614 ;
        chi2[17][9]= 17.337902 ; chi2[17][10]=20.601351 ; chi2[17][11]=21.6048862;
        chi2[17][12]=22.7595489; chi2[17][13]=25.9894184; chi2[17][14]=28.869321 ;
        chi2[17][15]=31.5264102; chi2[17][16]=32.3461707; chi2[17][17]=34.8052374;
        chi2[17][18]=37.1563856; chi2[17][19]=42.3119483;

        chi2[18][0]=6.843923334; chi2[18][1]=7.63269763 ; chi2[18][2]= 8.56703219 ;
        chi2[18][3]= 8.90651438; chi2[18][4]=10.1170062 ; chi2[18][5]=11.650912   ;
        chi2[18][6]=13.7157851 ; chi2[18][7]=14.5619976 ; chi2[18][8]=15.3516585  ;
        chi2[18][9]=18.33765   ; chi2[18][10]=21.6891285; chi2[18][11]=22.7178053 ;
        chi2[18][12]=23.9004185; chi2[18][13]=27.2035648; chi2[18][14]=30.1435051 ;
        chi2[18][15]=32.852337 ; chi2[18][16]=33.6874059; chi2[18][17]=36.1907747 ;
        chi2[18][18]=38.5821224; chi2[18][19]=43.8193646;

        chi2[19][0]=7.433811355; chi2[19][1]=8.26036838 ; chi2[19][2]= 9.23667798 ;
        chi2[19][3]= 9.59077247; chi2[19][4]=10.8507994 ; chi2[19][5]=12.4426014  ;
        chi2[19][6]=14.5784405 ; chi2[19][7]=15.4517747 ; chi2[19][8]=16.2658528  ;
        chi2[19][9]=19.3374296 ; chi2[19][10]=22.7745411; chi2[19][11]=23.8276894 ;
        chi2[19][12]=25.0375008; chi2[19][13]=28.4119699; chi2[19][14]=31.4104204 ;
        chi2[19][15]=34.1695814; chi2[19][16]=35.0196238; chi2[19][17]=37.5662715 ;
        chi2[19][18]=39.9968558; chi2[19][19]=45.3142182;

        chi2[20][0]=8.033602143; chi2[20][1]= 8.89717245; chi2[20][2]=9.9145491  ;
        chi2[20][3]= 10.2829066; chi2[20][4]= 11.591316 ; chi2[20][5]=13.2395955 ;
        chi2[20][6]= 15.4446132; chi2[20][7]= 16.3443873; chi2[20][8]= 17.1822693;
        chi2[20][9]= 20.3372282; chi2[20][10]=23.8577858; chi2[20][11]=24.9347832;
        chi2[20][12]=26.171094 ; chi2[20][13]=29.6150859; chi2[20][14]=32.670558 ;
        chi2[20][15]=35.4788557; chi2[20][16]=36.3434414; chi2[20][17]=38.9322325;
        chi2[20][18]=41.4009426; chi2[20][19]=46.7962708;

        chi2[21][0]=8.642680624; chi2[21][1]= 9.54249443; chi2[21][2]=10.6000225 ;
        chi2[21][3]=10.9823302 ; chi2[21][4]= 12.3380095; chi2[21][5]=14.0414896 ;
        chi2[21][6]= 16.3140386; chi2[21][7]= 17.2396185; chi2[21][8]=18.1007221 ;
        chi2[21][9]= 21.3370437; chi2[21][10]=24.9390134; chi2[21][11]=26.0392635;
        chi2[21][12]=27.3014548; chi2[21][13]=30.8132853; chi2[21][14]=33.9244598;
        chi2[21][15]=36.7806781; chi2[21][16]=37.6594765; chi2[21][15]= 40.289448;
        chi2[21][18]=42.7956641; chi2[21][19]=48.2676242;

        chi2[22][0]=9.26038309;  chi2[22][1]= 10.1956888 ; chi2[22][2]= 11.2925813 ;
        chi2[22][3]=11.6885343;  chi2[22][4]= 13.090505  ; chi2[22][5]= 14.8479543 ;
        chi2[22][6]=17.1865015;  chi2[22][7]= 18.137294  ; chi2[22][8]= 19.0210902 ;
        chi2[22][9]=22.3368799;  chi2[22][10]=26.0183674 ; chi2[22][11]=27.1413291 ;
        chi2[22][12]=28.4287897; chi2[22][13]=32.0068902 ; chi2[22][14]=35.1724602 ;
        chi2[22][15]=38.0756095; chi2[22][16]=38.9682769 ; chi2[22][17]=41.6383344 ;
        chi2[22][18]=44.1813851; chi2[22][19]=49.7276429 ;

        chi2[23][0]=9.886198665; chi2[23][1]=10.8563494  ; chi2[23][2]=11.9918024  ;
        chi2[23][3]=12.4011458 ; chi2[23][4]=13.8484222  ; chi2[23][5]=15.6586793  ;
        chi2[23][6]=18.0618004 ; chi2[23][7]=19.0372505  ; chi2[23][8]=19.9432272  ;
        chi2[23][9]=23.3367299 ; chi2[23][10]=27.0959557 ; chi2[23][11]=28.2411501 ;
        chi2[23][12]=29.5533202; chi2[23][13]=33.1962351 ; chi2[23][14]=36.4150265 ;
        chi2[23][15]=39.3640601; chi2[23][16]=40.270328  ; chi2[23][17]=42.9797813 ;
        chi2[23][18]=45.5583626; chi2[23][19]=51.1789694 ;

        chi2[24][0]=10.51964705; chi2[24][1]=11.5239511  ; chi2[24][2]=12.6972624  ;
        chi2[24][3]=13.119707  ; chi2[24][4]=14.6113957  ; chi2[24][5]=16.4734055  ;
        chi2[24][6]=18.9397497 ; chi2[24][7]=19.9393377  ; chi2[24][8]=20.8670355  ;
        chi2[24][9]=24.3365837 ; chi2[24][10]=28.1719136 ; chi2[24][11]=29.3388466 ;
        chi2[24][12]=30.6751987; chi2[24][13]=34.3815833 ; chi2[24][14]=37.6524894 ;
        chi2[24][15]=40.6464978; chi2[24][16]=41.5660331 ; chi2[24][17]=44.3140141 ;
        chi2[24][18]=46.927966 ; chi2[24][19]= 52.6187381;

        chi2[25][0]=11.1602178 ; chi2[25][1]=12.1981769  ; chi2[25][2]=13.4085625  ;
        chi2[25][3]=13.8438811 ; chi2[25][4]= 15.3791626 ; chi2[25][5]= 17.2918796 ;
        chi2[25][6]= 19.8201889; chi2[25][7]= 20.8434347 ; chi2[25][8]= 21.7923994 ;
        chi2[25][9]= 25.3364585; chi2[25][10]=29.2463233 ; chi2[25][11]=30.4345588 ;
        chi2[25][12]=31.7946092; chi2[25][13]=35.5631637 ; chi2[25][14]=38.8851296 ;
        chi2[25][15]=41.9231379; chi2[25][16]=42.8558131 ; chi2[25][17]=45.6416362 ;
        chi2[25][18]=48.2897774; chi2[25][19]=54.0511357 ;

        chi2[26][0]=11.80765496; chi2[26][1]=12.8784685  ; chi2[26][2]=14.1254023  ;
        chi2[26][3]=14.5733732 ; chi2[26][4]=16.1513946  ; chi2[26][5]=18.1138885  ;
        chi2[26][6]=20.7029767 ; chi2[26][7]= 21.7494036 ; chi2[26][8]= 22.7192327 ;
        chi2[26][9]= 26.3363413; chi2[26][10]=30.3192897 ; chi2[26][11]=31.5284104 ;
        chi2[26][12]=32.9116829; chi2[26][13]=36.7412276 ; chi2[26][14]=40.1132656 ;
        chi2[26][15]=43.1945211; chi2[26][16]=44.1399289 ; chi2[26][17]=46.9628372 ;
        chi2[26][18]=49.6450354; chi2[26][19]=55.4750798 ;

        chi2[27][0]=12.46128105; chi2[27][1]=13.5646661  ; chi2[27][2]=14.8474626  ;
        chi2[27][3]=15.3078543 ; chi2[27][4]=16.9278763  ; chi2[27][5]=18.9392353  ;
        chi2[27][6]=21.5879679 ; chi2[27][7]= 22.6571575 ; chi2[27][8]= 23.6474599 ;
        chi2[27][9]= 27.3362315; chi2[27][10]=31.390874  ; chi2[27][11]=32.6204888 ;
        chi2[27][12]=34.0265688; chi2[27][13]=37.9159074 ; chi2[27][14]=41.3371517 ;
        chi2[27][15]=44.4607905; chi2[27][16]=45.4188057 ; chi2[27][17]=48.2781662 ;
        chi2[27][18]=50.9935588; chi2[27][19]=56.8917562 ;

        chi2[28][0]=13.12106662; chi2[28][1]= 14.2564062 ; chi2[28][2]= 15.5744601 ;
        chi2[28][3]= 16.0470506; chi2[28][4]= 17.7083814 ; chi2[28][5]= 19.7677396 ;
        chi2[28][6]= 22.4750517; chi2[28][7]= 23.5665882 ; chi2[28][8]= 24.5769843 ;
        chi2[28][9]= 28.3361296; chi2[28][10]=32.4611627 ; chi2[28][11]=33.7109064 ;
        chi2[28][12]=35.1393659; chi2[28][13]=39.0874753 ; chi2[28][14]=42.5569475 ;
        chi2[28][15]=45.7222795; chi2[28][16]= 46.692637 ; chi2[28][17]= 49.587829 ;
        chi2[28][18]=52.3354953; chi2[28][19]= 58.3006421;

        chi2[29][0]=13.78668167; chi2[29][1]= 14.9534644 ; chi2[29][2]= 16.3061526 ;
        chi2[29][3]= 16.7907558; chi2[29][4]= 18.4926672 ; chi2[29][5]= 20.5992447 ;
        chi2[29][6]= 23.3641134; chi2[29][7]=   24.477603; chi2[29][8]= 25.5077642 ;
        chi2[29][9]= 29.3360283; chi2[29][10]= 33.5302356; chi2[29][11]= 34.7997355;
        chi2[29][12]=36.2501825; chi2[29][13]= 40.256017 ; chi2[29][14]= 43.7729539;
        chi2[29][15]=46.9792176; chi2[29][16]= 47.9617853; chi2[29][17]= 50.8921806;
        chi2[29][18]= 53.671868; chi2[29][19]= 59.7022125;





        //La variable gradosdelibertad contiene el n{umero de grados de libertad de la distribuci{on chi
        int gradosdelibertad=0;


//Lectura de la opción seleccionada
        int opcion;
        opcion=a_teclado.nextInt();

        if ((opcion<1) || (opcion>3))
        {System.out.println("Debes seleccionar un n{umero entre 1 y 3");
        return;
        }

//Lectura del nivel de confianza
        int confianza=0;
        System.out.println("Niveles de significaci{on: ");
        System.out.println("Nivel de significaci{on 1....0.995");
        System.out.println("Nivel de significaci{on 2.... 0.99");
        System.out.println("Nivel de significaci{on 3.... 0.98");
        System.out.println("Nivel de significaci{on 4.... 0.975");
        System.out.println("Nivel de significaci{on 5.... 0.95");
        System.out.println("Nivel de significaci{on 6....0.9");
        System.out.println("Nivel de significaci{on 7....0.8");
        System.out.println("Nivel de significaci{on 8....0.75");
        System.out.println("Nivel de significaci{on 9....0.7");
        System.out.println("Nivel de significaci{on 10...0.5");
        System.out.println("Nivel de significaci{on 11....0.3");
        System.out.println("Nivel de significaci{on 12....0.25");
        System.out.println("Nivel de significaci{on 13....0.2");
        System.out.println("Nivel de significaci{on 14....0.1");
        System.out.println("Nivel de significaci{on 15....0.05");
        System.out.println("Nivel de significaci{on 16....0.025");
        System.out.println("Nivel de significaci{on 17....0.02");
        System.out.println("Nivel de significaci{on 18....0.01");
        System.out.println("Nivel de significaci{on 19....0.005");
        System.out.println("Nivel de significaci{on 20....0.001");
        confianza=a_teclado.nextInt();
        if ((confianza<1) || (confianza>20))
        {System.out.println("Debes seleccionar un n{umero entre 1 y 20");
        return;
        }

//Resolución del problema correspondiente, si la opción elegida es 1, 2 o 3
        double estadistico=0;
        int aux1,aux2,totalxy=0;
        switch(opcion)   {
        case 1: //La variable valores contiene el número de modalidades distintas del
        //carácter en estudio
        int valores;

        valores=(int)k;

        //Los valores observados y teóricos se guardan cada uno en un array
        double [] obser1;


        //Lectura de los datos teóricos y observados
        obser1=frecuenciaObservada;
        double [] teori1 = new double[obser1.length];
        for(int i=0;i<teorico.size();i++){
        teori1[i]=teorico.get(i);
        }

        //La variable estadistico contiene el valor del estadístico del contraste

        for (aux1=0 ; aux1 < valores ; aux1++)
        estadistico += ((obser1[aux1]-teori1[aux1])*
        (obser1[aux1]-teori1[aux1]))/teori1[aux1] ;

        gradosdelibertad=valores-1;
        break;

default:
        System. out.println("Debes seleccionar un n{umero entre 1 y 3");
        }  //llave del switch

        System.out.println("Valor del estadístico experimental de la prueba..."+estadistico);
        System.out.println("Valor de la distribución chicuadrado..."+chi2[gradosdelibertad-1][confianza-1]+"  COMO CONSECUENCIA: ");
        if (estadistico < chi2[gradosdelibertad-1][confianza-1])
        switch(opcion) {
        case 1: System.out.println("Se acepta la Bondad del ajuste con dicho nivel de significación y los datos observados");
        break;

        }
        else
        switch(opcion) {
        case 1: System.out.println("Se rechaza la Bondad del ajuste con dicho nivel de significación y los datos observados");
        break;

        }

        } //llave del procedimiento main
        } //llave de la clase