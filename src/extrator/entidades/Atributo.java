package extrator.entidades;

public class Atributo {
    String tipo;
    String nome;
    String valor;
    String encapsulamento;
    boolean estatica;
    boolean entFinal;
    
    public Atributo(String tipo, String nome,String valor,String encapsulamento,boolean estatica,boolean entFinal) {
        this.tipo = tipo;
        this.nome = nome;
        this.valor = valor;
        this.encapsulamento = encapsulamento;
        this.estatica = estatica;
        this.entFinal = entFinal;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getValor() {
        return valor;
    }
    
    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getEncapsulamento() {
        return encapsulamento;
    }

    public boolean isEstatica() {
        return estatica;
    }

    public boolean isEntFinal() {
        return entFinal;
    }
    
}
