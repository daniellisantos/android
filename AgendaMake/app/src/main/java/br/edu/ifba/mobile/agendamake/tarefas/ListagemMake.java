package br.edu.ifba.mobile.agendamake.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.agendamake.bd.FBancoDeDados;
import br.edu.ifba.mobile.agendamake.bd.Make;


/**
 * Created by Letícia Porto on 27/05/2016.
 */
public class ListagemMake extends AsyncTask<Void, Void, List<Make>> {//paradigma generics

    private Context contexto = null;
    private ListView listaMakes = null;

    public ListagemMake(Context contexto, ListView listaMakes){
        this.listaMakes= listaMakes;
        this.contexto = contexto;
    }

    @Override
    protected List<Make> doInBackground(Void... params) {
        List<Make> makes = FBancoDeDados.getInstancia().listarMakes();
        return makes;
    }

    @Override
    protected void onPostExecute(List<Make> makes){
        if(makes.isEmpty()) {
            Toast.makeText(contexto, "Lista vazia. Cadastre um cliente para maquiagem.", Toast.LENGTH_LONG).show();
        }
        else{
            ArrayAdapter<Make> adaptador=new ArrayAdapter<Make>(contexto,android.R.layout.simple_list_item_single_choice,makes);//colcoar a lista de disciplina dentro do listview
            listaMakes.setAdapter(adaptador);
        }
    }
}
