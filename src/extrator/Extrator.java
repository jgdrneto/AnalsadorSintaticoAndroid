package extrator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import extrator.analisadorLexico.CriadorLexemas;
import extrator.analisadorSintatico.AnalisadorSintatico;
import extrator.entidades.Entidade;
import extrator.entidades.Grupo;

public class Extrator {
    
    private List<Grupo> grupos;
    private CriadorLexemas analisadorLexico;
    private AnalisadorSintatico analisadorSintatico;
    
    public Extrator(String nomeDiretorioRaiz){
        
        grupos = new ArrayList<Grupo>();
        
        analisadorLexico = new CriadorLexemas();
        analisadorSintatico = new AnalisadorSintatico();
        
        List<Entidade> grupo = new ArrayList<Entidade>();
        
        File diretorioRaiz = new File(nomeDiretorioRaiz);
        
        if(diretorioRaiz.exists() && diretorioRaiz.isDirectory()){
        
            for(File diretorioGrupo : diretorioRaiz.listFiles()){
                
                if(diretorioGrupo.exists() && diretorioGrupo.isDirectory()){
                    
                    for(File arquivo : diretorioGrupo.listFiles()){
                        
                        grupo.add(analisadorSintatico.analisarLexemas(analisadorLexico.criarLexemas(arquivo.getAbsolutePath())));
                        
                    }
                    
                }
                
                grupos.add(new Grupo(diretorioGrupo.getName(),new ArrayList<Entidade>(grupo)));
                
                grupo.clear();
            }
            
        }else{
            System.out.println("Diretório não encontrado ou não é um diretório");
        }
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public CriadorLexemas getAnalisadorLexico() {
        return analisadorLexico;
    }

    public void setAnalisadorLexico(CriadorLexemas analisadorLexico) {
        this.analisadorLexico = analisadorLexico;
    }

    public AnalisadorSintatico getAnalisadorSintatico() {
        return analisadorSintatico;
    }

    public void setAnalisadorSintatico(AnalisadorSintatico analisadorSintático) {
        this.analisadorSintatico = analisadorSintático;
    }

}
