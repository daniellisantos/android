package br.edu.ifba.mobile.agendamake.bd;

/**
 * Created by Pabila on 25/06/2016.
 */
public class Make {

    private long codigo=-1;
    private String nome;
    private String data;
    private String horario;
    private String descricao;


    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData (String data){
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {

        return nome + " - "+ data+ " - "+horario+" - "+descricao;
    }
}

