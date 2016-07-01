package br.edu.ifba.mobile.agendamake.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.agendamake.bd.DesignBD;
import br.edu.ifba.mobile.agendamake.bd.FBancoDeDados;


/**
 * Created by Pábila Mello on 28/06/2016.
 */
public class RemocaoDesign extends AsyncTask<Void, Void, String> {//paradigma generics

    private Context contexto = null;
    private DesignBD design = null;

    public RemocaoDesign(Context contexto, DesignBD design){
        this.design = design;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(design.getCodigo()!=-1){
            if(FBancoDeDados.getInstancia().removerDesign(design)==0){
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