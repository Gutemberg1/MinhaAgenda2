package com.example.caririinovacao.minhaagenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;



public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       Button btn_add = (Button)findViewById(R.id.btn_add);
       btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CadEventoActivity.class));
            }
        });

       Button btn_listar = (Button)findViewById(R.id.btn_listar);
       btn_listar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(MainActivity.this,ListarActivity.class));
           }
       });

    }




}
