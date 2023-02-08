import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        
        for(Vertice v : this.g.getVertices()) {
            System.out.println(v.getLabel() + " -> " + v.getArestasIncidentes().size());
        }


        if (!verticesDeGrauImpar.isEmpty()) {
            eulerizarGrafo(verticesDeGrauImpar);
        } else {
            System.out.println("O grafo ja é euleriano!");
        }
        
        for(Vertice v : this.g.getVertices()) {
            System.out.println(v.getLabel() + " -> " + v.getArestasIncidentes().size());
        }

        // TODO Implementar Fleury para conseguir a rota
        List<Vertice> encaminhamento = fleury();
        for(Vertice e : encaminhamento) {
            System.out.print(e.getLabel() + " -> ");
        }
        System.out.println(this.g.getVertices().get(0).getLabel());
    }

    public List<Vertice> fleury() {
        List<Vertice> encaminhamento = new ArrayList<>();
        Vertice v = g.getVertices().get(0);
        List<Aresta> es = new ArrayList<>();
        Vertice anterior = null;
        double custoTotal = 0;

        for(int i = 0; i < g.getVertices().size(); i++) {
            g.getVertices().get(i).setAnterior(null);
        }

        while (es.size() < g.getArestas().size()) {
            encaminhamento.add(v);
            Aresta e = v.getArestaNaoVisitadaMenorPeso();
            // if(Objects.isNull(e)) break;
            System.out.println(e.getPeso());
            custoTotal += e.getPeso();
            e.setVisitada(true);
            es.add(e);
            anterior = v;
            v = e.verticeOposta(v);
        }


        return encaminhamento;

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
        
        while (d.size() > 0) {
            Vertice[] v = d.menorPar();
            Vertice inicio = v[0];
            Vertice fim = v[1];

            dijkstra(inicio, fim);

            System.out.println("Par com a menor distancia presente na matriz de distancias: " + inicio.getLabel() + " - " + fim.getLabel());

            Vertice atual = fim;
            Vertice anterior = atual.getAnterior();
            while (!atual.equals(inicio)) {
                double w = g.getAresta(atual.getAnterior(), atual).getPeso();
                anterior = atual;
                atual = atual.getAnterior();
                System.out.println("Adicionando aresta entre : " + anterior.getLabel() + " -> " + atual.getLabel());
                g.adicionarAresta(w, anterior, atual);
            }

            d.remove(inicio);
            d.remove(fim);
            System.out.println();
        }

        System.out.println("Grafo eulerizado!");
    }

}
