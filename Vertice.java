import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Vertice {
    private List<Vertice> verticesAdjacentes; 
    private List<Aresta> arestasIncidentes;
    private Integer data;    
    private double distancia;
    private Vertice anterior;
    private boolean visitada;
    private String label;


    public Vertice(Integer data) {
        this.verticesAdjacentes = new ArrayList<>();
        this.arestasIncidentes = new ArrayList<>();
        this.data = data;
    }

    public Vertice(String label, Integer data) {
        this.verticesAdjacentes = new ArrayList<>();
        this.arestasIncidentes = new ArrayList<>();
        this.data = data;
        this.label = label;
    }

    public void addArestaIncidente(Aresta e) {
        this.arestasIncidentes.add(e);
        this.verticesAdjacentes.add(e.verticeOposta(this));
    }

    public List<Vertice> getVerticesAdjacentes() {
        return this.verticesAdjacentes;
    }

    public void setVerticesAdjacentes(List<Vertice> verticesAdjacentes) {
        this.verticesAdjacentes = verticesAdjacentes;
    }

    public List<Aresta> getArestasIncidentes() {
        return this.arestasIncidentes;
    }

    public void setArestasIncidentes(List<Aresta> arestasIncidentes) {
        this.arestasIncidentes = arestasIncidentes;
    }

    public Integer getData() {
        return this.data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public double getDistancia() {
        return this.distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public Vertice getAnterior() {
        return this.anterior;
    }

    public void setAnterior(Vertice anterior) {
        this.anterior = anterior;
    }

    public boolean isVisitada() {
        return this.visitada;
    }

    public void setVisitada(boolean visitada) {
        this.visitada = visitada;
    }

    public String getLabel() {
        return this.label;
    }

    public Aresta getArestaNaoVisitada() {
        Optional<Aresta> o = this.arestasIncidentes.stream().filter(e -> e.isVisitada()).min((a,b) -> Double.compare(a.getPeso(), b.getPeso()));

                                                                    
        return o.isPresent() ? o.get() : null;
    }



}