package algoritmos_geneticos;

import algoritmos_geneticos.model.Node;
import algoritmos_geneticos.model.Tuple;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class TravellingSalesman {

    private List<Node> cities;
    private List<Tuple> genotype = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    int number_of_cities;
    int[][] matrix;

    public TravellingSalesman() {
        cities = new ArrayList<>();
    }

    public void setMatrix() {
        System.out.println("¿Desea cargar su propia matriz de adyacencia o prefiere que el programa genere una al azar?\n1 - Sí\n2 - No");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                try {
                    Stream<String> stream = Files.lines(Paths.get("matrix.txt"));
                    generateMatrixFromFile(stream);
                }catch (Exception e){e.printStackTrace();}
                break;
            case 2:
                System.out.println("Número de ciudades");
                number_of_cities = scanner.nextInt();
                generateMatrix(number_of_cities);
                break;
        }
    }

    public List<Tuple> sart() {
        matrixToList();
        return closestNeighbour();
    }

    private void generateMatrixFromFile(Stream<String> stream) {
        matrix = new int[(int) stream.count()][(int) stream.count()];
        List<Integer> numbers = new ArrayList<>();

        stream.forEach(s -> {
            String[] s_split = s.split(" ");
            for(String n : s_split)
                numbers.add(Integer.parseInt(n));
        });

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = numbers.get(0);
                numbers.remove(0);
            }
        }
    }

    private void generateMatrix(int number_of_cities) {
        matrix = new int[number_of_cities][number_of_cities];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(i != j)
                    matrix[i][j] = ((int) (Math.random() * 50));
                else matrix[i][j] = 0;
                matrix[j][i] = matrix[i][j];
            }
        }
    }

    private void matrixToList() {
        cities = new ArrayList<>();
        for(int i = 0; i < matrix.length; i ++)
            cities.add(new Node(i));
        for (Node node : cities)
            System.out.println(node.getNode_number());
    }

    private List<Tuple> closestNeighbour() {
        genotype.clear();
        Tuple closest_neighbour_data;
        Random random = new Random();
        int start_point = random.nextInt(number_of_cities);
        Node start_node = cities.get(start_point);
        cities.get(start_point).setVisited(true);
        findNeighbors(start_node);
        closest_neighbour_data = getClosestNeighbour(start_node);
        genotype.add(closest_neighbour_data);

        while(!isAllCitiesVisited()) {
            Node next_node = cities.get(closest_neighbour_data.getNode_number());
            cities.get(next_node.getNode_number()).setVisited(true);
            findNeighbors(next_node);
            closest_neighbour_data = getClosestNeighbour(next_node);
            genotype.add(closest_neighbour_data);
        }
        return genotype;
    }

    private void findNeighbors(Node start_node) {
        List<Tuple> neighbours = new ArrayList<>();
        for(int i = 0; i < matrix[start_node.getNode_number()].length; i++) {
            if(matrix[start_node.getNode_number()][i] != 0 && !cities.get(i).isVisited())
                neighbours.add(new Tuple(i, matrix[start_node.getNode_number()][i]));
        }
        start_node.setNeighbours(neighbours);
    }

    private Tuple getClosestNeighbour(Node node) {
        return Collections.min(node.getNeighbours(), Comparator.comparing(t -> t.getWeight()));
    }

    private boolean isAllCitiesVisited() {
        boolean visited = false;
        for (Node node : cities)
            visited = (node.isVisited() ? true : false);
        return visited;
    }
}
