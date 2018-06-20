package com.example.caririinovacao.minhaagenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListarActivity extends AppCompatActivity {

    ListView lista;
    Cursor cursor;
    String codigo;
    AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);


        Controller c = new Controller(getBaseContext());
        cursor = c.lista();
        String[] campos = new String[]{"_id","descr","tipo","hora","data"};
        int[] idViews = new int[]{R.id.id,R.id.desc,R.id.tipo, R.id.hora, R.id.data};

        SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(),R.layout.layout_lista,cursor,campos,idViews,0);

        lista = (ListView)findViewById(R.id.listarEventos);
        lista.setAdapter(ad);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cursor.moveToPosition(i);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                AlertDialog.Builder builder = new AlertDialog.Builder(ListarActivity.this);
                builder.setMessage("O que deseja fazer?");
                builder.setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ListarActivity.this,CadEventoActivity.class);
                        intent.putExtra("situacao","alterar");
                        intent.putExtra("codigo",codigo);
                        startActivity(intent);

                    }
                });

                builder.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ListarActivity.this);
                        builder.setMessage("Deseja realmente excluir?");
                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Controller c = new Controller(getBaseContext());
                                c.deletar(Integer.parseInt(codigo));
                                //Controller c2 = new Controller(getBaseContext());
                                cursor = c.lista();
                                String[] campos = new String[]{"_id","descr","tipo","hora","data"};
                                int[] idViews = new int[]{R.id.id,R.id.desc,R.id.tipo, R.id.hora, R.id.data};

                                SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(),R.layout.layout_lista,cursor,campos,idViews,0);

                                lista = (ListView)findViewById(R.id.listarEventos);
                                lista.setAdapter(ad);
                                Toast.makeText(ListarActivity.this,"Deletado com sucesso",Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alerta = builder.create();
                        alerta.show();

                    }
                });
                alerta = builder.create();
                alerta.show();
            }
        });


    }

}
