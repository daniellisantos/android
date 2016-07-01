package br.edu.ifba.mobile.agendamake.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.agendamake.bd.DesignBD;
import br.edu.ifba.mobile.agendamake.bd.FBancoDeDados;


/**
 * Created by Pábila Mello on 28/06/2016.
 */
public class ListagemDesign extends AsyncTask<Void, Void, List<DesignBD>> {//paradigma generics

    private Context contexto = null;
    private ListView listaDesigns = null;

    public  ListagemDesign(Context contexto, ListView listaDesigns){
        this.listaDesigns= listaDesigns;
        this.contexto = contexto;
    }

    @Override
    protected List<DesignBD> doInBackground(Void... params) {
        List<DesignBD> designs = FBancoDeDados.getInstancia().listarDesigns();
        return designs;
    }

    @Override
    protected void onPostExecute(List<DesignBD> designs){
        if(designs.isEmpty()) {
            Toast.makeText(contexto, "Lista vazia. Cadastre um cliente para sobrancelhas.", Toast.LENGTH_LONG).show();
        }
        else{
            ArrayAdapter<DesignBD> adaptador=new ArrayAdapter<DesignBD>(contexto,android.R.layout.simple_list_item_single_choice,designs);//colcoar a lista de disciplina dentro do listview
            listaDesigns.setAdapter(adaptador);
        }
    }
}
