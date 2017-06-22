package principal;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import extrator.Extrator;
import extrator.analisadorLexico.CriadorLexemas;
import extrator.analisadorSintatico.AnalisadorSintatico;
import extrator.entidades.Atributo;
import extrator.entidades.Classe;
import extrator.entidades.Entidade;
import extrator.entidades.Grupo;
import extrator.entidades.Interface;
import extrator.entidades.Metodo;

public class VerInformacoes {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        Extrator extrator = new Extrator("src/grupos");
               
        for(Grupo g : extrator.getGrupos()){
            
            if(!g.getEntidades().isEmpty() && g.getNome().equals("g2")){
            
                System.out.println("###########################################################################################################");    
                
                System.out.println("Grupo: " + g.getNome());
                
                System.out.println("###########################################################################################################");
                
                for(Entidade e : g.getEntidades()){
                    
                    System.out.println("===========================================================================================================");
                    
                    System.out.println(e.getNome() + "  Tipo: " + ((e instanceof Classe) ? "Classe" : "Interface") + " Pai: " + e.getPai());
                    
                    System.out.println("Abstrata: "+ e.isAbstrata());
                    System.out.println("Encapsulamento: "+ e.getEncapsulamento());
                    
                    System.out.println("===========================================================================================================");
                    
                    if(e instanceof Classe){
                        
                        Classe c = (Classe)e;
                        
                        if(!c.getAtributos().isEmpty()){
                            
                            System.out.println("Atributos");
                            
                            System.out.println("===========================================================================================================");
                            
                            for(Atributo a : c.getAtributos()){
                                System.out.println("Encapsulamento: "+ a.getEncapsulamento() + " Tipo: "+ a.getTipo() +" Nome: "+ a.getNome());
                            }
                            
                            System.out.println("===========================================================================================================");
                        }
                        
                        if(!c.getSubclasses().isEmpty()){
                        
                            System.out.println("Subclasses");
                            
                            System.out.println("===========================================================================================================");
                            
                            for(Classe subC : c.getSubclasses()){
                                System.out.println("Nome: "+ subC.getNome());
                            }
                            
                            System.out.println("===========================================================================================================");
                        }
                        
                        
                    }
                    
                    if(!e.getMetodos().isEmpty()){
                    
                        System.out.println("Métodos");
                        
                        System.out.println("===========================================================================================================");
                        
                        for (Metodo m : e.getMetodos()){
                            System.out.println("Encapsulamento: "+ m.getEncapsulamento() + " Retorno: " + m.getRetorno() + " Nome: " + m.getNome());
                        }
                        
                        System.out.println("===========================================================================================================");
                    }
                }
                
                System.out.println("###########################################################################################################");    
                
                System.out.println("toString: "+ "\n" + g.toString());
                
                System.out.println("Valores: " + g.atributosParaList());
                
                System.out.println("ValoresNormalizados: "+ g.getListNormalizacaoMaxMinEqual());
                
                System.out.println("###########################################################################################################");
                
            }
            
        }
        
        /*
        CriadorLexemas analisadorLexico = new CriadorLexemas();
        
        //System.out.println("Lexemas: " + leitor.getLexemas().toString());
        
        AnalisadorSintatico analisadorSintatico =  new AnalisadorSintatico();
        
        List<String> lexemas = analisadorLexico.criarLexemas("src/grupos/g1/PreferenceFragment.java");
        
        System.out.println(lexemas);
        
        //Classe c = (Classe)(analisadorSintatico.analisarLexemas(analisadorLexico.criarLexemas("src/grupos/g1/MainSettingsActivity.java")));
        
        
        Classe c = (Classe)(analisadorSintatico.analisarLexemas(lexemas));
        
        System.out.println("Nome:" + c.getNome());
        
        System.out.println("Abstrata: " +c.isAbstrata());
        
        System.out.println("Pai:" + c.getPai());
        
        for (Metodo m : c.getMetodos()){
            System.out.println("Retorno do método: " + m.getRetorno() + " Nome do método: " + m.getNome());
        }
        
        System.out.println("===============================================");
        
        for (Atributo t : c.getAtributos()){
            System.out.println("Tipo: "+ t.getTipo() +" Nome: "+ t.getNome());
        }
        
        System.out.println("===============================================");
        
        for(Classe subc : c.getSubclasses()){
            System.out.println("Classe: " + subc.getNome());
            
            for (Atributo t : subc.getAtributos()){
                System.out.println("Nome do atributo: " + t.getNome());
                System.out.println("tipo do atributo: " + t.getTipo());
                System.out.println("valor do atributo: " + t.getValor());
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
       */ 
    }
}
