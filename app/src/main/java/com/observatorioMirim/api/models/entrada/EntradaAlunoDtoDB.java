package com.observatorioMirim.api.models.entrada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public final class EntradaAlunoDtoDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "observatorio_mirirm";
    private static final int VERSION = 1;

    private static final String TABELA = "alunos_dto";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_ID_ENTRADA = "id_entrada";
    private static final String COLUNA_NOME = "nome";

    public EntradaAlunoDtoDB(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABELA + " ("
                + COLUNA_ID + " integer primary key autoincrement,"
                + COLUNA_ID_ENTRADA + " integer,"
                + COLUNA_NOME + " text"
                +")";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(sqLiteDatabase);
    }

    public static void insertAll(Context context, List<EntradaAlunoDto> alunos){
        ContentValues valores;

        SQLiteDatabase db = new EntradaAlunoDtoDB(context).getWritableDatabase();

        for(EntradaAlunoDto a : alunos){
            valores = new ContentValues();
            valores.put(COLUNA_ID_ENTRADA, a.getIdEntrada());
            valores.put(COLUNA_NOME, a.getNome());

            db.insert(TABELA, null, valores);
        }

        db.close();

    }

    public static ArrayList<EntradaAlunoDto> listByEntrada(Context context, Integer entradaId) {
        ArrayList<EntradaAlunoDto> alunos = new ArrayList<>();

        SQLiteDatabase db = new EntradaAlunoDtoDB(context).getWritableDatabase();
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

    public static void deleteAll(Context context){
        SQLiteDatabase db = new EntradaAlunoDtoDB(context).getWritableDatabase();
        db.execSQL("DELETE FROM " + TABELA);
        db.close();
    }
}
