# RedSegura - Sistema de Atención de Emergencias (Sincelejo)

Proyecto académico desarrollado para la asignatura **Estructuras de Datos II** (Java 17, Maven, Apache NetBeans). Modela la movilidad urbana entre universidades, centros de salud y nodos de transporte mediante estructuras de árboles AVL y grafos mixtos ponderados.

## 🏛️ Arquitectura y Paquetes
El sistema se encuentra modularizado bajo contratos públicos claros:
- `com.redsegura.model`: Entidades principales (`Vertex` y `Edge`).
- `com.redsegura.tree`: Implementación propia de Árbol Binario de Búsqueda y Árbol AVL con rotaciones (LL, RR, LR, RL).
- `com.redsegura.graph`: Gestión del grafo mixto, lista de adyacencia, matriz de adyacencia ponderada y matriz de incidencia.
- `com.redsegura.algorithm`: Algoritmos de recorrido (BFS, DFS) y optimización de rutas mínimas (`DijkstraSolver`).
- `com.redsegura` (`RedSeguraApplication`): Demostración en consola (CLI) y manejo de consultas Q1-Q7.

## ⏱️ Complejidad Temporal y Espacial (Big O)
- **Árbol AVL (Búsqueda / Inserción):** Tiempo $\mathcal{O}(\log N)$, Espacio $\mathcal{O}(N)$. Mantiene el balance automático de alturas.
- **Algoritmo de Dijkstra:** Tiempo $\mathcal{O}((V + E) \log V)$ usando colas de prioridad (`PriorityQueue`), Espacio $\mathcal{O}(V + E)$.
- **Recorridos BFS / DFS:** Tiempo $\mathcal{O}(V + E)$, Espacio $\mathcal{O}(V)$ mediante control de vértices visitados.

## 🚀 Instrucciones de Ejecución y Pruebas
1. Clonar el repositorio y abrirlo en **Apache NetBeans** como un proyecto **Maven**.
2. Ejecutar las pruebas unitarias con Maven:
   ```bash
   mvn clean test
