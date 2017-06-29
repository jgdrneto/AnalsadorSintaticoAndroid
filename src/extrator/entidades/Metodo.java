package extrator.entidades;

import java.util.ArrayList;
import java.util.List;

public class Metodo{
    
    private String nome;
    private String retorno;
    private String encapsulamento;
    private boolean abstrato;
    private boolean efinal;
    
    List<Parametro> parametros;
    
    List<String> conteudo;
    
    public Metodo(String nome, String retorno,String encapsulamento, boolean abstrato, boolean eFinal) {
        this.nome = nome;
        this.retorno = retorno;
        this.encapsulamento = encapsulamento;
        this.abstrato = abstrato;
        this.parametros = new ArrayList<Parametro>();
        this.conteudo = new ArrayList<String>();
    }
    
    public List<String> getConteudo() {
        return conteudo;
    }

    public void setConteudo(List<String> conteudo) {
        this.conteudo = conteudo;
    }
    
    public String getEncapsulamento() {
        return encapsulamento;
    }

    public void setEncapsulamento(String encapsulamento) {
        this.encapsulamento = encapsulamento;
    }

    public boolean isAbstrato() {
        return abstrato;
    }

    public void setAbstrato(boolean abstrato) {
        this.abstrato = abstrato;
    }

    public boolean isEfinal() {
        return efinal;
    }

    public void setEfinal(boolean efinal) {
        this.efinal = efinal;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getRetorno() {
        return retorno;
    }
    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }
    public List<Parametro> getParametros() {
        return parametros;
    }
    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

    @Override
    public String toString() {
        return "Metodo [nome=" + nome + ", retorno=" + retorno + ", abstrato=" + abstrato + ", parametros=" + parametros
                + "]";
    }
}
