package com.example.rollerpizza_tfg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity_IniciarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_iniciar_sesion);

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity_IniciarSesion.this,R.color.rojo));
        TextView registrate=findViewById(R.id.textView_registrate);
        registrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí es donde se inicia el nuevo Activity
                Intent intent = new Intent(MainActivity_IniciarSesion.this, MainActivity_Pantalla_registrarse.class);
                startActivity(intent);
            }
        });
    }
}