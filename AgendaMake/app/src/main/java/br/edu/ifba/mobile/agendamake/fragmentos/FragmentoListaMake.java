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
import br.edu.ifba.mobile.agendamake.bd.Make;
import br.edu.ifba.mobile.agendamake.tarefas.ListagemMake;
import br.edu.ifba.mobile.agendamake.tarefas.RemocaoMake;


/**
 * Created by Letícia Porto on 20/05/2016.
 */
public class FragmentoListaMake extends Fragment {

    private static FragmentoListaMake instancia = null; //transformando em sington

    public static FragmentoListaMake getInstancia() {
        if(instancia == null){
            instancia = new FragmentoListaMake();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;
    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle savedInstance){ //pega o arquivo .xml e transforma em arquivo na memoria
        tela = inflador.inflate(R.layout.fragmento_lista_make, vgrupo, false);
        prepararMake();
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
                RemocaoMake remocao=new RemocaoMake(this.getContext(), this.getMakeSelecionada());
                remocao.execute();
                atualizar();

            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void prepararMake() {
        lista = (ListView) tela.findViewById(R.id.listaMakes);

        this.setHasOptionsMenu(true); //quero que tenha menu
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //especifica o modo de escolha

    }

    public void atualizar(){
        ListagemMake listagem = new ListagemMake(this.getContext(),lista);
        listagem.execute();
    }

    public Make getMakeSelecionada(){
        Make make=new Make();
        int posicao=lista.getCheckedItemPosition();
        if(posicao!= ListView.INVALID_POSITION){
            make = (Make) lista.getItemAtPosition(posicao);
        }

        return make;
    }
}
