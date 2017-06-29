package algoritmo;

import java.util.List;

public class Ponto {
    
    private List<Double> valores;
    private int conjunto;
    private String nome;
    private int id;
    
    public Ponto(int nId,String nNome,List<Double> nValores) {
        this.id = nId;
        this.nome= nNome;
        this.valores = nValores;
        conjunto=-1;
    }

    public List<Double> getValores() {
        return valores;
    }

    public int getConjunto() {
        return conjunto;
    }

    public void setConjunto(int conjunto) {
        this.conjunto = conjunto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
