package principal;

import java.util.ArrayList;
import java.util.List;

import algoritmo.Conjunto;
import algoritmo.Ponto;
import algoritmo.kmeans.KNN;
import algoritmo.kmeans.Kmeans;
import extrator.Extrator;
import extrator.entidades.Grupo;

public class TestesKNN {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Extrator extrator = new Extrator("src/grupos");
        
        List<Ponto> pontos = new ArrayList<Ponto>();
        
        int i=0;
        
        for(Grupo g : extrator.getGrupos()){
            pontos.add(new Ponto(i,g.getNome(),g.getListNormalizacaoSigmoidal()));
            i++;
        }

        for(Ponto p : pontos){
            switch(p.getNome()){
                case "g1":
                case "g2":
                case "g3":
                case "g4":
                    p.setConjunto(0);
                break;
                case "g6":
                case "g7":
                case "g8":
                case "g9":
                    p.setConjunto(1);
                break;    
                case "g11":
                case "g12":
                case "g13":
                case "g14":
                    p.setConjunto(2);
                break;    
            }
        }
        
        KNN KNN = new KNN(pontos,3);
        
        List<Conjunto> conjuntos = KNN.run();
        
        for(Conjunto c : conjuntos){
            System.out.println(c.toString());
        }
        
    }

}
