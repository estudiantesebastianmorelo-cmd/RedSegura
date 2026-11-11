package com.redsegura.graph;

import com.redsegura.model.Edge;
import com.redsegura.model.Vertex;
import java.util.*;

public class GraphRepresentations {

    public static void printAdjacencyList(MixedGraph graph) {
        System.out.println("=== LISTA DE ADYACENCIA ===");
        for (Map.Entry<String, List<Edge>> entry : graph.getAdjacencyList().entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Edge e : entry.getValue()) {
                System.out.print(e.getDestination().getId() + "(" + e.getWeight() + "m) ");
            }
            System.out.println();
        }
    }

    public static double[][] getWeightedAdjacencyMatrix(MixedGraph graph, List<Vertex> vertices) {
        int n = vertices.size();
        double[][] matrix = new double[n][n];
        Map<String, Integer> indexMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            indexMap.put(vertices.get(i).getId(), i);
            Arrays.fill(matrix[i], Double.POSITIVE_INFINITY);
            matrix[i][i] = 0.0;
        }

        for (Edge e : graph.getEdges()) {
            int u = indexMap.get(e.getOrigin().getId());
            int v = indexMap.get(e.getDestination().getId());
            matrix[u][v] = e.getWeight();
            if (!e.isDirected()) {
                matrix[v][u] = e.getWeight();
            }
        }
        return matrix;
    }

    public static int[][] getIncidenceMatrix(MixedGraph graph, List<Vertex> vertices, List<Edge> edges) {
        int vSize = vertices.size();
        int eSize = edges.size();
        int[][] matrix = new int[vSize][eSize];
        Map<String, Integer> vIndex = new HashMap<>();
        
        for (int i = 0; i < vSize; i++) {
            vIndex.put(vertices.get(i).getId(), i);
        }

        for (int j = 0; j < eSize; j++) {
            Edge e = edges.get(j);
            int originIdx = vIndex.get(e.getOrigin().getId());
            int destIdx = vIndex.get(e.getDestination().getId());

            if (e.isDirected()) {
                matrix[originIdx][j] = -1; 
                matrix[destIdx][j] = 1;    
            } else {
                matrix[originIdx][j] = 1;
                matrix[destIdx][j] = 1;
            }
        }
        return matrix;
    }
}