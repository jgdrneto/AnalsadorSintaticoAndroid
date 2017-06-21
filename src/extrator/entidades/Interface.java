package extrator.entidades;

import java.util.ArrayList;
import java.util.List;

public class Interface extends Entidade{

    public Interface(String encapsulamento, String nome, boolean entFinal, boolean abstrata) {
        super(encapsulamento, nome, entFinal, abstrata,false);
    }
    
    public Interface(String encapsulamento, boolean entFinal, boolean abstrata) {
        super(encapsulamento, entFinal, abstrata,false);
    }
    
}
