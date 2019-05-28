package algoritmos_geneticos.model;

import java.util.List;

public class Chromosome {
    private List<Integer> genotype;
    private Double aptitude;

    public Chromosome() { }

    public Chromosome(List<Integer> genotype) {
        this.genotype = genotype;
    }

    public List<Integer> getGenotype() {
        return genotype;
    }

    public void setGenotype(List<Integer> genotype) {
        this.genotype = genotype;
    }

    public Double getAptitude() {
        return aptitude;
    }

    public void setAptitude(Double aptitude) {
        this.aptitude = aptitude;
    }

    @Override
    public String toString() {
        String s = "";
        for(int i : genotype)
            s += i;
        return "Genotype: " + s + "\n" + "Aptitude: " + this.aptitude;
    }
}
