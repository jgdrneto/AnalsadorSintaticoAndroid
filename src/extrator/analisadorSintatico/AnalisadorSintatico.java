package extrator.analisadorSintatico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import extrator.entidades.Atributo;
import extrator.entidades.Classe;
import extrator.entidades.Entidade;
import extrator.entidades.Interface;
import extrator.entidades.Metodo;
import extrator.entidades.Parametro;

public class AnalisadorSintatico{
    
    public Entidade analisarLexemas(List<String> nlexemas){
        
        Entidade entidade = null;
        
        List<String> fita = new ArrayList<String>(nlexemas);
        
        String al="";
        
        while(!fita.isEmpty()){
                        
            al = fita.remove(0);
            
            switch(al){
                case "/":
                    //System.out.println("Comentário");
                    comentario(fita);
                break;
                case "package":
                    //System.out.println("Pacote");
                    pacote(fita);
                break;
                case "import":
                    //System.out.println("Importe");
                    importe(fita);
                break;
                case "}":
                    //System.out.println("Fim da classe");
                break;    
                default:
                    //System.out.println("Entidade");
                    fita.add(0,al);
                    entidade = entidade(fita);
                break;
                
            }
        }
        
        if(!fita.isEmpty()){
            al=fita.get(0);
        }else{
            al="";
        }
        /*
        System.out.println("Cabeçote: " + al);
        System.out.println("Fita: "+ fita.toString());
        System.out.println("Fim do arquivo");
        */
        
        return entidade;
    }
    
    private Entidade entidade(List<String> fita) {
        
        System.out.println(fita.toString());
        
        Entidade e = null;
        String encapsulamento="defaut";
        boolean abstrata = false;
        boolean entFinal = false;
        boolean estatica = false;
        
        while(!fita.isEmpty() && !(fita.size()==1 && fita.get(0).equals("}"))){
            
            String tipo = fita.remove(0);
            
            switch(tipo){
                case "public":
                case "private":
                case "protected":
                    encapsulamento = tipo;    
                break;
                case "final":
                    entFinal = true;
                break;    
                case "abstract":
                    abstrata=true;
                break;
                case "static":
                    estatica = true;
                break;    
                case "class":
                   e = definirEntidade(new Classe(encapsulamento,abstrata, entFinal,estatica),fita);
                break;    
                case "interface":
                    e = definirEntidade(new Interface(encapsulamento,abstrata, entFinal),fita);
                break;
            }

        }
        
        return e;
    }
   
    
    private Entidade definirEntidade(Entidade entidade, List<String> fita) {
        
        if(!fita.isEmpty()){

            while(!fita.isEmpty() && !fita.get(0).equals("{")){
                String al = fita.remove(0);
                
                switch(al){ 
                    case "extends":
                        if(!fita.isEmpty()){
                            entidade.setPai(fita.remove(0));
                        }
                    break;
                    case "implements":
                        entidade.setNomesInterfacesImp(interfacesClasse(fita));
                    break;
                    default:
                        entidade.setNome(al);
                    break;    
                }
                
            }
            
            if(!fita.isEmpty()&& fita.get(0).equals("{")){
                fita.remove(0);
            }
            

            interpretarEntidade(entidade, fita);
            
            for(int i=0;i<entidade.getComandos().size();i++){
                
                System.out.println("=========================================================");
                
                System.out.println("Comando: " + entidade.getComandos().get(i).toString());
                
                interpretarComando(entidade,entidade.getComandos().get(i));
                 
                System.out.println("=========================================================");
            }
        }
        
        return entidade;
        
    }
    
    private void interpretarComando(Entidade entidade, List<String> nLexemas) {
        
        String encapsulamento="default";
        boolean entFinal=false;
        boolean abstrata = false;
        boolean estatica = false;
        
        while(!nLexemas.isEmpty() && !(nLexemas.size()==1 && nLexemas.get(0).equals("}")) && !(nLexemas.size()==1 && nLexemas.get(0).equals(";"))){
            
            String tipo = nLexemas.remove(0);
            
            switch(tipo){
                case "public":
                case "private":
                case "protected":
                    encapsulamento = tipo;    
                break;
                case "final":
                    entFinal = true;
                break;
                case "static":
                    estatica = true;
                break; 
                case "abstract":
                    abstrata=true;
                break;    
                case "class":
                    Classe c = (Classe)entidade;
                    c.getSubclasses().add((Classe)definirEntidade(new Classe(encapsulamento,abstrata, entFinal, estatica),nLexemas));
                break;    
                case "interface":
                    entidade.getInterfaces().add((Interface)definirEntidade(new Interface(encapsulamento,abstrata, entFinal),nLexemas));
                break;
                default:
                    nLexemas.add(0, tipo); 
                    determinarCampo(entidade,encapsulamento,abstrata, entFinal,estatica,nLexemas);
                break;
            }
            
        }
        
        if(!nLexemas.isEmpty()&& nLexemas.get(0).equals("{")){
            nLexemas.remove(0);
        }
    }

