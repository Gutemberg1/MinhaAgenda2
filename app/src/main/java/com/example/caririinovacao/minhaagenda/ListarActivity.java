package com.example.caririinovacao.minhaagenda;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListarActivity extends AppCompatActivity {

    ListView lista;
    Cursor cursor;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        Controller c = new Controller(getBaseContext());
        cursor = c.lista();
        String[] campos = new String[]{"_id","desc","tipo","hora","data"};
        int[] idViews = new int[]{R.id.id,R.id.desc,R.id.tipo, R.id.hora, R.id.data};

        SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(),R.layout.layout_lista,cursor,campos,idViews,0);

        lista = (ListView)findViewById(R.id.listarEventos);
        lista.setAdapter(ad);


    }

}
