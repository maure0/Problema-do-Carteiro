import java.util.List;

public class MatrizDistancia {

    private List<Vertice> vertices;
    private double[][] matriz;
    private int a = -1;
    private int b = -1;

    public MatrizDistancia(List<Vertice> v) {
        this.vertices = v;
        matriz = new double[v.size()][v.size()];
    }

    public void setDistancia(int i, int j, double d) {
        matriz[i][j] = d;
    }

    public double getMenorDistancia() {
        double menor = Double.MAX_VALUE;
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (i != j && getDistancia(i, j) < menor) {
                    this.a = i;
                    this.b = j;
                }
                ;
            }
        }

        return menor;
    }

    public double getDistancia(int i, int j) {
        return this.matriz[i][j];
    }

    public int size() {
        return this.vertices.size();
    }

    public void print() {

        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i).getLabel() + " ");
        }

        System.out.println();

        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i).getLabel() + " ");
            for (int j = 0; j < vertices.size(); j++) {
                System.out.print(getDistancia(i, j) + " ");
            }
            System.out.println();
        }
    }

    public Vertice[] menorPar() {
        getMenorDistancia();
        return new Vertice[] { this.vertices.get(a), this.vertices.get(b) };
    }

    public void remove(Vertice v) {
        int index = this.vertices.indexOf(v);
        this.vertices.remove(index);

        double[][] m = new double[size()][size()];


        System.out.println();
        System.out.println();
        System.out.println();

        int k = 0, l = 0;
        int x = 1;
        for(int i = 0; i < matriz.length; i++) {
            if(i == index) continue;
            for(int j = 0; j < matriz.length; j++) {
                if(j == index) continue;
                System.out.println((x++) + ": " + k + " " + l);
                m[k][l] = matriz[i][j];
                l++;
            }
            l = 0;
            k++;
        }
        matriz = m;
        print();

    }

}
