package com.example.rollerpizza_tfg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class MainActivity_IniciarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_iniciar_sesion);

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity_IniciarSesion.this,R.color.rojo));
    }
}