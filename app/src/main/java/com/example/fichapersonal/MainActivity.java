package com.example.fichapersonal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private Button btnNombre, btnCurso, btnLenguajes, btnNuevo;
    private TextView lblNombre, lblCurso, lblLenguajes;
    private int cursoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblNombre = findViewById(R.id.lblNombre);
        lblCurso = findViewById(R.id.lblCurso);
        lblLenguajes = findViewById(R.id.lblLenguajes);
        btnNombre = findViewById(R.id.btnNombre);
        btnCurso = findViewById(R.id.btnCurso);
        btnLenguajes = findViewById(R.id.btnLenguajes);
        btnNuevo = findViewById(R.id.btnNuevo);

        lblNombre.setText("");
        lblCurso.setText("");
        lblLenguajes.setText("");

        // Botón Nombre
        btnNombre.setOnClickListener(v -> {
            View nombreLayout = getLayoutInflater().inflate(R.layout.alert_nombre, null);
            new AlertDialog.Builder(this)
                    .setTitle(R.string.alertNombreTitulo)
                    .setView(nombreLayout)
                    .setPositiveButton(R.string.alertNombreAceptar, (dialog, which) -> {
                        EditText txtNombre = nombreLayout.findViewById(R.id.txtNombre);
                        lblNombre.setText(txtNombre.getText());
                    })
                    .setNegativeButton(R.string.alertNombreCancelar, null)
                    .create()
                    .show();
        });

        // Botón Curso
        btnCurso.setOnClickListener(v -> {
            String[] arr = getResources().getStringArray(R.array.cursos);
            new AlertDialog.Builder(this)
                    .setTitle(R.string.alertCursoTitulo)
                    .setCancelable(false)
                    .setSingleChoiceItems(arr, 0, (dialog, which) -> {
                        cursoSeleccionado = which;
                    })
                    .setPositiveButton(R.string.alertCursoAceptar, (dialog, which) -> {
                        lblCurso.setText(arr[cursoSeleccionado]);
                    })
                    .setNegativeButton(R.string.alertCursoCancelar, null)
                    .create()
                    .show();
        });

        // Botón Lenguajes
        btnLenguajes.setOnClickListener(v -> {
            String[] arr = getResources().getStringArray(R.array.lenguajes);
            boolean[] checkeItems = new boolean[arr.length];
            new AlertDialog.Builder(this)
                    .setTitle(R.string.alertLenguajesTitulo)
                    .setCancelable(false)
                    .setMultiChoiceItems(arr, checkeItems, (dialog, which, isChecked) -> {
                        checkeItems[which] = isChecked;
                    })
                    .setPositiveButton(R.string.alertLenguajesAceptar, (dialog, which) -> {
                        String texto = "";
                        for (int i = 0; i < arr.length; i++) {
                            if (checkeItems[i]) {
                                texto += arr[i] + "\n";
                            }
                        }
                        lblLenguajes.setText(texto);
                    })
                    .setNegativeButton(R.string.alertLenguajesCancelar, null)
                    .setNeutralButton(R.string.alertLenguajesLimpiar, (dialog, which) -> {
                        Arrays.fill(checkeItems, false);
                    })
                    .create()
                    .show();
        });

        // Botón Nuevo registro
        btnNuevo.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.alertNuevoTitulo)
                    .setMessage(R.string.alertNuevoTexto)
                    .setCancelable(false)
                    .setPositiveButton(R.string.alertNuevoSi, (dialog, which) -> {
                        lblNombre.setText("");
                        lblCurso.setText("");
                        lblLenguajes.setText("");
                    })
                    .setNegativeButton(R.string.alertNuevoNo, null)
                    .create()
                    .show();
        });
    }
}