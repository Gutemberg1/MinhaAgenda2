package com.example.caririinovacao.minhaagenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CadEventoActivity extends AppCompatActivity {

    String desc, tipo, hora, data, resultado;
    EditText etDesc, etTipo, etHora, etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_evento);

         etDesc = (EditText)findViewById(R.id.txt_desc);
         etTipo =(EditText)findViewById(R.id.txt_tipo);
         etHora = (EditText)findViewById(R.id.txt_hora);
         etData = (EditText)findViewById(R.id.txt_data);

        ImageButton btn_salvar = (ImageButton)findViewById(R.id.btn_salvar);
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
