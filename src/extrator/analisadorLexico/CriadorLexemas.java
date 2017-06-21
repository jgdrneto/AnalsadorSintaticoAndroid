package extrator.analisadorLexico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CriadorLexemas {
    
    public List<String> criarLexemas(String nomeArquivo){
        
        List<String> lexemas = new ArrayList<String>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
                
            while(br.ready()){
                
                String s = br.readLine();
                
                while(s.startsWith(" ")){
                    s=s.substring(1);
                }
                
                //Excluir comentário em posições diferentes
                if(s.contains("//")){
                    s=s.substring(0, s.indexOf("//"));
                }
                //Excluir comentarios no começo da linha
                if(!s.startsWith("//")){
                    lexemas.addAll(quebrarString(s));
                }

            }
            
            br.close();
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return lexemas;
    }
    
    public List<String> quebrarString(String l){
                
        List<String> retorno =  new ArrayList<String>();
        
        //String temp="";
        
        l = l.replaceAll("\\/", " \\/ ");
        l = l.replaceAll("\\(", " \\( ");
        l = l.replaceAll("\\)", " \\) ");
        l = l.replaceAll("\\{", " \\{ ");
        l = l.replaceAll("\\}", " \\} ");
        l = l.replaceAll("\\,", " \\, ");
        l = l.replaceAll("\\;", " \\; ");
        l = l.replaceAll("\\=", " \\= ");
        l = l.replaceAll("\\*", " \\* ");
        
        String[] semEspacos = l.split("\\s");
        
        for(String s : semEspacos){
            if(!s.isEmpty() && !s.startsWith("@")){
                retorno.add(s);
            }
        }

        /*
        for(String nl : semEspacos){
        
            for(char c : nl.toCharArray()){
                 
                switch (String.valueOf(c)){
                   case "/":
                   case "(":
                   case ")":
                   case "{":
                   case "}":
                   case ",":    
                   case ";":
                   case "=":
                   case "\"":    
                       if(!temp.isEmpty()){
                           retorno.add(temp);
                           temp="";
                       }
                       retorno.add(String.valueOf(c));
                   break;
                   case " ":
                       if(!temp.isEmpty()){
                           retorno.add(temp);
                           temp="";
                       }
                   break;
                   default:
                       temp+=c;
                   break;
                }
            }
        }*/    
        return retorno;
    }
    
}
