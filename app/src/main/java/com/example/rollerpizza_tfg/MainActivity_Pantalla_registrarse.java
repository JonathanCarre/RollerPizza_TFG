package com.example.rollerpizza_tfg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity_Pantalla_registrarse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pantalla_registrarse);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity_Pantalla_registrarse.this,R.color.rojo));
        TextView inicia_sesion=findViewById(R.id.textView_iniciasesion);

        inicia_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí es donde se inicia el nuevo Activity
                Intent intent = new Intent(MainActivity_Pantalla_registrarse.this, MainActivity_IniciarSesion.class);
                startActivity(intent);
            }
        });

        

    }
}