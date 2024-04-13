package com.example.armoredmole;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences sharedPreferences = getSharedPreferences( "Configurações" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int duration = Toast.LENGTH_SHORT;

        // Spinner
        Spinner boardSizesSpinner=findViewById(R.id.editBoardSizeSpn);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.boardSizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        boardSizesSpinner.setAdapter(adapter);



        //// Botões

        // Salvar Nome Padrão
        Button saveDefaultName = findViewById(R.id.saveDefNameBtn);

        EditText defaultName = findViewById(R.id.editDefNameTxt);

        saveDefaultName.setOnClickListener(v -> {
            String defaultNameString = defaultName.getText().toString();

            editor.putString("defaultName", defaultNameString);
            editor.apply();

            Toast toast = Toast.makeText(this, "Nome Padrão Salvo Como: " + defaultNameString, duration);
            toast.show();
        });

        // Salvar Tamanho do Tabuleiro
        Button saveBoardSize = findViewById(R.id.saveBoardSizeBtn);

        saveBoardSize.setOnClickListener(v -> {
            String spinnerValue = boardSizesSpinner.getSelectedItem().toString();

            editor.putString("boardSize", spinnerValue);
            editor.apply();

            Toast toast = Toast.makeText(this, "Tamanho do Tabuleiro Salvo Como: " + spinnerValue, duration);
            toast.show();
        });
    }
}