package com.observatorioMirim.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.observatorioMirim.api.models.entrada.EntradaDtoDao;
import com.observatorioMirim.api.models.entrada.aluno.EntradaAlunoDtoDao;
import com.observatorioMirim.api.models.entrada.item.EntradaItemDtoDao;

public final class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "observatorio_mirirm";
    private static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(EntradaDtoDao.CREATE);
        sqLiteDatabase.execSQL(EntradaItemDtoDao.CREATE);
        sqLiteDatabase.execSQL(EntradaAlunoDtoDao.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            sqLiteDatabase.execSQL(EntradaItemDtoDao.DROP);
            sqLiteDatabase.execSQL(EntradaAlunoDtoDao.DROP);
            sqLiteDatabase.execSQL(EntradaDtoDao.DROP);
            onCreate(sqLiteDatabase);
        }
    }
}
