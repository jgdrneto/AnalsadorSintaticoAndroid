package principal;

import extrator.Extrator;
import extrator.entidades.Grupo;

public class VerValoresNormalizados {

    public static void main(String[] args) {
        
        Extrator extrator = new Extrator("src/grupos");
        
        for(Grupo g : extrator.getGrupos()){
            
            if(g.getNome().equals("g1")){
            
                System.out.println("Nome:" + g.getNome());
                System.out.println(g.atributosParaList());
                //System.out.println(g.getListNormalizacaoMaxMinEqual());
                System.out.println(g.getListNormalizacaoSigmoidal());
            }
        }
    }

}
