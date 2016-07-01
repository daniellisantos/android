package br.edu.ifba.mobile.agendamake.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.agendamake.R;
import br.edu.ifba.mobile.agendamake.bd.DesignBD;
import br.edu.ifba.mobile.agendamake.tarefas.ListagemDesign;
import br.edu.ifba.mobile.agendamake.tarefas.RemocaoDesign;


/**
 * Created by Pábila Mello on 28/06/2016.
 */
public class FragmentoListaDesign extends Fragment {

    private static FragmentoListaDesign instancia = null; //transformando em sington

    public static FragmentoListaDesign getInstancia() {
        if(instancia == null){
            instancia = new FragmentoListaDesign();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;
    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle savedInstance){ //pega o arquivo .xml e transforma em arquivo na memoria
        tela = inflador.inflate(R.layout.fragmento_lista_design, vgrupo, false);
        prepararDesign();
        return tela;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflador){//cria o menu graficamente
        super.onCreateOptionsMenu(menu,inflador);
        inflador.inflate(R.menu.menu_agenda_make, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){//qual a ação a depender do clic
        long id= item.getItemId();
        if (id != AdapterView.INVALID_ROW_ID){
            if (id== R.id.cadastro_remover){
                RemocaoDesign remocao=new RemocaoDesign(this.getContext(), this.getDesignSelecionada());
                remocao.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepararDesign() {
        lista = (ListView) tela.findViewById(R.id.listaDesigns);

        this.setHasOptionsMenu(true); //quero que tenha menu
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //especifica o modo de escolha

    }

    public void atualizar(){
        ListagemDesign listagem = new ListagemDesign(this.getContext(),lista);
        listagem.execute();
    }

    public DesignBD getDesignSelecionada(){
        DesignBD design=new DesignBD();
        int posicao=lista.getCheckedItemPosition();
        if(posicao!= ListView.INVALID_POSITION){
           design = (DesignBD) lista.getItemAtPosition(posicao);
        }

        return design;
    }
}