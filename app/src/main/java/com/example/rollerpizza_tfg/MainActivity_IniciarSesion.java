package com.example.rollerpizza_tfg;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity_IniciarSesion extends AppCompatActivity {


    private EditText edit_Text_Email;
    private TextInputLayout edit_Text_Contrasena;

    private FirebaseAuth autentificacion_cuenta;

    private static final String TAG="MainActivity_IniciarSesion";


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

        edit_Text_Email = findViewById(R.id.editTextEmail);
        edit_Text_Contrasena = findViewById(R.id.textInputLayout);

        autentificacion_cuenta = FirebaseAuth.getInstance();


        Button ingresar = findViewById(R.id.button2);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text_email = edit_Text_Email.getText().toString();
                String text_password = edit_Text_Contrasena.getEditText().getText().toString();

                if (TextUtils.isEmpty(text_email)){
                    Toast.makeText(MainActivity_IniciarSesion.this, "Porfavor introduzca tu Email", Toast.LENGTH_LONG).show();
                    edit_Text_Email.setError("Se requiere un Email");
                    edit_Text_Email.requestFocus();
                } // Comprobamos si el email introudcido por el usuario es correcto o no
                else if (!Patterns.EMAIL_ADDRESS.matcher(text_email).matches()) {
                    Toast.makeText(MainActivity_IniciarSesion.this, "Por favor Vuelva a Introducir el Email", Toast.LENGTH_LONG).show();
                    edit_Text_Email.setError("Se requiere introducir el Email Valido");
                    edit_Text_Email.requestFocus();
                } else if (TextUtils.isEmpty(text_password)) {
                    Toast.makeText(MainActivity_IniciarSesion.this, "Porfavor introduzca una Contraseña", Toast.LENGTH_LONG).show();
                    edit_Text_Contrasena.setError("Se requiere una Contraseña");
                    edit_Text_Contrasena.requestFocus();
                } else {
                    login_user(text_email, text_password);
                }


            }

        });





    }

    private void login_user(String Email, String Password) {
        autentificacion_cuenta.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(MainActivity_IniciarSesion.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    FirebaseUser firebaseUser = autentificacion_cuenta.getCurrentUser();
                    if (firebaseUser.isEmailVerified()) {
                        Toast.makeText(MainActivity_IniciarSesion.this, "Ya has iniciado sesión", Toast.LENGTH_LONG).show();
                    } else {
                        // Determine método de autenticación seleccionado
                        String metodoAutenticacion = "Email"; // Por defecto, supongamos que eligió Email
                        // Mostrar dialogo según el método seleccionado
                        if (metodoAutenticacion.equals("Email")) {
                            mostrar_dialogo_alerta();
                        } else if (metodoAutenticacion.equals("Teléfono")) {
                            mostrar_dialogo_telefono();
                        }
                    }


                }else{

                    //exepciones que debe cumplir el usuario ala hora de registrarse
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){
                        edit_Text_Email.setError("El usuario no existe o ya no es válido. Por favor regístrese nuevamente ");
                        edit_Text_Email.requestFocus();

                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        if (e.getErrorCode().equals("ERROR_INVALID_EMAIL")) {
                            edit_Text_Email.setError("El Email no es válido. Por favor vuelva a introducir un Email válido");
                            edit_Text_Email.requestFocus();
                        } else if (e.getErrorCode().equals("ERROR_WRONG_PASSWORD")) {
                            edit_Text_Contrasena.setError("La contraseña es incorrecta");
                            edit_Text_Contrasena.requestFocus();
                        } else {
                            // Manejar otras excepciones de credenciales inválidas aquí
                            Toast.makeText(MainActivity_IniciarSesion.this, "Credenciales inválidas: Verifique su correo electrónico y contraseña.", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (Exception e){
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(MainActivity_IniciarSesion.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(MainActivity_IniciarSesion.this, "Algo salió mal", Toast.LENGTH_LONG).show();
                    }

                }



            }
        });


    }

    private void mostrar_dialogo_alerta() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_IniciarSesion.this);
        builder.setTitle("Email no verificado");
        builder.setMessage("Porfavor verifique el correo electronico ahora mismo. No puedes iniciar sesión sin verificación por correo electrónico");

        builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        //crear el dialogo
        AlertDialog alertDialog = builder.create();

        //mostrar el dialogo
        alertDialog.show();

        // Obtener el botón positivo del diálogo y cambiar su color de texto a rojo
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if (positiveButton != null) {
            positiveButton.setTextColor(Color.RED);
        }

    }

    private void mostrar_dialogo_telefono() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_IniciarSesion.this);
        builder.setTitle("Teléfono no verificado");
        builder.setMessage("Por favor, verifique su SMS. No puede iniciar sesión sin verificar su número.");

        builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Redirigir a la actividad de verificación por teléfono
                startActivity(new Intent(MainActivity_IniciarSesion.this, Telefono_Verificacion_OTP_Pantalla_1.class));
            }
        });

        // Crear y mostrar el diálogo
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Cambiar el color del texto del botón positivo a rojo
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if (positiveButton != null) {
            positiveButton.setTextColor(Color.RED);
        }
    }

}
