package com.redsegura.model;

import java.util.Objects;

public class Vertex implements Comparable<Vertex> {
    private String id;
    private String label;
    private String type;

    public Vertex(String id, String label, String type) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("El ID del vértice no puede estar vacío.");
        if (label == null || label.isBlank()) throw new IllegalArgumentException("La etiqueta no puede estar vacía.");
        this.id = id;
        this.label = label;
        this.type = type;
    }

    public String getId() { return id; }
    public String getLabel() { return label; }
    public String getType() { return type; }

    @Override
    public int compareTo(Vertex o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return id.equals(vertex.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", id, label, type);
    }
}