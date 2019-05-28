package algoritmos_geneticos.model;

import java.util.List;

public class Node {
    int node_number;
    List<Tuple> neighbours;
    boolean visited;

    public Node(int node_number) {
        this.node_number = node_number;
        this.visited = false;
    }

    public int getNode_number() {
        return node_number;
    }

    public void setNode_number(int node_number) {
        this.node_number = node_number;
    }

    public List<Tuple> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Tuple> neighbours) {
        this.neighbours = neighbours;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
