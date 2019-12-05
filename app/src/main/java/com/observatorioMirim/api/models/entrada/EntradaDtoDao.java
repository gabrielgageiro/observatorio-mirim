package com.observatorioMirim.api.models.entrada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.observatorioMirim.api.models.entrada.aluno.EntradaAlunoDtoDao;
import com.observatorioMirim.api.models.entrada.item.EntradaItemDtoDao;
import com.observatorioMirim.utils.DbGateway;
import com.observatorioMirim.utils.Shared;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class EntradaDtoDao {

    private static final String TABELA = "entradas_dto";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_ID_CONTA = "id_conta";
    private static final String COLUNA_ID_ESCOLA = "id_escola";
    private static final String COLUNA_ID_SAIDA = "id_saida";
    private static final String COLUNA_OBSERVACAO = "observacao";
    private static final String COLUNA_FINALIZADA = "finalizada";
    private static final String COLUNA_DATA = "data";

    public static final String CREATE = "CREATE TABLE " + TABELA + " ("
            + COLUNA_ID + " integer primary key autoincrement,"
            + COLUNA_ID_CONTA + " integer,"
            + COLUNA_ID_ESCOLA + " integer,"
            + COLUNA_ID_SAIDA + " integer,"
            + COLUNA_OBSERVACAO + " text,"
            + COLUNA_FINALIZADA + " integer,"
            + COLUNA_DATA + " text"
            +")";

    public static final String DROP = "DROP TABLE IF EXISTS " + TABELA;

    public static void insert(Context context, EntradaDto entradaDto){
        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COLUNA_ID_CONTA, Shared.getInt(context, "idConta"));
        valores.put(COLUNA_ID_ESCOLA, Shared.getInt(context, "idEscola"));
        valores.put(COLUNA_ID_SAIDA, entradaDto.getIdSaida());
        valores.put(COLUNA_OBSERVACAO, entradaDto.getObservacao());
        valores.put(COLUNA_FINALIZADA, entradaDto.isFinalizada());
        valores.put(COLUNA_DATA, LocalDate.now().toString());
        long id = db.insert(TABELA, null, valores);

        entradaDto.setId((int) id);
    }

    public static void update(Context context, EntradaDto entradaDto){
        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();

        String where = COLUNA_ID + " = " + entradaDto.getId();

        ContentValues valores = new ContentValues();
        valores.put(COLUNA_ID_SAIDA, entradaDto.getIdSaida());
        valores.put(COLUNA_ID_CONTA, entradaDto.getIdConta());
        valores.put(COLUNA_ID_ESCOLA, entradaDto.getIdEscola());
        valores.put(COLUNA_OBSERVACAO, entradaDto.getObservacao());
        valores.put(COLUNA_FINALIZADA, entradaDto.isFinalizada());

        db.update(TABELA, valores, where,null);
    }

    public static void save(Context context, EntradaDto entradaDto){
        if(entradaDto.isNew()){
            insert(context, entradaDto);
        }else {
            update(context, entradaDto);
        }
    }

    public static EntradaDto findById(Context context, Integer id) {

        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABELA + " WHERE " + COLUNA_ID + " = " + id, null);

        if (cursor.moveToFirst()) {
            EntradaDto entradaDto = new EntradaDto();
            entradaDto.setId(Integer.parseInt(cursor.getString(0)));
            entradaDto.setIdConta(Integer.parseInt(cursor.getString(1)));
            entradaDto.setIdEscola(Integer.parseInt(cursor.getString(2)));
            entradaDto.setIdSaida(Integer.parseInt(cursor.getString(3)));
            entradaDto.setObservacao(cursor.getString(4));
            entradaDto.setFinalizada("1".equals(cursor.getString(5)));

            return entradaDto;
        }

        return null;
    }

    public static void delete(Context context, int id){
        String where = COLUNA_ID + " = " + id;

        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
        db.delete(TABELA, where,null);

        EntradaItemDtoDao.deleteByIdEntrada(context, id);
        EntradaAlunoDtoDao.deleteByIdEntrada(context, id);
    }

    public static List<EntradaDto> listAllPendentes(Context context) {

        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABELA + " WHERE " + COLUNA_FINALIZADA + " = 1", null);

        List<EntradaDto> entradas = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do{
                EntradaDto entradaDto = new EntradaDto();
                entradaDto.setId(Integer.parseInt(cursor.getString(0)));
                entradaDto.setIdConta(Integer.parseInt(cursor.getString(1)));
                entradaDto.setIdEscola(Integer.parseInt(cursor.getString(2)));
                entradaDto.setIdSaida(Integer.parseInt(cursor.getString(3)));
                entradaDto.setObservacao(cursor.getString(4));
                entradaDto.setFinalizada("1".equals(cursor.getString(5)));

                entradas.add(entradaDto);
            }while (cursor.moveToNext());

        }

        return entradas;
    }

    public static void deleteEntradasNaoFinalizadasAnteriores(Context context){ //Retorna os ids das entradas removidas
        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
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
        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUNA_ID + " FROM " + TABELA + " WHERE " + COLUNA_FINALIZADA + " = 0", null);

        if (cursor.moveToFirst()) {
            return Integer.parseInt(cursor.getString(0));
        }

        return null;
    }

    public static int countSincronizacoesPendentes(Context context){ //Retorna o id da entrada não finalizada
        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABELA + " WHERE " + COLUNA_FINALIZADA + " = 1", null);

        if (cursor.moveToFirst()) {
            return Integer.parseInt(cursor.getString(0));
        }

        return 0;
    }
}
