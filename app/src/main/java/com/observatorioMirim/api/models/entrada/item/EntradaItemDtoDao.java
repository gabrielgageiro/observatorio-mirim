package com.observatorioMirim.api.models.entrada.item;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.observatorioMirim.utils.DbGateway;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public final class EntradaItemDtoDao {

    private static final String TABELA = "produtos_dto";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_ID_PRODUTO = "id_produto";
    private static final String COLUNA_ID_ENTRADA = "id_entrada";
    private static final String COLUNA_NOME = "name";
    private static final String COLUNA_MARCA = "marca";
    private static final String COLUNA_DATA_VALIDADE = "data_validade";
    private static final String COLUNA_QUANTIDADE = "quantidade";
    private static final String COLUNA_UNIDADE = "unidade";
    private static final String COLUNA_OBSERVACAO = "observacao";
    private static final String COLUNA_ENTRADA = "entrada";
    private static final String COLUNA_UPLOAD = "upload";

    public static final String CREATE = "CREATE TABLE " + TABELA + " ("
            + COLUNA_ID + " integer primary key autoincrement,"
            + COLUNA_ID_PRODUTO + " integer,"
            + COLUNA_ID_ENTRADA + " integer,"
            + COLUNA_NOME + " text,"
            + COLUNA_MARCA + " text,"
            + COLUNA_DATA_VALIDADE + " text,"
            + COLUNA_QUANTIDADE + " real,"
            + COLUNA_UNIDADE + " text,"
            + COLUNA_OBSERVACAO + " text,"
            + COLUNA_ENTRADA + " integer,"
            + COLUNA_UPLOAD + " integer"
            +")";

    public static final String DROP = "DROP TABLE IF EXISTS " + TABELA;

    public static void insert(Context context, EntradaItemDto entradaItemDto){
        ContentValues valores;

        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
        valores = new ContentValues();
        valores.put(COLUNA_ID_PRODUTO, entradaItemDto.getIdProduto());
        valores.put(COLUNA_ID_ENTRADA, entradaItemDto.getIdEntrada());
        valores.put(COLUNA_NOME, entradaItemDto.getNome());
        valores.put(COLUNA_MARCA, entradaItemDto.getMarca());
        valores.put(COLUNA_DATA_VALIDADE, entradaItemDto.getDataValidade() != null ? entradaItemDto.getDataValidade().toString() : null);
        valores.put(COLUNA_QUANTIDADE, entradaItemDto.getQuantidade() != null ? entradaItemDto.getQuantidade().doubleValue() : null);
        valores.put(COLUNA_UNIDADE, entradaItemDto.getUnidade());
        valores.put(COLUNA_OBSERVACAO, entradaItemDto.getObservacao());
        valores.put(COLUNA_ENTRADA, entradaItemDto.isEntrada());
        valores.put(COLUNA_UPLOAD, entradaItemDto.isUpload());

        long id = db.insert(TABELA, null, valores);

        entradaItemDto.setId((int) id);
    }

    public static void update(Context context, EntradaItemDto entradaItemDto){
        ContentValues valores;
        String where;

        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();

        where = COLUNA_ID + " = " + entradaItemDto.getId();

        valores = new ContentValues();
        valores.put(COLUNA_ID_PRODUTO, entradaItemDto.getIdProduto());
        valores.put(COLUNA_ID_ENTRADA, entradaItemDto.getIdEntrada());
        valores.put(COLUNA_NOME, entradaItemDto.getNome());
        valores.put(COLUNA_MARCA, entradaItemDto.getMarca());
        valores.put(COLUNA_DATA_VALIDADE, entradaItemDto.getDataValidade().toString());
        valores.put(COLUNA_QUANTIDADE, entradaItemDto.getQuantidade().doubleValue());
        valores.put(COLUNA_UNIDADE, entradaItemDto.getUnidade());
        valores.put(COLUNA_OBSERVACAO, entradaItemDto.getObservacao());
        valores.put(COLUNA_ENTRADA, entradaItemDto.isEntrada());
        valores.put(COLUNA_UPLOAD, entradaItemDto.isUpload());

        db.update(TABELA, valores, where,null);
    }

    public static void save(Context context, EntradaItemDto entradaItemDto){
        if(entradaItemDto.isNew()){
            insert(context, entradaItemDto);
        }else {
            update(context, entradaItemDto);
        }
    }

    private static EntradaItemDto cursorToProdutoDto(Cursor cursor){
        EntradaItemDto entradaItemDto = new EntradaItemDto();
        entradaItemDto.setId(Integer.parseInt(cursor.getString(0)));
        entradaItemDto.setIdProduto(Integer.parseInt(cursor.getString(1)));
        entradaItemDto.setIdEntrada(Integer.parseInt(cursor.getString(2)));
        entradaItemDto.setNome(cursor.getString(3));
        entradaItemDto.setMarca(cursor.getString(4));
        entradaItemDto.setDataValidade(cursor.getString(5) != null ? LocalDate.parse(cursor.getString(5)) : null);
        entradaItemDto.setQuantidade(cursor.getString(6) != null ? new BigDecimal(cursor.getString(6)) : null);
        entradaItemDto.setUnidade(cursor.getString(7));
        entradaItemDto.setObservacao(cursor.getString(8));
        entradaItemDto.setEntrada(cursor.getString(9).equals("1"));
        entradaItemDto.setUpload(cursor.getString(10).equals("1"));

        return entradaItemDto;
    }

    public static ArrayList<EntradaItemDto> list(Context context) {
        ArrayList<EntradaItemDto> produtos = new ArrayList<>();

        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABELA, null);

        if (cursor.moveToFirst()) {
            do {
                produtos.add(cursorToProdutoDto(cursor));
            } while (cursor.moveToNext());
        }

        return produtos;
    }

    public static ArrayList<EntradaItemDto> listByEntrada(Context context, Integer idEntrada) {
        ArrayList<EntradaItemDto> produtos = new ArrayList<>();

        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABELA + " WHERE " + COLUNA_ID_ENTRADA + " = " + idEntrada, null);

        if (cursor.moveToFirst()) {
            do {
                produtos.add(cursorToProdutoDto(cursor));
            } while (cursor.moveToNext());
        }

        return produtos;
    }

    public static void deleteByIdEntrada(Context context, int idEntrada){
        String where = COLUNA_ID_ENTRADA + " = " + idEntrada;

        SQLiteDatabase db = DbGateway.getInstance(context).getDatabase();
        db.delete(TABELA, where,null);
    }
}
