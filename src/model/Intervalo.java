package model;

import java.util.concurrent.atomic.AtomicReference;

public class Intervalo {
    private Double inicio;
    private Double fin;
    private Double apariciones;
    private Double frecuencia;
    private Double frec_acumulada;

    public Intervalo(Double inicio, Double fin) {
        this.inicio = inicio;
        this.fin = fin;
        this.apariciones = 0.0;
    }

    public Intervalo() {}

    public Double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Double frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Double getFrec_acumulada() {
        return frec_acumulada;
    }

    public void setFrec_acumulada(Double frec_acumulada) {
        this.frec_acumulada = frec_acumulada;
    }

    public Double getInicio() {
        return inicio;
    }

    public void setInicio(Double inicio) {
        this.inicio = inicio;
    }

    public Double getFin() {
        return fin;
    }

    public void setFin(Double fin) {
        this.fin = fin;
    }

    public Double getApariciones() {
        return apariciones;
    }

    public void setApariciones(Double apariciones) {
        this.apariciones = apariciones;
    }
}
