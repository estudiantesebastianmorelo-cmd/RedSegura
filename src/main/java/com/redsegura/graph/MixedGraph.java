package com.redsegura.graph;

import com.redsegura.model.Edge;
import com.redsegura.model.Vertex;
import java.util.*;

public class MixedGraph {
    private final Map<String, Vertex> vertices = new LinkedHashMap<>();
    private final Map<String, Edge> edges = new LinkedHashMap<>();
    private final Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public void addVertex(Vertex v) {
        if (vertices.containsKey(v.getId())) {
            throw new IllegalArgumentException("El vértice con ID " + v.getId() + " ya existe.");
        }
        vertices.put(v.getId(), v);
        adjacencyList.put(v.getId(), new ArrayList<>());
    }

    public void addEdge(Edge edge) {
        if (edges.containsKey(edge.getId())) {
            throw new IllegalArgumentException("La arista con ID " + edge.getId() + " ya existe.");
        }
        if (!vertices.containsKey(edge.getOrigin().getId()) || !vertices.containsKey(edge.getDestination().getId())) {
            throw new IllegalArgumentException("Origen o destino no existen en el grafo.");
        }
        edges.put(edge.getId(), edge);
        adjacencyList.get(edge.getOrigin().getId()).add(edge);

        if (!edge.isDirected()) {
            Edge reverseEdge = new Edge(edge.getId() + "_rev", edge.getDestination(), edge.getOrigin(), false, edge.getWeight(), edge.getLabel());
            adjacencyList.get(edge.getDestination().getId()).add(reverseEdge);
        }
    }

    public Vertex getVertex(String id) {
        Vertex v = vertices.get(id);
        if (v == null) {
            throw new NoSuchElementException("Vértice no encontrado: " + id);
        }
        return v;
    }

    public Collection<Vertex> getVertices() { return vertices.values(); }
    public Collection<Edge> getEdges() { return edges.values(); }
    public Map<String, List<Edge>> getAdjacencyList() { return adjacencyList; }
}