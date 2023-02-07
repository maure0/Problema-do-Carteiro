import java.util.ArrayList;
import java.util.List;

public class CarteiroChines {

    private Grafo g;
    private Double INFINITO = Double.MAX_VALUE;

    public CarteiroChines(Grafo g) {
        this.g = g;
    }

    public void resolver() {
        List<Vertice> verticesDeGrauImpar = new ArrayList<>();
        for (Vertice v : this.g.getVertices()) {
            if (v.getArestasIncidentes().size() % 2 == 1) {
                verticesDeGrauImpar.add(v);
            }
        }

        if (!verticesDeGrauImpar.isEmpty())
            eulerizarGrafo(verticesDeGrauImpar);

        // TODO Implementar Fleury para conseguir a rota


    }

    public double dijkstra(Vertice v1, Vertice v2) {
        List<Vertice> Q = new ArrayList<Vertice>();

        for (Vertice v : g.getVertices()) {
            v.setDistancia(INFINITO);
            v.setAnterior(null);
            Q.add(v);
        }

        v1.setDistancia(0);

        while (!Q.isEmpty()) {
            Vertice u = Q.stream().min((a, b) -> Double.compare(a.getDistancia(), b.getDistancia())).get();
            Q.remove(u);
            u.setVisitada(true);

            for (Vertice v : u.getVerticesAdjacentes()) {
                double alt = u.getDistancia() + g.getAresta(u, v).getPeso();
                if (alt < v.getDistancia()) {
                    v.setDistancia(alt);
                    v.setAnterior(u);
                }
            }
        }
        return v2.getDistancia();
    }

    public void eulerizarGrafo(List<Vertice> verticesGrauImpar) {
        MatrizDistancia d = new MatrizDistancia(verticesGrauImpar);

        for (int i = 0; i < verticesGrauImpar.size(); i++) {
            for (int j = 0; j < verticesGrauImpar.size(); j++) {
                double distancia = dijkstra(verticesGrauImpar.get(i), verticesGrauImpar.get(j));
                d.setDistancia(i, j, distancia);
            }
        }

        while(d.size() > 0) {
            Vertice[] v = d.menorPar();
            Vertice inicio = v[0];
            Vertice fim = v[1];

            dijkstra(inicio, fim);

            Vertice atual = fim;
            while(!atual.equals(inicio)) {
                double w = g.getAresta(atual.getAnterior(), atual).getPeso();
                g.adicionarAresta(w, fim, atual);
                atual = atual.getAnterior();
            }
            
            d.remove(inicio);
            d.remove(fim);
        
        }
    }


}
