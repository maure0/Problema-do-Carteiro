public class Aresta {
    private Vertice inicio;
    private Vertice fim;
    private double peso;
    public Aresta(double peso, Vertice inicio, Vertice fim) {
        this.inicio = inicio;
        this.fim = fim;
        this.peso = peso;
    }

    public Vertice verticeOposta(Vertice v) {
        return inicio.equals(v) ? fim : inicio;
    }

    public Vertice getInicio() {
        return this.inicio;
    }

    public void setInicio(Vertice inicio) {
        this.inicio = inicio;
    }

    public Vertice getFim() {
        return this.fim;
    }

    public void setFim(Vertice fim) {
        this.fim = fim;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

}