package br.edu.ifba.mobile.agendamake.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.agendamake.bd.FBancoDeDados;
import br.edu.ifba.mobile.agendamake.bd.Make;


/**
 * Created by Letícia Porto on 27/05/2016.
 */
public class GravacaoMake extends AsyncTask<Void, Void, String> {//paradigma generics

    private Context contexto = null;
    private Make make = null;

    public GravacaoMake(Context contexto, Make make){
        this.make = make;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo= -1;
        if(make.getCodigo()==-1){
            codigo= FBancoDeDados.getInstancia().inserirMake(make);
        }else{
            codigo= FBancoDeDados.getInstancia().atualizarMake(make);
        }
        if (codigo > 0) {
            mensagem="Cliente para maquiagem gravado com sucesso!!";
        }
        else{
            mensagem="Erro de gravação!";
        }

        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto,mensagem, Toast.LENGTH_LONG).show();
    }
}
