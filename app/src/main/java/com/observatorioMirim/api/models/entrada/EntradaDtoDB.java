package com.observatorioMirim.api.models.entrada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.observatorioMirim.api.models.produto.ProdutoDtoDB;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public final class EntradaDtoDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "observatorio_mirirm";
    private static final int VERSION = 1;

    private static final String TABELA = "entradas_dto";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_ID_CONTA = "id_conta";
    private static final String COLUNA_ID_ESCOLA = "id_escola";
    private static final String COLUNA_ID_SAIDA = "id_saida";
    private static final String COLUNA_OBSERVACAO = "observacao";
    private static final String COLUNA_FINALIZADA = "finalizada";
    private static final String COLUNA_DATA = "data";

    public EntradaDtoDB(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABELA + " ("
                + COLUNA_ID + " integer primary key autoincrement,"
                + COLUNA_ID_CONTA + " integer,"
                + COLUNA_ID_ESCOLA + " integer,"
                + COLUNA_ID_SAIDA + " integer,"
                + COLUNA_OBSERVACAO + " text,"
                + COLUNA_FINALIZADA + " integer,"
                + COLUNA_DATA + " text"
                +")";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(sqLiteDatabase);
    }

    public static void insert(Context context, EntradaDto entradaDto){
        SQLiteDatabase db = new EntradaDtoDB(context).getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COLUNA_ID_CONTA, entradaDto.getIdConta());
        valores.put(COLUNA_ID_ESCOLA, entradaDto.getIdEscola());
        valores.put(COLUNA_ID_SAIDA, entradaDto.getIdSaida());
        valores.put(COLUNA_OBSERVACAO, entradaDto.getObservacao());
        valores.put(COLUNA_FINALIZADA, entradaDto.isFinalizada());
        valores.put(COLUNA_DATA, LocalDate.now().toString());
        long id = db.insert(TABELA, null, valores);
        db.close();

        entradaDto.setId((int) id);
    }

    public static void update(Context context, EntradaDto entradaDto){
        SQLiteDatabase db = new EntradaDtoDB(context).getWritableDatabase();

        String where = COLUNA_ID + " = " + entradaDto.getId();

        ContentValues valores = new ContentValues();
        valores.put(COLUNA_ID_SAIDA, entradaDto.getIdSaida());
        valores.put(COLUNA_ID_CONTA, entradaDto.getIdConta());
        valores.put(COLUNA_ID_ESCOLA, entradaDto.getIdEscola());
        valores.put(COLUNA_OBSERVACAO, entradaDto.getObservacao());
        valores.put(COLUNA_FINALIZADA, entradaDto.isFinalizada());

        db.update(TABELA, valores, where,null);
        db.close();
    }

    public static void save(Context context, EntradaDto entradaDto){
        if(entradaDto.isNew()){
            insert(context, entradaDto);
        }else {
            update(context, entradaDto);
        }
    }

    public static void delete(Context context, int id){
        String where = COLUNA_ID + " = " + id;

        SQLiteDatabase db = new EntradaDtoDB(context).getWritableDatabase();
        db.delete(TABELA, where,null);
        db.close();

        ProdutoDtoDB.deleteByIdEntrada(context, id);
    }

    public static EntradaDto getBySaida(Context context, int idSaida) {

        SQLiteDatabase db = new EntradaDtoDB(context).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABELA + " WHERE " + COLUNA_ID_SAIDA + " = " + idSaida, null);

        if (cursor.moveToFirst()) {
            EntradaDto entradaDto = new EntradaDto();
            entradaDto.setId(Integer.parseInt(cursor.getString(0)));
            entradaDto.setIdConta(Integer.parseInt(cursor.getString(1)));
            entradaDto.setIdEscola(Integer.parseInt(cursor.getString(2)));
            entradaDto.setIdSaida(Integer.parseInt(cursor.getString(3)));
            entradaDto.setObservacao(cursor.getString(4));
            entradaDto.setFinalizada("1".equals(cursor.getString(5)));

            db.close();

            return entradaDto;
        }

        db.close();
        return null;
    }

    public static void deleteEntradasNaoFinalizadasAnteriores(Context context){ //Retorna os ids das entradas removidas
        SQLiteDatabase db = new EntradaDtoDB(context).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUNA_ID + "," + COLUNA_DATA + " FROM " + TABELA + " WHERE " + COLUNA_FINALIZADA + " = 0", null);

        Set<Integer> ids = new HashSet<>();
        LocalDate hoje = LocalDate.now();

        if (cursor.moveToFirst()) {
            do {
                LocalDate data = LocalDate.parse(cursor.getString(1));

                if(data != null && data.isBefore(hoje)){
                    ids.add(Integer.parseInt(cursor.getString(0)));
                }
            }while (cursor.moveToNext());
        }

        ids.forEach( i -> {
            delete(context, i);
        });
    }

    public static Integer existeEntradaNaoFinalizada(Context context){ //Retorna o id da entrada não finalizada
        SQLiteDatabase db = new EntradaDtoDB(context).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUNA_ID + " FROM " + TABELA + " WHERE " + COLUNA_FINALIZADA + " = 0", null);

        if (cursor.moveToFirst()) {
            return Integer.parseInt(cursor.getString(0));
        }

        return null;
    }
}
