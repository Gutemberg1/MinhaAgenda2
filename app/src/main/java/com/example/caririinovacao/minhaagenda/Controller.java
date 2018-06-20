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

    //metodo inserir, recebe por parametro os campos a serem inseridos no banco
    //é preciso criar uma base sqlite e um objeto banco, deve-se criar tbm um
    //contentvalues que recebe os campos e se encarrega de inserir os dados
    //na base
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
    //metodo lista, ultilizado para listar o que já está gravado no banco
    //deve-se criar um cursor que se encarregará de execurar uma query no banco
    //e percorer toda a tabela até não ter mais o que buscar
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
    //metodo buscaid, bem parecido com o lista, porém faz uma busca direcionada por um id
    public Cursor buscaID(int id){
        Cursor cursor;
        String[] campos = {"_id", "descr", "tipo", "hora", "data"};
        String where ="_id="+id;
        db = banco.getReadableDatabase();
        cursor = db.query("evento",campos,where,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    //metodo alterar, recebe todos os dados referentes a alteração
    //ultiliza um contentvalues para organizar todos esses dados
    //ultilizando o objeto banco instanciado no inico do cod e o objeto base sqlite
    //este metodo busca a tupla referente ao id previamente passado e subistituo os valores da tupla

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
    //metodo deletar,ultilizando o id previamente passado o metodo faz uma busca por esse id
    //chama o metodo delete do obejeto base sqlite e passa o id, logo a deleção será feita somente na tupla de id
    //correspondente
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