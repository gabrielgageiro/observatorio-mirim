package com.observatorioMirim.api.models.entrada.aluno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.observatorioMirim.utils.DbGateway;

import java.util.ArrayList;
import java.util.List;

public final class EntradaAlunoDtoDao{

    private static final String TABELA = "alunos_dto";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_ID_ENTRADA = "id_entrada";
    private static final String COLUNA_NOME = "nome";

    public static final String CREATE  = "CREATE TABLE " + TABELA + " ("
            + COLUNA_ID + " integer primary key autoincrement,"
            + COLUNA_ID_ENTRADA + " integer,"
            + COLUNA_NOME + " text"
            +")";

    public static final String DROP = "DROP TABLE IF EXISTS " + TABELA;

    public static void insertAll(Context context, List<EntradaAlunoDto> alunos){
        ContentValues valores;

        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();

        for(EntradaAlunoDto a : alunos){
            valores = new ContentValues();
            valores.put(COLUNA_ID_ENTRADA, a.getIdEntrada());
            valores.put(COLUNA_NOME, a.getNome());

            db.insert(TABELA, null, valores);
        }

    }

    public static ArrayList<EntradaAlunoDto> listByEntrada(Context context, Integer entradaId) {
        ArrayList<EntradaAlunoDto> alunos = new ArrayList<>();

        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABELA + " WHERE " + COLUNA_ID_ENTRADA + " = " + entradaId, null);

        if (cursor.moveToFirst()) {
            do {
                EntradaAlunoDto aluno = new EntradaAlunoDto();
                aluno.setId(Integer.parseInt(cursor.getString(0)));
                aluno.setIdEntrada(Integer.parseInt(cursor.getString(1)));
                aluno.setNome(cursor.getString(2));
                alunos.add(aluno);
            } while (cursor.moveToNext());
        }

        return alunos;
    }

    public static void deleteByIdEntrada(Context context, int idEntrada){
        String where = COLUNA_ID_ENTRADA + " = " + idEntrada;

        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
        db.delete(TABELA, where,null);
    }
}