    private void determinarCampo(Entidade entidade, String encapsulamento, boolean abstrata, boolean entFinal,
                                 boolean estatica, List<String> nLexemas) {
        
        List<String> campos = new ArrayList<String>();
        
        
        //System.out.println("Fita: "+ nLexemas);
        
        while(!nLexemas.isEmpty() && !nLexemas.get(0).equals("(") && !nLexemas.get(0).equals("=") && !nLexemas.get(0).equals(";")){
            
            campos.add(nLexemas.remove(0));
            
        }
         
        //System.out.println("Fita Termino: "+ nLexemas);
        
        //System.out.println("Cabeçote: "+ nLexemas.get(0));
        
        switch(nLexemas.get(0)){
            case "(":
                
                Metodo m = null;
                
                if(campos.size()==1){
                
                    m = new Metodo(campos.get(0),campos.get(0),abstrata,entFinal);
                }else{
                    
                    m = new Metodo(campos.get(1),campos.get(0),abstrata,entFinal);
                }
                
                //System.out.println("Fita antes de interpretar parametros: "+ nLexemas);
                
                m.setParametros(interpretarParametros(nLexemas));
                
                //System.out.println("Fita depois de interpretar parametros: "+ nLexemas);
                
                //System.out.println("Fita antes de conteudo: "+ nLexemas);
                
                m.setConteudo(interpretarConteudoMet(nLexemas));
                
                //System.out.println("Fita depois de conteudo: "+ nLexemas);
                
                entidade.getMetodos().add(m);                
            break;
            case "=":
               
                Atributo a = new Atributo(campos.get(0),campos.get(1),setValorAtributo(nLexemas));
                
                Classe c = (Classe)entidade;
                
                c.getAtributos().add(a);
                
            break;
            case ";":
                
                //System.out.println("Fita: "+ nLexemas);
                
                Atributo an = new Atributo(campos.get(0),campos.get(1),"nulo");
                
                Classe cn = (Classe)entidade;
                
                cn.getAtributos().add(an);
                
            break;    
            
        }
                
    }

    private List<String> interpretarConteudoMet(List<String> nLexemas) {
        
        List<String> conteudo =  new ArrayList<String>();
        
        if(nLexemas.size()>=2){
            nLexemas.remove(0);
            
            nLexemas.remove(nLexemas.size()-1);
            
            conteudo =  new ArrayList<String>(nLexemas);
            
            nLexemas.clear();
        }
        
        return conteudo;
    }

    private String setValorAtributo(List<String> nLexemas) {
        String lex="";
        String valor="";
        
        List<String> aLex = new ArrayList<String>();
        
        while(!nLexemas.isEmpty() && valor.isEmpty()){
            lex = nLexemas.remove(0);
            
            switch(lex){
                case "new":
                    if(!nLexemas.isEmpty()){
                        valor = (nLexemas.remove(0));
                    }
                break;
                default:
                    aLex.add(lex);
                break;    
            }
        }
        
        if(valor.isEmpty()){
            
            if(aLex.contains("(")){
                valor = "Objeto";
            }else{
                valor = "primitivo";
            }
               
        }
        
        nLexemas.clear();
        
        return valor;
    }

    private List<Parametro> interpretarParametros(List<String> nLexemas) {
        
        List<Parametro> atributos =new ArrayList<Parametro>();
        
        String lex;
  
        List<String> valores = new ArrayList<>();
        
                
        while(!nLexemas.isEmpty() && !nLexemas.get(0).equals("{")){
           
           lex = nLexemas.remove(0);
            
           if(!lex.equals(",") || 
              !lex.equals("(") ||
              !lex.equals(")")){ 
               valores.add(lex);
           }
  
        }
        
        for(int i=0;i<valores.size()/2;i=i+2){
            atributos.add(new Parametro(valores.get(i),valores.get(i+1)));
        }
        
        return atributos;
        
    }

    private void interpretarEntidade(Entidade entidade, List<String> fita) {
        
        List<List<String>> comandos =  new ArrayList<>();
        
        List<String> comando =  new ArrayList<String>();
        
        String s;
        
        int contChave=0;
        
        while(!fita.isEmpty() && !(fita.size()==1 && fita.get(0).equals("}"))){
            
            //System.out.println("Fita: " + fita);
            
            
            /*
            try {
                System.in.read();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            */
            s = fita.remove(0);
            
            //System.out.println("Cabeçote: " + s);
            
            //System.out.println("comando: " + comando);
            
            comando.add(s);
            
            switch(s){
                case ";":
                    if(contChave==0){
                        if(comando.size()!=1){
                            comandos.add(comando);
                            comando =  new ArrayList<String>();
                        }else{
                           comando.remove(0);     
                        }
                    }
                break;
                case "{":
                    contChave++;
                break;
                case "}":
                    contChave--;
                    
                    if(contChave==0){                        
                        comandos.add(comando);
                        comando =  new ArrayList<String>();
                    }
                    
                break;
                case "/":
                    
                    if(!fita.isEmpty() && (fita.get(0).equals("/") || fita.get(0).equals("*"))){
                        comando.remove(0);
                        comentario(fita);
                    }
                    
                break;    
                
            }            
        }
        
        entidade.setComandos(comandos);
        
        //System.out.println("Saindo de interpretar comando");
        
    }

    private List<String> interfacesClasse(List<String> fita){

        List<String> interfaces = new ArrayList<String>();

        String al="";
        
        while(!fita.isEmpty() && !fita.get(0).equals("{")){
            al = fita.remove(0);
            
            if(!al.equals(",")){
                
                interfaces.add(al);
            }
        }
        
        //System.out.println("Valor de saida da fita  de interfaces:" + fita.toString());
        
        return interfaces;
    }
    
    private void importe(List<String> fita){
       while(!fita.isEmpty() && !fita.remove(0).equals(";")){
           //Nada msm
       }
    }
    
    private void pacote(List<String> fita){
        while(!fita.isEmpty() && !fita.remove(0).equals(";")){
            //Nada aqui
        }
    }
    
    private void comentario(List<String> fita){
        
        String terminoDeComentario="";
        
        while(!fita.isEmpty() && (!terminoDeComentario.equals("*/"))){
            
            String s = fita.remove(0);
            
            if(s.equals("*") || s.equals("/")){
                terminoDeComentario+=s;
            }else{
                terminoDeComentario="";
            }
            /*
            if(!terminoDeComentario.isEmpty()){
                System.out.println("Termino de comentario: "+ terminoDeComentario);
            } 
            */
            
        }    
    }

}
