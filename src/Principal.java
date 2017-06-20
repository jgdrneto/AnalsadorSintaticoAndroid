import java.io.FileNotFoundException;
import java.util.List;

import analisadorLexico.CriadorLexemas;
import analisadorSintatico.AnalisadorSintatico;
import nucleo.Atributo;
import nucleo.Classe;
import nucleo.Interface;
import nucleo.Metodo;

public class Principal {

    public static void main(String[] args) throws FileNotFoundException {
       
        CriadorLexemas leitor = new CriadorLexemas("src/af_1/PreferenceFragment.java");
        
        System.out.println("Lexemas: " + leitor.getLexemas().toString());
        
        AnalisadorSintatico analSint =  new AnalisadorSintatico(leitor.getLexemas());
        
        Classe c = (Classe)analSint.getEntidade();
        /*
        for (Metodo m : c.getMetodos()){
            System.out.println("Nome do método: " + m.getNome());
            System.out.println("Retorno do método: " + m.getRetorno());
        }
        */
        System.out.println("===============================================");
        
        for (Atributo t : c.getAtributos()){
            System.out.println("Tipo: "+ t.getTipo() +" Nome: "+ t.getNome());
        }
        
        
        
        System.out.println("===============================================");
        
        for(Classe subc : c.getSubclasses()){
            System.out.println("Classe: " + subc.getNome());
            
            
            for (Atributo t : subc.getAtributos()){
                System.out.println("Nome do método: " + t.getNome());
                System.out.println("tipo do método: " + t.getTipo());
                System.out.println("valor do método: " + t.getValor());
            }
            
            System.out.println("===============================================");
            
            for (Metodo t : subc.getMetodos()){
                System.out.println("Nome do método: " + t.getNome());
                System.out.println("Retorno do método: " + t.getRetorno());
            }
            
            System.out.println("===============================================");
        }
        
        for(Interface i : c.getInterfaces()){
            System.out.println("Tipo: "+ i.getNome() +" Nome 1º metodo: "+ i.getMetodos().get(0).getNome());
        }
        
    }
}
