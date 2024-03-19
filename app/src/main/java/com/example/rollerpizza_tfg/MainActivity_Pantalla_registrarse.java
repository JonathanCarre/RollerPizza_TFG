package com.example.rollerpizza_tfg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class MainActivity_Pantalla_registrarse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pantalla_registrarse);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity_Pantalla_registrarse.this,R.color.rojo));

        

    }
}