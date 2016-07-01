package br.edu.ifba.mobile.agendamake.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.agendamake.bd.FBancoDeDados;
import br.edu.ifba.mobile.agendamake.bd.Make;


/**
 * Created by Letícia Porto on 27/05/2016.
 */
public class RemocaoMake extends AsyncTask<Void, Void, String> {//paradigma generics

    private Context contexto = null;
    private Make make = null;

    public RemocaoMake(Context contexto, Make make){
        this.make = make;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(make.getCodigo()!=-1){
            if(FBancoDeDados.getInstancia().removerMake(make)==0){
                mensagem="Problemas de remoção!";
            }else
                mensagem="Cliente removido!";
        }else{
            mensagem="Selecione um cliente!";
        }
        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto,mensagem, Toast.LENGTH_LONG).show();
    }
}
