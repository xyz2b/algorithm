package graph.undirected_weighted_graph;

class WeightEdge implements Comparable<WeightEdge> {
    private int v, w, weight;
    public WeightEdge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int getV() {
        return v;
    }

    public int getW() {
        return w;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightEdge another) {
        return weight - another.weight;
    }

    @Override
    public String toString() {
        return String.format("(%d-%d: %d)", v, w, weight);
    }
}
