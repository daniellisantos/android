package br.edu.ifba.mobile.agendamake.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.ifba.mobile.agendamake.R;


/**
 * Created by alunoifba on 13/05/2016.
 */
public class FragmentoTelaInicio extends Fragment {

    private static FragmentoTelaInicio instancia = null;

    public static FragmentoTelaInicio getInstancia()
    {
        if(instancia==null) {
            instancia = new FragmentoTelaInicio();
        }
        return instancia;
    }
    private View tela=null;
    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_tela_inicio, vgrupo, false);

        return tela;
    }
}
