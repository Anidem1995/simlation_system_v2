package algoritmos_geneticos.model;

public class Tuple {
    int node_number;
    int weight;

    public Tuple(int node_number, int weight) {
        this.node_number = node_number;
        this.weight = weight;
    }

    public int getNode_number() {
        return node_number;
    }

    public void setNode_number(int node_number) {
        this.node_number = node_number;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
