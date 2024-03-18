package com.example.rollerpizza_tfg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class MainActivity_PantallaInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pantalla_inicio);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity_PantallaInicio.this,R.color.mi_color_de_barra_de_notificaciones));
    }
}