package br.edu.ifba.mobile.agendamake.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifba.mobile.agendamake.R;
import br.edu.ifba.mobile.agendamake.bd.DesignBD;
import br.edu.ifba.mobile.agendamake.tarefas.GravacaoDesign;


/**
 * Created by Pábila Mello on 28/06/2016.
 */
public class FragmentoCadastroDesign extends Fragment {


    private static FragmentoCadastroDesign instancia = null;

    public static FragmentoCadastroDesign getInstancia()
    {
        if(instancia==null)        {
            instancia= new FragmentoCadastroDesign();
        }
        return instancia;
    }

    private View tela = null;

    private EditText nome =null;
    private EditText data =null;
    private EditText horario =null;
    private EditText descricao =null;

    private Button botaoGravar = null;

    private DesignBD design =null;



    @Override

    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle)    {
        tela= inflador.inflate(R.layout.fragmento_cadastro_design,vgrupo, false);

        preparar();
        return tela;
    }

    private void preparar(){
        nome=(EditText)tela.findViewById(R.id.nomeD);
        data=(EditText) tela. findViewById(R.id.dataD);
        horario=(EditText) tela. findViewById(R.id.horarioD);
        descricao=(EditText) tela. findViewById(R.id.descricaoD);

        botaoGravar = (Button) tela.findViewById(R.id.botaoGravarD);
        botaoGravar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GravacaoDesign gravacao=new GravacaoDesign(getContexto(),getDesign());
                gravacao.execute();
                limparCampos();
            }
        });
    }

    private Context getContexto(){
        return this.getContext();
    }

    private DesignBD getDesign(){
        design.setNome(nome.getText().toString());
        design.setData(data.getText().toString());
        design.setHorario(horario.getText().toString());
        design.setDescricao(descricao.getText().toString());

        return design;
    }

    public void exibirDesignSelecionada(){
        design=FragmentoListaDesign.getInstancia().getDesignSelecionada();
        if(design.getCodigo()==-1){
            limparCampos();
        }else{
            carregarCampos();
        }
    }

    public void limparCampos(){
        nome.setText("");
        data.setText("/ /");
        horario.setText(" :");
        descricao.setText("");
    }

    private void carregarCampos(){
        nome.setText(design.getNome());
        data.setText(design.getData()+"");
        horario.setText(design.getHorario()+"");
        descricao.setText(design.getDescricao()+"");
    }
}
