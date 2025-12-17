package com.elarbiallam.notes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteListActivity extends AppCompatActivity {

    private ListView listView;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        listView = findViewById(R.id.listViewNotes);
        FloatingActionButton fab = findViewById(R.id.fabAdd);
        Button btnCamera = findViewById(R.id.btnCamera);

        // Configuration de l'adapter
        adapter = new NoteAdapter(this, NoteManager.getNotes());
        listView.setAdapter(adapter);

        // Clic sur Ajouter
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(NoteListActivity.this, AddNoteActivity.class);
            startActivity(intent);
        });

        // Clic sur une note (Détails)
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Note selectedNote = NoteManager.getNotes().get(position);
            Intent intent = new Intent(NoteListActivity.this, DetailsNoteActivity.class);
            intent.putExtra("NOTE_EXTRA", selectedNote); // On passe l'objet
            startActivity(intent);
        });

        // Clic sur Camera
        btnCamera.setOnClickListener(v -> {
            startActivity(new Intent(NoteListActivity.this, CameraActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Rafraichir la liste quand on revient de l'ajout
        adapter.notifyDataSetChanged();
    }
}