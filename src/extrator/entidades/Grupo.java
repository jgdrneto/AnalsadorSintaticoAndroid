package extrator.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grupo {
    
    String nome;
    
    List<Entidade> entidades;
    
    int numAtributos;
    int numMetodos;
    int numInterfacesImplementadas;
    int numReferenciasEntreGrupo; 
    int numSubClasses;
    int numSubInterfaces;
    int numEntAbstrata;
    int numMetodosPublicos;
    int numMetodosPrivados;
    int numAtributosPublicos;
    int numAtributosPrivados;
    int numMetodosVoid;
    int numMetodosNaoVoid;
        
    public Grupo(String nNome,List<Entidade> nEntidades){
        
        entidades = nEntidades;
        
        nome = nNome;
        
        this.numAtributos=0;
        this.numMetodos=0;
        this.numInterfacesImplementadas=0;
        this.numReferenciasEntreGrupo=0; 
        this.numSubClasses=0;
        this.numSubInterfaces=0;
        this.numEntAbstrata=0;
        this.numMetodosPublicos=0;
        this.numMetodosPrivados=0;
        this.numAtributosPublicos=0;
        this.numAtributosPrivados=0;
        this.numMetodosVoid=0;
        this.numMetodosNaoVoid=0;
        
        gerarNumAtributos();
        gerarNumMetodo();
        gerarNumInterfacesImplementadas();
        gerarNumReferenciasEntreGrupo();
        gerarNumSubClasses();
        gerarNumSubInterfaces();
        gerarNumEntAbstrata();
        gerarNumsAtribMetodos();
        gerarNumAtributosPublicosEPrivados();        
    }

    public List<Double> getListNormalizacaoMaxMinEqual() {
        
        List<Integer> valoresBrutos = atributosParaList();
        
        Integer max = Collections.max(valoresBrutos); 
        Integer min = Collections.min(valoresBrutos);
        
        List<Double> valoresNormalizados = new ArrayList<Double>();
        
        for(Integer i : valoresBrutos){
            valoresNormalizados.add((new Double(i-min))/(max - min));
        }
        
        return valoresNormalizados;
    }
    
    public List<Double> getListNormalizacaoSigmoidal() {
        
        List<Integer> valoresBrutos = atributosParaList();
               
        List<Double> valoresNormalizados = new ArrayList<Double>();
        
        Double media = calcularMedia(valoresBrutos);
        
        Double desvioPadrao = Math.sqrt(calcularVariancia(valoresBrutos,media));
        
        for(Integer i : valoresBrutos){
            
            valoresNormalizados.add( 1/(1+Math.exp(-1*calcularZScore(i, media, desvioPadrao))));
        }
        
        return valoresNormalizados;
    }
    
    private double calcularZScore(Integer num,Double media,Double desvioPadrao){
        return (((double)num-media)/desvioPadrao);
    }
    
    private Double calcularMedia(List<Integer> valoresBrutos) {
        
        double media = 0;
        
        for(Integer i : valoresBrutos){
            media +=i;
        }
        
        return media/valoresBrutos.size();
    }
    
    private Double calcularVariancia(List<Integer> valoresBrutos, Double media) {
        
        double resultado=0;
        
        for(Integer i : valoresBrutos){
            resultado+=((double)(i-media))*((double)(i-media));
        }
        
        return resultado/(valoresBrutos.size()-1);
    }

    public List<Integer> atributosParaList(){
        
        List<Integer> valoresBrutos = new ArrayList<Integer>();
        
        valoresBrutos.add(this.numAtributos);
        valoresBrutos.add(this.numMetodos);
        valoresBrutos.add(this.numInterfacesImplementadas);
        valoresBrutos.add(this.numReferenciasEntreGrupo); 
        valoresBrutos.add(this.numSubClasses);
        valoresBrutos.add(this.numSubInterfaces);
        valoresBrutos.add(this.numEntAbstrata);
        valoresBrutos.add(this.numMetodosPublicos);
        valoresBrutos.add(this.numMetodosPrivados);
        valoresBrutos.add(this.numAtributosPublicos);
        valoresBrutos.add(this.numAtributosPrivados);
        valoresBrutos.add(this.numMetodosVoid);
        valoresBrutos.add(this.numMetodosNaoVoid);
        
        return valoresBrutos;
    }
    
    private void gerarNumAtributosPublicosEPrivados() {
        for(Entidade e : entidades){
            
            if(e instanceof Classe){
            
                Classe c = (Classe)e;    
                    
                for(Atributo a : c.getAtributos()){
                    if(a.getEncapsulamento().equals("public")){
                        this.numAtributosPublicos++;
                    }
                    if(a.getEncapsulamento().equals("private")){
                        this.numAtributosPrivados++;
                    }
                    
                }
            }
        }
        
    }
    
    private void gerarNumsAtribMetodos() {
        for(Entidade e : entidades){
            for(Metodo m : e.getMetodos()){
                if(m.getEncapsulamento().equals("public")){
                    this.numMetodosPublicos++;
                }
                if(m.getEncapsulamento().equals("private")){
                    this.numMetodosPrivados++;
                }
                if(m.getRetorno().equals("void")){
                    this.numMetodosVoid++;
                }else{
                    this.numMetodosNaoVoid++;
                }
            }
        }
        
    }

    private void gerarNumEntAbstrata() {
        for(Entidade e : entidades){
            
            if(e.isAbstrata()){
                this.numEntAbstrata ++;
            }
        }
    }

    private void gerarNumSubInterfaces() {
        for(Entidade e : entidades){
            this.numSubInterfaces += e.getInterfaces().size();
        }
    }

    private void gerarNumSubClasses() {
        
        for(Entidade e : entidades){
            
            if(e instanceof Classe){
                
                Classe c = (Classe)e;
                
                this.numSubClasses += c.getSubclasses().size();  
            }
        }
    }    
    private void gerarNumReferenciasEntreGrupo() {
        for(Entidade e : entidades){
            this.numReferenciasEntreGrupo += verReferencias(e); 
        }
    }

    private int verReferencias(Entidade e) {
        
        int valor=0;
        
        for(Entidade oEnt : entidades){
            
            if(!oEnt.getNome().equals(e.getNome())){
                valor+= auxVerReferencias(e.getNome(),oEnt); 
            }
        }
        
        return valor;
    }

    private int auxVerReferencias(String nome, Entidade e) {
        
        int cont=0;
        
        for(Metodo m : e.getMetodos()){
            if(m.getRetorno().equals(nome)){
                cont++;
            }
            
            for(Parametro p : m.getParametros()){
                if(p.getTipo().equals(nome)){
                    cont++;
                }
            }
            
            cont+=Collections.frequency(m.getConteudo(), nome);
        }
        
        cont += Collections.frequency(e.getNomesInterfacesImp(), nome);
        
        if(e instanceof Classe){
            
            Classe c = (Classe)e;
            
            for(Atributo a : c.getAtributos()){
                if(a.getTipo().equals(nome)){
                    cont++;
                }
                
                if(a.getValor().equals(nome)){
                    cont++;
                }
                
            }
        }
        
        return cont;
    }
    
    private void gerarNumMetodo() {
        for(Entidade e : entidades){
            this.numMetodos += e.getMetodos().size(); 
        }
    }

    private void gerarNumAtributos() {
        for(Entidade e : entidades){
             if(e instanceof Classe){
                 Classe c =  (Classe)e;
                 
                 this.numAtributos+=c.getAtributos().size();
             }
        }
    }

    private void gerarNumInterfacesImplementadas() {
        for(Entidade e : entidades){
             this.numInterfacesImplementadas =  e.getNomesInterfacesImp().size();
        }
    }
    
    public String getNome() {
        return nome;
    }

    public List<Entidade> getEntidades() {
        return entidades;
    }

    public int getNumAtributos() {
        return numAtributos;
    }

    public int getNumMetodos() {
        return numMetodos;
    }

    public int getNumInterfacesImplementadas() {
        return numInterfacesImplementadas;
    }

    public int getNumReferenciasEntreGrupo() {
        return numReferenciasEntreGrupo;
    }

    public int getNumSubClasses() {
        return numSubClasses;
    }

    public int getNumSubInterfaces() {
        return numSubInterfaces;
    }

    public int getNumEntAbstrata() {
        return numEntAbstrata;
    }

    public int getNumMetodosPublicos() {
        return numMetodosPublicos;
    }

    public int getNumMetodosPrivados() {
        return numMetodosPrivados;
    }

    public int getNumAtributosPublicos() {
        return numAtributosPublicos;
    }

    public int getNumAtributosPrivados() {
        return numAtributosPrivados;
    }

    public int getNumMetodosVoid() {
        return numMetodosVoid;
    }

    public int getNumMetodosNaoVoid() {
        return numMetodosNaoVoid;
    }

    @Override
    public String toString() {
        return "Grupo ["+ "\n" +
                      "nome: " + nome +"\n" + 
                      "numAtributos: " + numAtributos + "\n" + 
                      "numMetodos: " + numMetodos + "\n" +
                      "numInterfacesImplementadas: " + numInterfacesImplementadas +"\n" +
                      "numReferenciasEntreGrupo: "+ numReferenciasEntreGrupo + "\n" +
                      "numSubClasses: " + numSubClasses + "\n" +
                      "numSubInterfaces: "+ numSubInterfaces + "\n" +
                      "numEntAbstrata: " + numEntAbstrata + "\n" +
                      "numMetodosPublicos: " + numMetodosPublicos + "\n" +
                      "numMetodosPrivados: " + numMetodosPrivados + "\n" +
                      "numAtributosPublicos: " + numAtributosPublicos + "\n" +
                      "numAtributosPrivados: " + numAtributosPrivados + "\n" +
                      "numMetodosVoid: " + numMetodosVoid + "\n" +
                      "numMetodosNaoVoid: " + numMetodosNaoVoid + "\n"+
                      "]";
    }
    
    
    
}
