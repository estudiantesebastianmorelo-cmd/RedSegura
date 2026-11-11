package com.redsegura.algorithm;

import com.redsegura.model.Edge;
import com.redsegura.model.Vertex;
import com.redsegura.graph.MixedGraph;

import java.util.*;

public class DijkstraSolver {
    
    public static class DijkstraResult {
        public double cost;
        public List<Vertex> path;
        public DijkstraResult(double cost, List<Vertex> path) {
            this.cost = cost;
            this.path = path;
        }
    }

    public static DijkstraResult shortestPath(MixedGraph graph, String startId, String endId) {
        Vertex start = graph.getVertex(startId);
        Vertex end = graph.getVertex(endId);

        Map<Vertex, Double> distances = new HashMap<>();
        Map<Vertex, Vertex> predecessors = new HashMap<>();
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingDouble(nd -> nd.distance));

        for (Vertex v : graph.getVertices()) {
            distances.put(v, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);
        pq.add(new NodeDistance(start, 0.0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            Vertex u = current.vertex;

            if (u.equals(end)) break;
            if (current.distance > distances.get(u)) continue;

            for (Edge edge : graph.getAdjacencyList().getOrDefault(u.getId(), Collections.emptyList())) {
                Vertex v = edge.getDestination();
                double weight = edge.getWeight();

                if (distances.get(u) + weight < distances.get(v)) {
                    distances.put(v, distances.get(u) + weight);
                    predecessors.put(v, u);
                    pq.add(new NodeDistance(v, distances.get(v)));
                }
            }
        }

        List<Vertex> path = new ArrayList<>();
        Vertex curr = end;
        if (!predecessors.containsKey(curr) && !curr.equals(start)) {
            return new DijkstraResult(-1, Collections.emptyList());
        }
        
        while (curr != null) {
            path.add(0, curr);
            curr = predecessors.get(curr);
        }

        return new DijkstraResult(distances.get(end), path);
    }

    private static class NodeDistance {
        Vertex vertex;
        double distance;
        NodeDistance(Vertex vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}