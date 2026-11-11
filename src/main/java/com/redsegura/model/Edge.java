package com.redsegura.model;

public class Edge {
    private String id;
    private Vertex origin;
    private Vertex destination;
    private boolean directed;
    private double weight; // Minutos estimados
    private String label;

    public Edge(String id, Vertex origin, Vertex destination, boolean directed, double weight, String label) {
        if (weight < 0) throw new IllegalArgumentException("El peso (minutos) no puede ser negativo.");
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.directed = directed;
        this.weight = weight;
        this.label = label;
    }

    public String getId() { return id; }
    public Vertex getOrigin() { return origin; }
    public Vertex getDestination() { return destination; }
    public boolean isDirected() { return directed; }
    public double getWeight() { return weight; }
    public String getLabel() { return label; }
    
    public void setWeight(double weight) {
        if (weight < 0) throw new IllegalArgumentException("Peso inválido.");
        this.weight = weight;
    }

    @Override
    public String toString() {
        String arrow = directed ? "->" : "---";
        return String.format("%s: %s %s %s [%.0f min - %s]", id, origin.getId(), arrow, destination.getId(), weight, label);
    }
}