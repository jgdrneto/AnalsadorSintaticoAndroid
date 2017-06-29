package algoritmo;

import java.util.ArrayList;
import java.util.List;

public class Conjunto {
    
    List<Double> centralDeValores;
    List<Ponto> pontos;
    int id;
    
    public Conjunto(int nId, Ponto ponto) {
        this.id = nId;
        this.pontos = new ArrayList<Ponto>();
        this.centralDeValores = new ArrayList<Double>(ponto.getValores());
        
        pontos.add(ponto);
        
    }
    
    public Conjunto(int nId){
        this.id=nId;
        pontos = new ArrayList<Ponto>();
    }
    
    public List<Double> getCentralDeValores() {
        return centralDeValores;
    }

    public void setCentralDeValores(List<Double> centralDeValores) {
        this.centralDeValores = centralDeValores;
    }

    public void setPontos(List<Ponto> pontos) {
        this.pontos = pontos;
    }

    public List<Ponto> getPontos() {
        return pontos;
    }
    
    public boolean removerPonto(int id){
        for(int i=0;i<pontos.size();i++){
            if(pontos.get(i).getId()==id){
                
                pontos.remove(pontos.get(i));
                
                return true;
            }
        }
        
        return false;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        
        String retorno="Conjunto "+ this.id +"\n"+
                       "Pontos: " + "\n";
        
        for(Ponto p : pontos){
            retorno+= "Nome: " + p.getNome() + " " + p.getValores() + "\n";
        }
        
        return retorno;
    }
    
    
    
}
