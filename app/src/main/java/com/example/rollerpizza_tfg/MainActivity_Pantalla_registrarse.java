package com.example.rollerpizza_tfg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity_Pantalla_registrarse extends AppCompatActivity {

    private EditText editTextFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pantalla_registrarse);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity_Pantalla_registrarse.this, R.color.rojo));

        TextView inicia_sesion = findViewById(R.id.textView_iniciasesion);
        editTextFecha = findViewById(R.id.editText_fecha);

        inicia_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí es donde se inicia el nuevo Activity
                Intent intent = new Intent(MainActivity_Pantalla_registrarse.this, MainActivity_IniciarSesion.class);
                startActivity(intent);
            }
        });

        // Implement MaterialDatePicker on click of EditText
        editTextFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMaterialDatePicker();
            }
        });
    }

    public void showMaterialDatePicker() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Selecciona una fecha");

        // Crear restricción para fechas futuras
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setEnd(System.currentTimeMillis()); // Establecer la fecha final como el tiempo actual

        builder.setCalendarConstraints(constraintsBuilder.build());

        MaterialDatePicker<Long> materialDatePicker = builder.build();
        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                String fechaSeleccionada = formatDate(selection);
                editTextFecha.setText(fechaSeleccionada);
            }


        });

        // Quitar el foco del EditText
        editTextFecha.clearFocus();
    }


    private String formatDate(Long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Change format if needed
        return sdf.format(new Date(timestamp));
    }
}
