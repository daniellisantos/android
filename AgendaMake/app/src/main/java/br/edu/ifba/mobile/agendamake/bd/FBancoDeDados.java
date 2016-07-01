package br.edu.ifba.mobile.agendamake.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Pabila on 25/06/2016.
 */
public class FBancoDeDados extends SQLiteOpenHelper {

    private static FBancoDeDados instancia = null;

    public static FBancoDeDados criarInstancia(Context context){
        if (instancia == null){
            instancia = new FBancoDeDados(context);
        }

        return instancia;
    }

    public static FBancoDeDados getInstancia() {
        return instancia;
    }

    private static String NOME_BANCO = "AgendaMake";
    private static int VERSAO_BANCO = 1;

    public FBancoDeDados(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);

    }

    private static String COMANDO_CRIACAO_TABELA_MAKES =
            "CREATE TABLE MAKES(" +
                    "CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NOME TEXT, DATA TEXT, HORARIO TEXT, DESCRICAO TEXT)";
    private static String COMANDO_CRIACAO_TABELA_DESIGNS =
            "CREATE TABLE DESIGNS(" +
                    "CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NOME TEXT, DATA TEXT, HORARIO TEXT, DESCRICAO TEXT)";
    @Override
    public void onCreate(SQLiteDatabase db) { //adiciona +BD que n tem... 1.0 na 2.0 usa o BD+n coisas
        db.execSQL(COMANDO_CRIACAO_TABELA_MAKES);
        db.execSQL(COMANDO_CRIACAO_TABELA_DESIGNS);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        // TODO Auto-generated method stub
    }

    // metodos de criacao de um CRUD utilizando o SQLite

    public long inserirMake(Make make) { //C
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", make.getNome());
        valores.put("DATA", make.getData());
        valores.put("HORARIO", make.getHorario());
        valores.put("DESCRICAO", make.getDescricao());

        long codigo = db.insert("MAKES", null, valores);
        return codigo;
    }
    public long inserirDesign(DesignBD design){ //C
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", design.getNome());
        valores.put("DATA", design.getData());
        valores.put("HORARIO", design.getHorario());
        valores.put("DESCRICAO", design.getDescricao());

        long codigo = db.insert("DESIGNS", null, valores);
        return codigo;
    }


    // LISTAR OS CADASTROS DE MAQUIAGENS E DE DESIGNS DE SOBRANCELHAS
    public List<Make> listarMakes() { //R
        List<Make> makes = new ArrayList<Make>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT CODIGO, NOME, DATA, HORARIO, DESCRICAO FROM MAKES";

        Cursor cursor = db.rawQuery(selecao, null);
        if (cursor != null) {
            boolean temProximo = cursor.moveToFirst();
            while (temProximo) {
                Make make = new Make();
                make.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                make.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                make.setData(cursor.getString(cursor.getColumnIndex("DATA")));
                make.setHorario(cursor.getString(cursor.getColumnIndex("HORARIO")));
                make.setDescricao(cursor.getString(cursor.getColumnIndex("DESCRICAO")));

                makes.add(make);

                temProximo = cursor.moveToNext();
            }

        }
        return makes;
    }

    public List<DesignBD> listarDesigns() { //R
        List<DesignBD> designs = new ArrayList<DesignBD>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT CODIGO, NOME, DATA, HORARIO, DESCRICAO FROM DESIGNS";

        Cursor cursor = db.rawQuery(selecao, null);
        if (cursor != null) {
            boolean temProximo = cursor.moveToFirst();
            while (temProximo) {
                DesignBD design = new DesignBD();
                design.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                design.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                design.setData(cursor.getString(cursor.getColumnIndex("DATA")));
                design.setHorario(cursor.getString(cursor.getColumnIndex("HORARIO")));
                design.setDescricao(cursor.getString(cursor.getColumnIndex("DESCRICAO")));

                designs.add(design);

                temProximo = cursor.moveToNext();
            }

        }
        return designs;
    }


    // ATUALIZAR AS LISTAS DE MAQUIAGENS E DE SOBRANCELHAS

    public long atualizarMake(Make make) { //U
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", make.getNome());
        valores.put("DATA", make.getData());
        valores.put("HORARIO", make.getHorario());
        valores.put("DESCRICAO", make.getDescricao());

        long codigo = db.update("MAKES", valores, "CODIGO = " + make.getCodigo(), null);
        return codigo;
    }


    public long atualizarDesign(DesignBD design) { //U
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", design.getNome());
        valores.put("DATA", design.getData());
        valores.put("HORARIO", design.getHorario());
        valores.put("DESCRICAO", design.getDescricao());

        long codigo = db.update("DESIGNS", valores, "CODIGO = " + design.getCodigo(), null);
        return codigo;
    }


    // REMOVER CADASTROS


    public int removerMake(Make make) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("MAKES","CODIGO = " + make.getCodigo(), null);


    }

    public int removerDesign(DesignBD design) { //D
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("DESIGNS","CODIGO = " + design.getCodigo(), null);
    }
}