package br.edu.ifba.mobile.agendamake.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.agendamake.bd.DesignBD;
import br.edu.ifba.mobile.agendamake.bd.FBancoDeDados;


/**
 * Created by Pábila Mello on 28/06/2016.
 */
public class GravacaoDesign extends AsyncTask<Void, Void, String> {//paradigma generics

    private Context contexto = null;
    private DesignBD design = null;

    public GravacaoDesign(Context contexto, DesignBD design){
        this.design=design;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo=-1;
        if(design.getCodigo()==-1){
            codigo= FBancoDeDados.getInstancia().inserirDesign(design);
        }else{
            codigo= FBancoDeDados.getInstancia().atualizarDesign(design);
        }
        if (codigo > 0) {
            mensagem="Cliente para sobrancelhas gravado com sucesso!!";
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
