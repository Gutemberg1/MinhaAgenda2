package com.example.caririinovacao.minhaagenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper{


    public Banco(Context context) {
        super(context, "agenda.db", null, 2);
    }

    @Override
    //metodo que efetivamente cria o banco
    //recebe um base sqlite, insere um sql de cração de banco e executa
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE evento (" +
                "_id integer primary key autoincrement not null," +
                "descr text," +
                "tipo text,"+
                "hora text,"+
                "data text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}