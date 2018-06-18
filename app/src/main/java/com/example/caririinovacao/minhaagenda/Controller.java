package com.example.caririinovacao.minhaagenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Controller {
    SQLiteDatabase db;
    Banco banco;

    public Controller(Context context) {
        banco = new Banco(context);
    }

    public String inserir(String descr, String tipo, String hora, String data) {
        ContentValues v;
        long result;

        db = banco.getWritableDatabase();

        v = new ContentValues();
        v.put("descr", descr);
        v.put("tipo", tipo);
        v.put("hora", hora);
        v.put("data", data);

        result = db.insert("evento", null, v);
        db.close();

        if (result == -1) {
            return "Error ao inserir o registro";
        } else {
            return "Registro gravado com sucesso";
        }

    }

    public Cursor lista() {
        Cursor cursor;
        String[] campos = {"_id", "descr", "tipo", "hora", "data"};
        db = banco.getReadableDatabase();
        cursor = db.query("evento", campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor buscaID(int id){
        Cursor cursor;
        String[] campos = {"_id", "descr", "tipo", "hora", "data"};
        String where ="_id="+id;
        db = banco.getReadableDatabase();
        cursor = db.query("cliente",campos,where,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String alterar (int id, String descr, String tipo, String hora, String data){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = "_id = " + id;

        valores = new ContentValues();
        valores.put("_id",id);
        valores.put("descr",descr);
        valores.put("tipo",tipo);
        valores.put("hora", hora);
        valores.put("data", data);

        int result = db.update("evento",valores, where, null);
        db.close();

        if (result == -1){
            return "Erro ao alterar o registro";
        }else
            return "Registro alterado com sucesso";

    }

    public String deletar (int id){
        String where;

        db = banco.getWritableDatabase();
        where = "_id = " + id;

        int result = db.delete("evento",where,null);
        db.close();

        if (result == -1){
            return "Erro ao deletar o registro";
        }else
            return "Registro deletado com sucesso";

    }



}