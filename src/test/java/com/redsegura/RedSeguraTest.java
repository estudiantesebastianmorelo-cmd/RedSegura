package com.redsegura;

import com.redsegura.algorithm.DijkstraSolver;
import com.redsegura.graph.MixedGraph;
import com.redsegura.model.Edge;
import com.redsegura.model.Vertex;
import com.redsegura.tree.AvlTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class RedSeguraTest {

    private MixedGraph graph;
    private AvlTree<String> avl;

    @BeforeEach
    void setUp() {
        graph = new MixedGraph();
        avl = new AvlTree<>();

        Vertex s01 = new Vertex("S01", "UAJS Sede C", "Universidad");
        Vertex s02 = new Vertex("S02", "UAJS Sede E", "Universidad");
        Vertex s07 = new Vertex("S07", "Estación Central SETP", "Transporte");
        Vertex s08 = new Vertex("S08", "Terminal de Transporte", "Transporte");

        graph.addVertex(s01);
        graph.addVertex(s02);
        graph.addVertex(s07);
        graph.addVertex(s08);

        graph.addEdge(new Edge("R01", s01, s07, true, 4, "Corredor"));
        graph.addEdge(new Edge("R04", s02, s01, true, 8, "Conexión"));
        graph.addEdge(new Edge("R16", s07, s08, true, 12, "Ruta terminal"));
    }

    @Test
    void testDijkstraControlRoute() {
        var result = DijkstraSolver.shortestPath(graph, "S02", "S08");
        assertEquals(24.0, result.cost, 0.001, "La ruta S02 a S08 debe costar exactamente 24 minutos.");
    }

    @Test
    void testInvalidVertexException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            graph.getVertex("S99");
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void testNegativeWeightException() {
        Vertex v1 = graph.getVertex("S01");
        Vertex v2 = graph.getVertex("S02");
        assertThrows(IllegalArgumentException.class, () -> {
            new Edge("R99", v1, v2, true, -3.0, "Peso inválido");
        });
    }

    @Test
    void testAvlTreeInsertionAndSearch() {
        avl.insert("Hospital Universitario");
        assertTrue(avl.search("Hospital Universitario"));
        assertFalse(avl.search("Lugar Inexistente"));
    }
}