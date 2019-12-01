package com.observatorioMirim.api.models.produto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class ProdutoDtoDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "observatorio_mirirm";
    private static final int VERSION = 1;

    private static final String TABELA = "produtos_dto";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_ID_PRODUTO = "id_produto";
    private static final String COLUNA_ID_SAIDA = "id_saida";
    private static final String COLUNA_NOME = "name";
    private static final String COLUNA_MARCA = "marca";
    private static final String COLUNA_DATA_VALIDADE = "data_validade";
    private static final String COLUNA_QUANTIDADE = "quantidade";
    private static final String COLUNA_UNIDADE = "unidade";
    private static final String COLUNA_OBSERVACAO = "observacao";
    private static final String COLUNA_ENTRADA = "entrada";

    public ProdutoDtoDB(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABELA + " ("
                + COLUNA_ID + " integer primary key autoincrement,"
                + COLUNA_ID_PRODUTO + " integer,"
                + COLUNA_ID_SAIDA + " integer,"
                + COLUNA_NOME + " text,"
                + COLUNA_MARCA + " text,"
                + COLUNA_DATA_VALIDADE + " text,"
                + COLUNA_QUANTIDADE + " real,"
                + COLUNA_UNIDADE + " text,"
                + COLUNA_OBSERVACAO + " text,"
                + COLUNA_ENTRADA + " integer"
                +")";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(sqLiteDatabase);
    }

    public static void insert(Context context, ProdutoDto produtoDto){
        ContentValues valores;

        SQLiteDatabase db = new ProdutoDtoDB(context).getWritableDatabase();
        valores = new ContentValues();
        valores.put(COLUNA_ID_PRODUTO, produtoDto.getIdProduto());
        valores.put(COLUNA_ID_SAIDA, produtoDto.getIdSaida());
        valores.put(COLUNA_NOME, produtoDto.getNome());
        valores.put(COLUNA_MARCA, produtoDto.getMarca());
        valores.put(COLUNA_DATA_VALIDADE, produtoDto.getDataValidade().toString());
        valores.put(COLUNA_QUANTIDADE, produtoDto.getQuantidade().doubleValue());
        valores.put(COLUNA_UNIDADE, produtoDto.getUnidade());
        valores.put(COLUNA_OBSERVACAO, produtoDto.getObservacao());
        valores.put(COLUNA_ENTRADA, produtoDto.isEntrada());

        long id = db.insert(TABELA, null, valores);
        db.close();

        produtoDto.setId((int) id);
    }

    public static void update(Context context, ProdutoDto produtoDto){
        ContentValues valores;
        String where;

        SQLiteDatabase db = new ProdutoDtoDB(context).getWritableDatabase();

        where = COLUNA_ID + " = " + produtoDto.getId();

        valores = new ContentValues();
        valores.put(COLUNA_ID_PRODUTO, produtoDto.getIdProduto());
        valores.put(COLUNA_ID_SAIDA, produtoDto.getIdSaida());
        valores.put(COLUNA_NOME, produtoDto.getNome());
        valores.put(COLUNA_MARCA, produtoDto.getMarca());
        valores.put(COLUNA_DATA_VALIDADE, produtoDto.getDataValidade().toString());
        valores.put(COLUNA_QUANTIDADE, produtoDto.getQuantidade().doubleValue());
        valores.put(COLUNA_UNIDADE, produtoDto.getUnidade());
        valores.put(COLUNA_OBSERVACAO, produtoDto.getObservacao());
        valores.put(COLUNA_ENTRADA, produtoDto.isEntrada());

        db.update(TABELA, valores, where,null);
        db.close();
    }

    public static ArrayList<ProdutoDto> list(Context context) {
        ArrayList<ProdutoDto> produtos = new ArrayList<>();

        SQLiteDatabase db = new ProdutoDtoDB(context).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABELA, null);

        if (cursor.moveToFirst()) {
            do {
                ProdutoDto produtoDto = new ProdutoDto();
                produtoDto.setId(Integer.parseInt(cursor.getString(0)));
                produtoDto.setIdProduto(Integer.parseInt(cursor.getString(1)));
                produtoDto.setIdSaida(Integer.parseInt(cursor.getString(2)));
                produtoDto.setNome(cursor.getString(3));
                produtoDto.setMarca(cursor.getString(4));
                produtoDto.setDataValidade(LocalDate.parse(cursor.getString(5)));
                produtoDto.setQuantidade(new BigDecimal((cursor.getString(6))));
                produtoDto.setUnidade(cursor.getString(7));
                produtoDto.setObservacao(cursor.getString(8));
                produtoDto.setEntrada(cursor.getString(9).equals("1"));

                produtos.add(produtoDto);
            } while (cursor.moveToNext());
        }

        return produtos;
    }

    public static void delete(Context context, int id){
        String where = COLUNA_ID + " = " + id;

        SQLiteDatabase db = new ProdutoDtoDB(context).getWritableDatabase();
        db.delete(TABELA, where,null);
        db.close();
    }
}
