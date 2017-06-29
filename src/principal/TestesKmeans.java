package principal;

import java.util.ArrayList;
import java.util.List;

import algoritmo.Conjunto;
import algoritmo.Ponto;
import algoritmo.kmeans.KNN;
import algoritmo.kmeans.Kmeans;
import extrator.Extrator;
import extrator.entidades.Grupo;

public class TestesKmeans {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Extrator extrator = new Extrator("src/grupos");
        
        List<Ponto> pontos = new ArrayList<Ponto>();
        
        int i=0;
        
        for(Grupo g : extrator.getGrupos()){
            pontos.add(new Ponto(i,g.getNome(),g.getListNormalizacaoSigmoidal()));
            i++;
        }
        
        Kmeans kmeans = new Kmeans(3, pontos, 1000);
        
        kmeans.run();
        
    }

}
