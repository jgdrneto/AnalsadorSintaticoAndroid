package algoritmo.kmeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algoritmo.Conjunto;
import algoritmo.Ponto;

public class KNN {
    
    private int quantidadeConjuntos;
    private List<Ponto> pontos;
    
    public KNN(List<Ponto> nPontos,int nQuantidadeConjuntos){
        this.quantidadeConjuntos = nQuantidadeConjuntos;
        this.pontos = nPontos;
    }
        
    public double obterDistanciaEuclidiana(Ponto p1,Ponto p2){
        
        double soma=0;

        for(int i=0;i< p1.getValores().size();i++){
            soma += Math.pow(p1.getValores().get(i) - p2.getValores().get(i), 2);
        }
        
        return Math.sqrt(soma);
    }
    
    public List<Conjunto> run(){
        
        List<Conjunto> conjuntos = new ArrayList<Conjunto>();
        
        for(int i = 0;i<quantidadeConjuntos;i++){
            conjuntos.add(new Conjunto(i));
        }
        
        if(pontos.size()>=quantidadeConjuntos){
            
            classificacao();
        }
        
        
        for(Ponto p : pontos){
            conjuntos.get(p.getConjunto()).getPontos().add(p);
        }
        
        return conjuntos;
    }
    
    public void classificacao(){
                
        for(Ponto p : pontos){
           
           if(p.getConjunto()==-1){ 
               
               //System.out.println("===================================================================================");
               
               //System.out.println("Nome do ponto: " + p.getNome());
               
               List<Integer> conjuntos = new ArrayList<Integer>(quantidadeConjuntos); 
               
               for(int i =0;i<quantidadeConjuntos;i++){
                   conjuntos.add(0);
               }
               
               List<Double> distanciasParaVizinhos = calcularDistancias(pontos,p);
               
               //System.out.println("Distancias para vizinho: " + distanciasParaVizinhos);
               
               Ponto menorDeTodos=null;
               
               for(int i=0;i<quantidadeConjuntos;i++){
                   
                   double min = Collections.min(distanciasParaVizinhos);
                   
                   //System.out.println("Menor valor:" + min);
                           
                   int index = distanciasParaVizinhos.indexOf(min); 
                   
                   //System.out.println("Indice de menor valor:" + index);
                   
                   Ponto pzinho = pontos.get(index);
                   
                   //System.out.println("Ponto correspondente: " + pzinho.getNome());
                   
                   if(i==0){
                       menorDeTodos = pzinho;
                   }
                   
                   conjuntos.set(pzinho.getConjunto(), conjuntos.get(pzinho.getConjunto())+1);
                   
                   //System.out.println("Conjuntos Atualizado :" + conjuntos);
                   
                   distanciasParaVizinhos.set(index, Double.MAX_VALUE);
                   
                   //System.out.println("Distancias para vizinhos " + i + "º interação: "+ distanciasParaVizinhos);
               }
               
               
               Integer i = conjuntos.indexOf(Collections.max(conjuntos));
               
               //System.out.println("Indice de maior valor:"+ i);
   
               if(conjuntos.get(i)>conjuntos.get(menorDeTodos.getConjunto())){
                   p.setConjunto(i);
               }else{
                   p.setConjunto(menorDeTodos.getConjunto());
               }
               
               //System.out.println("Valor do conjunto:" + p.getConjunto());
           }
           
           //System.out.println("===================================================================================");
        }
    }

    private List<Double> calcularDistancias(List<Ponto> pontos2, Ponto p) {
        
        List<Double> distancias = new ArrayList<Double>();
        
        for(Ponto p2 : pontos){
            if(p.getId()!=p2.getId() && p2.getConjunto()!=-1){
                distancias.add(obterDistanciaEuclidiana(p, p2));
            }else{
                distancias.add(Double.MAX_VALUE);
            }
        }
        
        return distancias;
    }

    public List<Ponto> getPontos() {
        return pontos;
    }

}
