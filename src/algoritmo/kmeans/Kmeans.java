package algoritmo.kmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmo.Conjunto;
import algoritmo.Ponto;

public class Kmeans {
    
    List<Ponto> pontos;
    private int numeroDeConjuntos;
    private int totalValores;
    private int numeroInteracoes;
    private int maxInteracoes;
    
    public Kmeans(int nNumeroDeConjunto,List<Ponto> nPontos,int nMaxInteracoes) {
        this.totalValores = nPontos.get(0).getValores().size();
        this.numeroDeConjuntos = nNumeroDeConjunto;
        this.pontos = nPontos;
        this.maxInteracoes = nMaxInteracoes;
       
    }
    
    int getIDNearestCenter(List<Conjunto> conjuntos,Ponto ponto){
        
        double sum = 0, min_dist;
        int id_conjunto_central = 0;

        for(int i = 0; i < this.totalValores; i++){
            sum += Math.pow(conjuntos.get(0).getCentralDeValores().get(i) -
                       ponto.getValores().get(i), 2.0);
        }

        min_dist = Math.sqrt(sum);

        for(int i = 1; i < numeroDeConjuntos; i++){
            double dist;
            sum = 0;

            for(int j = 0; j < totalValores; j++) {
                sum += Math.pow(conjuntos.get(i).getCentralDeValores().get(j) -
                           ponto.getValores().get(j), 2.0);
            }

            dist = Math.sqrt(sum);

            if(dist < min_dist){
                min_dist = dist;
                id_conjunto_central = i;
            }
        }

        return id_conjunto_central;
    }
    
    public List<Conjunto> run(){
        
        List<Conjunto> resultado = new ArrayList<Conjunto>(this.numeroDeConjuntos);
        
        Random r = new Random();
        
        if(totalValores <= pontos.size()){
        }    
        
        List<Integer> indicesProibidos =  new ArrayList<Integer>();

        // choose K distinct values for the centers of the clusters
        for(int i = 0; i < totalValores; i++){
            while(true){
                
                int idPonto = r.nextInt(pontos.size()) ;

                if(!indicesProibidos.contains(idPonto)){
                    
                    indicesProibidos.add(idPonto);
                    pontos.get(idPonto).setConjunto(i);
                    Conjunto conj = new Conjunto(i,pontos.get(idPonto));
                    resultado.add(conj);
                    break;
                }
            }
        }

        int iter = 1;

        while(true){
            boolean done = true;

            // associates each point to the nearest center
            for(int i = 0; i < pontos.size(); i++){
                
                int idConjuntoAntigo = pontos.get(i).getConjunto();
                int idCentroMProx = getIDNearestCenter(resultado,pontos.get(i));

                if(idConjuntoAntigo != idCentroMProx){
                    
                    if(idConjuntoAntigo != -1){
                        resultado.get(idConjuntoAntigo).removerPonto(pontos.get(i).getId());
                    } 
                    
                    pontos.get(i).setConjunto(idCentroMProx);
                    resultado.get(idCentroMProx).getPontos().add(pontos.get(i));
                    done = false;
                }
            }

            // recalculating the center of each cluster
            for(int i = 0; i < numeroDeConjuntos; i++){
                
                for(int j = 0; j < totalValores; j++){
                    
                    int pontosTotaisConjunto = resultado.get(i).getPontos().size();
                    double sum = 0;

                    if(pontosTotaisConjunto > 0){
                        for(int p = 0; p < pontosTotaisConjunto; p++){
                            sum += resultado.get(i).getPontos().get(p).getValores().get(j);
                        }
                        resultado.get(i).getCentralDeValores().set(j, sum/pontosTotaisConjunto);
                    }
                }
            }

            if(done == true || iter >= maxInteracoes){
                System.out.println("Parado na interação "+ iter + "\n\n");
                break;
            }

            iter++;
        }

        // shows elements of clusters
        for(int i = 0; i < numeroDeConjuntos; i++){
            
            int total_points_cluster =  resultado.get(i).getPontos().size();

            System.out.println("Conjuntos: " + (resultado.get(i).getId() + 1));
            for(int j = 0; j < total_points_cluster; j++){
                
                String nomeDoPonto = resultado.get(i).getPontos().get(j).getNome();
                
                System.out.print("Grupo " + nomeDoPonto + ": ");
                
                for(int p = 0; p < totalValores; p++){
                    System.out.print(resultado.get(i).getPontos().get(j).getValores().get(p) + " ");
                }    
                
                System.out.println("");
            }
            
            System.out.println("\nValores do conjuntos: ");

            for(int j = 0; j < totalValores; j++){
                System.out.println(resultado.get(i).getCentralDeValores().get(j));
            }    
            System.out.println("\n");
        }
       
        return resultado;
    }
    
}
