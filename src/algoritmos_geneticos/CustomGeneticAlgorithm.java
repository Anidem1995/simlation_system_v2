package algoritmos_geneticos;
import algoritmos_geneticos.model.Chromosome;
import algoritmos_geneticos.model.Tuple;
import numeros_pseudoaleatorios.VonNeumann;
import pruebas_independencia.KolmogorovSmirnov;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomGeneticAlgorithm {
    private Scanner scanner = new Scanner(System.in);
    private List<Chromosome> chromosomes = new ArrayList<Chromosome>();
    private TravellingSalesman travellingSalesman = new TravellingSalesman();
    private Double acumulated_fitness = 0.0;
    private Chromosome c1, c2;
    private List<Chromosome> offspring = new ArrayList<>();

    public CustomGeneticAlgorithm() {
        //travellingSalesman.setMatrix();
    }

    private void setPopulation() {
        System.out.println("Tamaño de la población");
        int population_size = scanner.nextInt();

        for(int i = 0; i < population_size; i++) {
            Chromosome chromosome = new Chromosome();
            List<Tuple> chromosome_data = travellingSalesman.sart();
            chromosome.setGenotype(chromosome_data.stream().map(t -> t.getNode_number()).collect(Collectors.toList()));
            chromosome.setAptitude(chromosome_data.stream().mapToDouble(t -> t.getWeight()).sum());
            chromosomes.add(chromosome);
        }
    }

    public void start(List<Double> random_numbers) {
        setPopulation();
        setAcumulatedFitness();
        for(Chromosome chromosome : chromosomes)
            chromosome.setFitness_radio(acumulated_fitness);

        /*Chromosome cc1 = new Chromosome();
        List<Tuple> l1 = new ArrayList<Tuple>(Arrays.asList(new Tuple(0, 3), new Tuple(0, 3)));
        Collections.addAll(l1, 0,1,6,2,4,7,3,5);
        cc1.setGenotype(l1);
        cc1.setAptitude(66.0);

        Chromosome cc2 = new Chromosome();
        List<Integer> l2 = new ArrayList<>();
        Collections.addAll(l2, 2,0,1,6,3,7,4,5);
        cc2.setGenotype(l2);
        cc2.setAptitude(65.0);

        Chromosome c3 = new Chromosome();
        List<Integer> l3 = new ArrayList<>();
        Collections.addAll(l3, 5,3,1,0,2,4,7,6);
        c3.setGenotype(l3);
        c3.setAptitude(66.0);

        Chromosome c4 = new Chromosome();
        List<Integer> l4 = new ArrayList<>();
        Collections.addAll(l4, 6,1,0,2,4,7,3,5);
        c4.setGenotype(l4);
        c4.setAptitude(66.0);

        Collections.addAll(chromosomes, cc1, cc2, c3, c4);

        setAcumulatedFitness();

        cc1.setFitness_radio(acumulated_fitness);
        cc2.setFitness_radio(acumulated_fitness);
        c3.setFitness_radio(acumulated_fitness);
        c4.setFitness_radio(acumulated_fitness);*/

        for(int i = 0; i < 10; i++) {
            System.out.println("Población");
            for (Chromosome c : chromosomes)
                System.out.println(c.toString());

            System.out.println("Seleccionando cromosomas para el cruce");
            for(int j = 0; j < chromosomes.size() / 2; j++) {
                Random rr = new Random();
                Double roulete = random_numbers.get(rr.nextInt(random_numbers.size()));

                c1 = setupRanges(roulete);
                roulete = random_numbers.get(rr.nextInt(random_numbers.size()));
                c2 = setupRanges(roulete);
            /*c1 = Collections.min(chromosomes, Comparator.comparing(c -> c.getAptitude()));
            c2 = Collections.min(chromosomes, Comparator.comparing(c -> c.getAptitude() <= c1.getAptitude() || c.getAptitude() != c1.getAptitude()));*/

                System.out.println("Cromosomas a cruzar");
                System.out.println(c1.toString());
                System.out.println(c2.toString());

                List<Chromosome> new_offspring = crossover(c1, c2, random_numbers);
                offspring = Stream.of(offspring, new_offspring)
                        .flatMap(x -> x.stream())
                        .collect(Collectors.toList());
            }
            chromosomes.clear();
            chromosomes = offspring;
        }
        System.out.println("Población final");
        for (Chromosome c : chromosomes)
            System.out.println(c.toString());
    }

    private List<Chromosome> crossover(Chromosome parent1, Chromosome parent2, List<Double> random_numbers) {
        List<Chromosome> offspring = new ArrayList<Chromosome>();
        Double mutation_co = 0.004;

        int relative_sequence_size = (int) Math.ceil(parent1.getGenotype().size() * 0.4);
        int tail_size = (int) Math.rint(parent1.getGenotype().size() * 0.2);
        int head_size = (int) Math.rint(parent1.getGenotype().size() * 0.3);

        Chromosome descendant_1 = generateDescendant(parent1, parent2, relative_sequence_size, head_size, tail_size);
        Chromosome descendant_2 = generateDescendant(parent2, parent1, relative_sequence_size, head_size, tail_size);

        offspring.add(descendant_1);
        offspring.add(descendant_2);

        for(Chromosome chromosome : offspring)
            mutateChromosome(chromosome, mutation_co, head_size, tail_size, relative_sequence_size, random_numbers);

        return offspring;
    }

    private Chromosome generateDescendant(Chromosome parent1, Chromosome parent2, int relative_sequence_size, int head_size, int tail_size) {
        List<Integer> genotype = parent1.getGenotype().subList(head_size, head_size + relative_sequence_size);

        List<Integer> sub_sequence = parent2.getGenotype().subList(head_size + relative_sequence_size, parent2.getGenotype().size());
        List<Integer> sub_sequence_2 = parent2.getGenotype().subList(0, head_size);
        List<Integer> sub_sequence_3 = parent2.getGenotype().subList(head_size, head_size + relative_sequence_size);

        List<Integer> combined = Stream.of(sub_sequence, sub_sequence_2)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        List<Integer> full_sequence = Stream.of(combined, sub_sequence_3)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());


        full_sequence.removeAll(genotype);

        List<Integer> sub_gen = full_sequence.subList(0, head_size);
        List<Integer> combined_genes = Stream.of(genotype, sub_gen)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());

        List<Integer> sub_gen_2 = full_sequence.subList(head_size, head_size + tail_size);

        List<Integer> full_genotype = Stream.of(sub_gen_2, combined_genes)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());

        return new Chromosome(full_genotype);
    }

    private void mutateChromosome(Chromosome chromosome, Double mutation_co, int head_size, int tail_size, int relative_sequence_size, List<Double> random_numbers) {
        Random rr = new Random();
        Double n = random_numbers.get(rr.nextInt(random_numbers.size()));
        if(n < mutation_co)
            Collections.swap(chromosome.getGenotype(), relative_sequence_size - 1, head_size + relative_sequence_size);
    }

    private void setAcumulatedFitness() {
        for(Chromosome chromosome : chromosomes)
            acumulated_fitness += chromosome.getAptitude();
    }

    private Chromosome setupRanges(Double n) {
        Double bottom = 0.0, top = 0.0;
        Chromosome parent = null;
        for( Chromosome c : chromosomes) {
            top = c.getFitness_radio();
            if(n >= bottom && n <= top) {
                parent = c;
                break;
            }
            else
                bottom = c.getFitness_radio();
        }
        return parent;
    }

    private void selectChromosome(Double roulete) {

    }
}
