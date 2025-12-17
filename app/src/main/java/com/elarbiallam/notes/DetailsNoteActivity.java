package com.elarbiallam.notes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_note);

        TextView tvDetailsNom = findViewById(R.id.tvDetailsNom);
        TextView tvDetailsDesc = findViewById(R.id.tvDetailsDesc);
        TextView tvDetailsPrio = findViewById(R.id.tvDetailsPrio);
        Button btnBack = findViewById(R.id.btnBack);

        // Récupérer l'objet
        Note note = (Note) getIntent().getSerializableExtra("NOTE_EXTRA");

        if (note != null) {
            tvDetailsNom.setText(note.getNom());
            tvDetailsDesc.setText(note.getDescription());
            tvDetailsPrio.setText("Priorité: " + note.getPriorite());
        }

        btnBack.setOnClickListener(v -> finish());
    }
}