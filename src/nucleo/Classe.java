package nucleo;

import java.util.ArrayList;
import java.util.List;

public class Classe extends Entidade{
        
    public Classe(String encapsulamento, String nome, boolean entFinal, boolean abstrata, boolean estatica) {
        super(encapsulamento, nome, entFinal, abstrata,estatica);
        
        subclasses = new ArrayList<Classe>();
        atributos = new ArrayList<Atributo>();
    }
      
    List<Atributo> atributos;
    
    List<Classe> subclasses;
    
    public Classe(String encapsulamento, boolean entFinal, boolean abstrata,boolean estatica) {
        super(encapsulamento, entFinal, abstrata,estatica);
        
        subclasses = new ArrayList<Classe>();
        atributos = new ArrayList<Atributo>();
    }
    
    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    public List<Classe> getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(List<Classe> subclasses) {
        this.subclasses = subclasses;
    }

}
