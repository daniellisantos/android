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
import br.edu.ifba.mobile.agendamake.bd.Make;
import br.edu.ifba.mobile.agendamake.tarefas.GravacaoMake;


/**
 * Created by alunoifba on 13/05/2016.
 */
public class FragmentoCadastroMake extends Fragment {


    private static FragmentoCadastroMake instancia = null;

    public static FragmentoCadastroMake getInstancia()
    {
        if(instancia==null)        {
            instancia= new FragmentoCadastroMake();
        }
        return instancia;
    }

    private View tela = null;

    private EditText nome =null;
    private EditText data =null;
    private EditText horario =null;
    private EditText descricao =null;

    private Button botaoGravar = null;

    private Make make=null;



    @Override

    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle)    {
        tela= inflador.inflate(R.layout.fragmento_cadastro_make,vgrupo, false);

        preparar();
        return tela;
    }

    private void preparar(){
        nome=(EditText)tela.findViewById(R.id.nomeM);
        data=(EditText) tela. findViewById(R.id.dataM);
        horario=(EditText) tela. findViewById(R.id.horarioM);
        descricao=(EditText) tela. findViewById(R.id.descricaoM);

        botaoGravar = (Button) tela.findViewById(R.id.botaoGravarM);
        botaoGravar.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v) {
                                                GravacaoMake gravacao=new GravacaoMake(getContexto(),getMake());
                                                gravacao.execute();
                                                limparCampos();
                                        }
                                    });
    }

    private Context getContexto(){
        return this.getContext();
    }

    private Make getMake(){
        make.setNome(nome.getText().toString());
        make.setData(data.getText().toString());
        make.setHorario(horario.getText().toString());
        make.setDescricao(descricao.getText().toString());

        return make;
    }

    public void exibirMakeSelecionada(){
        make=FragmentoListaMake.getInstancia().getMakeSelecionada();
        if(make.getCodigo()==-1){
            limparCampos();
        }else{
            carregarCampos();
        }
    }

    public void limparCampos(){
        nome.setText("");
        data.setText("//");
        horario.setText("  :  ");
        descricao.setText("");
    }

    private void carregarCampos(){
        nome.setText(make.getNome());
        data.setText(make.getData()+"");
        horario.setText(make.getHorario()+"");
        descricao.setText(make.getDescricao()+"");
    }
}
