package extrator.entidades;

import java.util.List;

public class Grupo {
    
    List<Entidade> entidades;
    
    int numAtributos;
    int numMetodos;
    int interfacesImplementas;
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
    
    public Grupo(List<Entidade> nEntidades){
        
        entidades = nEntidades;
        
        gerarNumAtributos();
                
    }

    private void gerarNumAtributos() {
        
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

    public int getInterfacesImplementas() {
        return interfacesImplementas;
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

}
