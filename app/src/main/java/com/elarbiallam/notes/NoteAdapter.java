package com.elarbiallam.notes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(Context context, List<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. Récupération de la note courante
        Note note = getItem(position);

        // 2. Inflation de la vue (si elle n'existe pas déjà)
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);
        }

        // 3. Liaison avec les composants graphiques
        TextView tvNom = convertView.findViewById(R.id.tvNom);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        LinearLayout layoutRoot = convertView.findViewById(R.id.layout_root);

        // 4. Peuplement des données
        tvNom.setText(note.getNom());
        tvDate.setText(note.getDate());

        // 5. Logique de couleur selon la priorité
        if (note.getPriorite().equals("Haute")) {
            layoutRoot.setBackgroundColor(Color.parseColor("#FFCDD2"));
        } else if (note.getPriorite().equals("Moyenne")) {
            layoutRoot.setBackgroundColor(Color.parseColor("#FFF9C4"));
        } else {
            layoutRoot.setBackgroundColor(Color.parseColor("#C8E6C9"));
        }

        return convertView;
    }
}
