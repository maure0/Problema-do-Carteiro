public class Main {

    public static void main(String[] args) {
        Grafo g = new Grafo();

        Vertice A = new Vertice("A", 1);
        Vertice B = new Vertice("B", 2);
        Vertice C = new Vertice("C", 3);
        Vertice D = new Vertice("D", 4);
        Vertice E = new Vertice("E", 5);
        Vertice F = new Vertice("F", 6);
        Vertice G = new Vertice("G", 7);
    
        g.adicionarVertice(A);
        g.adicionarVertice(B);
        g.adicionarVertice(C);
        g.adicionarVertice(D);
        g.adicionarVertice(E);
        g.adicionarVertice(F);
        g.adicionarVertice(G);

        g.adicionarAresta(1, A, E);
        g.adicionarAresta(13, F, E);
        g.adicionarAresta(4, F, A);
        g.adicionarAresta(6, A, B);
        g.adicionarAresta(14, B, C);
        g.adicionarAresta(1, C, A);
        g.adicionarAresta(8, C, F);
        g.adicionarAresta(3, B, D);
        g.adicionarAresta(4, D, C);
        g.adicionarAresta(7, C, G);

        CarteiroChines c = new CarteiroChines(g);
        c.resolver();

    }

}


