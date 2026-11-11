package com.redsegura;

import com.redsegura.algorithm.DijkstraSolver;
import com.redsegura.graph.GraphRepresentations;
import com.redsegura.graph.MixedGraph;
import com.redsegura.model.Edge;
import com.redsegura.model.Vertex;
import com.redsegura.tree.AvlTree;

import java.util.ArrayList;
import java.util.List;

public class RedSeguraApplication {
    public static void main(String[] args) {
        System.out.println("=== INICIALIZANDO SISTEMA REDSEGURA (SINCELEJO) ===");
        MixedGraph graph = new MixedGraph();
        AvlTree<String> avl = new AvlTree<>();

        // 1. Cargar Vértices (S01 a S12)
        Vertex s01 = new Vertex("S01", "UAJS Sede C", "Universidad");
        Vertex s02 = new Vertex("S02", "UAJS Sede E", "Universidad");
        Vertex s03 = new Vertex("S03", "Universidad de Sucre", "Universidad");
        Vertex s04 = new Vertex("S04", "CECAR", "Universidad");
        Vertex s05 = new Vertex("S05", "Hospital Universitario", "Salud");
        Vertex s06 = new Vertex("S06", "Clínica Las Américas", "Salud");
        Vertex s07 = new Vertex("S07", "Estación Central SETP", "Transporte");
        Vertex s08 = new Vertex("S08", "Terminal de Transporte", "Transporte");
        Vertex s09 = new Vertex("S09", "Parque Santander", "Punto de encuentro");
        Vertex s10 = new Vertex("S10", "Mercado de Sincelejo", "Comercio");
        Vertex s11 = new Vertex("S11", "PEP Argelia", "Paradero");
        Vertex s12 = new Vertex("S12", "Glorieta Sincelejito", "Movilidad");

        Vertex[] verticesArray = {s01, s02, s03, s04, s05, s06, s07, s08, s09, s10, s11, s12};
        List<Vertex> verticesList = new ArrayList<>();
        for (Vertex v : verticesArray) {
            graph.addVertex(v);
            avl.insert(v.getLabel());
            verticesList.add(v);
        }

        // 2. Cargar Conexiones Ponderadas (R01 a R18)
        graph.addEdge(new Edge("R01", s01, s07, true, 4, "Corredor universitario"));
        graph.addEdge(new Edge("R02", s07, s01, true, 6, "Retorno por vía alterna"));
        graph.addEdge(new Edge("R03", s01, s09, false, 5, "Corredor peatonal"));
        graph.addEdge(new Edge("R04", s02, s01, true, 8, "Conexión entre sedes"));
        graph.addEdge(new Edge("R05", s02, s06, true, 10, "Ruta de atención"));
        graph.addEdge(new Edge("R06", s06, s05, true, 4, "Corredor clínico"));
        graph.addEdge(new Edge("R07", s05, s09, false, 6, "Vía hospitalaria"));
        graph.addEdge(new Edge("R08", s09, s10, false, 3, "Centro histórico"));
        graph.addEdge(new Edge("R09", s10, s08, true, 8, "Salida al terminal"));
        graph.addEdge(new Edge("R10", s08, s10, true, 9, "Ingreso desde terminal"));
        graph.addEdge(new Edge("R11", s03, s12, false, 4, "Corredor Puerta Roja"));
        graph.addEdge(new Edge("R12", s12, s09, false, 7, "Troncal al centro"));
        graph.addEdge(new Edge("R13", s04, s11, false, 6, "Conexión universitaria"));
        graph.addEdge(new Edge("R14", s11, s07, true, 8, "Ruta SETP al centro"));
        graph.addEdge(new Edge("R15", s07, s09, false, 3, "Transferencia central"));
        graph.addEdge(new Edge("R16", s07, s08, true, 12, "Ruta SETP al terminal"));
        graph.addEdge(new Edge("R17", s09, s03, true, 7, "Ruta hacia universidad"));
        graph.addEdge(new Edge("R18", s12, s04, true, 9, "Ruta hacia CECAR"));

        // ==========================================
        // DEMOSTRACIÓN DE CONSULTAS OBLIGATORIAS (Q1 - Q7)
        // ==========================================
        System.out.println("\n--- [Q1] BÚSQUEDA EN ÁRBOL AVL ---");
        boolean encontrado = avl.search("Hospital Universitario");
        System.out.println("¿Hospital Universitario está en el AVL? " + encontrado);

        System.out.println("\n--- [Q3] REPRESENTACIONES DEL GRAFO ---");
        GraphRepresentations.printAdjacencyList(graph);

        System.out.println("\n--- [Q5] PRUEBA DE CONTROL DIJKSTRA (S02 a S08) ---");
        var res = DijkstraSolver.shortestPath(graph, "S02", "S08");
        System.out.println("Costo total (debe ser 24 min): " + res.cost + " minutos");
        System.out.print("Ruta óptima: ");
        res.path.forEach(v -> System.out.print(v.getId() + " (" + v.getLabel() + ") -> "));
        System.out.println("FIN");

        System.out.println("\n--- [Q7] CONSULTAR VÉRTICE INEXISTENTE ---");
        try {
            graph.getVertex("S99");
        } catch (Exception e) {
            System.out.println("Excepción capturada con éxito: " + e.getMessage());
        }
        
        System.out.println("\n=== APLICACIÓN FINALIZADA CON ÉXITO ===");
    }
}