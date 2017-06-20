package nucleo;

import java.util.ArrayList;
import java.util.List;

public abstract class Entidade {
    String encapsulamento;
    
    String nome;
    
    List<Metodo> metodos;
        
    boolean entFinal;
    
    boolean abstrata;
    
    boolean estatica;
    
    List<List<String>> comandos;
    
    List<Interface> interfaces;
    
    List<String> interfacesImp;
    
    String pai;
    
    public Entidade(String encapsulamento, boolean entFinal, boolean abstrata, boolean estatica) {
        this.encapsulamento = encapsulamento;
        this.entFinal = entFinal;
        this.abstrata = abstrata;
        this.estatica = estatica;
        
        metodos = new ArrayList<Metodo>();
        interfaces = new ArrayList<Interface>();
        interfacesImp = new ArrayList<String>();
    }

    public Entidade(String encapsulamento, String nome, boolean entFinal, boolean abstrata, boolean estatica) {
        this.encapsulamento = encapsulamento;
        this.nome = nome;
        this.entFinal = entFinal;
        this.abstrata = abstrata;
        this.estatica = estatica;
        
        metodos = new ArrayList<Metodo>();
        interfaces = new ArrayList<Interface>();
        interfacesImp = new ArrayList<String>();
    }
    
    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }
    
    public String getEncapsulamento() {
        return encapsulamento;
    }

    public void setEncapsulamento(String encapsulamento) {
        this.encapsulamento = encapsulamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Metodo> getMetodos() {
        return metodos;
    }

    public void setMetodos(List<Metodo> metodos) {
        this.metodos = metodos;
    }

    public boolean isEntFinal() {
        return entFinal;
    }

    public void setEntFinal(boolean entFinal) {
        this.entFinal = entFinal;
    }

    public boolean isAbstrata() {
        return abstrata;
    }

    public void setAbstrata(boolean abstrata) {
        this.abstrata = abstrata;
    }

    public List<List<String>> getComandos() {
        return comandos;
    }

    public void setComandos(List<List<String>> comandos) {
        this.comandos = comandos;
    }

    public List<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
    }
    
    public List<String> getNomesInterfacesImp() {
        return interfacesImp;
    }

    public void setNomesInterfacesImp(List<String> interfaces) {
        this.interfacesImp = interfaces;
    }

    public boolean isEstatica() {
        return estatica;
    }

    public void setEstatica(boolean estatica) {
        this.estatica = estatica;
    }
    
}
