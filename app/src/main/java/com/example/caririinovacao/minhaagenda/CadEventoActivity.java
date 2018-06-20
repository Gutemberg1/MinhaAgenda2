package com.example.caririinovacao.minhaagenda;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;


public class CadEventoActivity extends AppCompatActivity {

    String desc, tipo, hora, data, resultado, codigo;
    EditText etDesc, etTipo, etHora, etData;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_evento);

         etDesc = (EditText)findViewById(R.id.txt_desc);
         etTipo =(EditText)findViewById(R.id.txt_tipo);
         etHora = (EditText)findViewById(R.id.txt_hora);
         etData = (EditText)findViewById(R.id.txt_data);


         if (getIntent().getStringExtra("situacao").equals("alterar")){
            Controller c = new Controller(getBaseContext());
            Toast.makeText(CadEventoActivity.this,"está entrando no if",Toast.LENGTH_LONG).show();
            codigo = getIntent().getStringExtra("codigo");
            cursor = c.buscaID(Integer.parseInt(codigo));
            etDesc.setText(cursor.getString(cursor.getColumnIndexOrThrow("descr")));
            etTipo.setText(cursor.getString(cursor.getColumnIndexOrThrow("tipo")));
            etHora.setText(cursor.getString(cursor.getColumnIndexOrThrow("hora")));
            etData.setText(cursor.getString(cursor.getColumnIndexOrThrow("data")));
        }

        Button btn_salvar = (Button)findViewById(R.id.btn_salvar);
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Controller c = new Controller(getBaseContext());
            desc = etDesc.getText().toString();
            tipo = etTipo.getText().toString();
            hora = etHora.getText().toString();
            data = etData.getText().toString();

            resultado = c.inserir(desc,tipo,hora,data);
            Toast.makeText(CadEventoActivity.this,resultado,Toast.LENGTH_LONG).show();
            }
        });

    }

}
