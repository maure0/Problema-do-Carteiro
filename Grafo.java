import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Vertice> vertices;
    private List<Aresta> arestas;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    public void adicionarVertice(Vertice v) {
        this.vertices.add(v);
    }

    public void adicionarAresta(double peso, Vertice v1, Vertice v2) {
        if (!vertices.contains(v1) || !vertices.contains(v2))
            throw new RuntimeException("Pelo menos um dos vertices não pertence ao grafo.");

        Aresta e = new Aresta(peso, v1, v2);
        this.arestas.add(e);

        v1.addArestaIncidente(e);
        v2.addArestaIncidente(e);

    }

    public List<Vertice> getVertices() {
        return this.vertices;
    }

    public Aresta getAresta(Vertice u, Vertice v) {
        for (int i = 0; i < arestas.size(); i++) {
            Aresta e = arestas.get(i);
            if (e.getInicio().equals(u) && e.getFim().equals(v) || e.getInicio().equals(v) && e.getFim().equals(u))
                return e;
        }
        return null;
    }

    public List<Aresta> getArestas() {
        return this.arestas;
    }

}