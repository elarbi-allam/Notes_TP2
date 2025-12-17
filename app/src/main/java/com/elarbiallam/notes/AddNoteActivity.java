package com.elarbiallam.notes;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText etNom = findViewById(R.id.etNom);
        EditText etDesc = findViewById(R.id.etDesc);
        EditText etDate = findViewById(R.id.etDate);
        Spinner spinner = findViewById(R.id.spinnerPriorite);
        Button btnSave = findViewById(R.id.btnSave);

        // Remplir le Spinner
        String[] priorities = {"Haute", "Moyenne", "Basse"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, priorities);
        spinner.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String nom = etNom.getText().toString();
            String desc = etDesc.getText().toString();
            String date = etDate.getText().toString();
            String prio = spinner.getSelectedItem().toString();

            if (!nom.isEmpty()) {
                Note newNote = new Note(nom, desc, date, prio);
                NoteManager.addNote(newNote);
                finish(); // Retourne à la liste et ferme l'activité
            } else {
                Toast.makeText(this, "Le titre est obligatoire", Toast.LENGTH_SHORT).show();
            }
        });
    }
}